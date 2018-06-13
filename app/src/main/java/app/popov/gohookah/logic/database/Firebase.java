package app.popov.gohookah.logic.database;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import app.popov.gohookah.HookahPage;
import app.popov.gohookah.MainActivity;
import app.popov.gohookah.adapters.HookahsAdapterForRecyclerView;
import app.popov.gohookah.fragments.GoogleMapFragment;
import app.popov.gohookah.fragments.RecyclerViewFragment;
import app.popov.gohookah.logic.FromCSVParser;
import app.popov.gohookah.logic.Hookah;

public class Firebase {
    private static Map<String, FirebaseHookah> hookahs = new LinkedHashMap<>();

    static ArrayList<FirebaseHookah> hookahsList;

    public static ArrayList<FirebaseHookah> getHookahsList() {
        return hookahsList;
    }


    public static Map<String, FirebaseHookah> getHookahs() {
        return hookahs;
    }

    public static File getInternalStorageDir() {
        return internalStorageDir;
    }

    private static File internalStorageDir;

    private static GoogleMapFragment.AddMarkersCallBack addMarkersCallBack;

    public static void setHookahs(Map<String, FirebaseHookah> hookahs) {
        Firebase.hookahs = hookahs;
    }

    public static void getHookahList(RecyclerViewFragment.AdapterSetterCallback myCallback, Context context, String city) {

        internalStorageDir = context.getFilesDir();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setTimestampsInSnapshotsEnabled(true).setPersistenceEnabled(false).build();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.setFirestoreSettings(settings);
        db.collection(city).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    hookahs.clear();
                    for (DocumentSnapshot snapshot : task.getResult().getDocuments()) {
                        FirebaseHookah firebaseHookah = snapshot.toObject(FirebaseHookah.class);
                        hookahs.put(snapshot.getId(), firebaseHookah);
                    }
                    hookahsList = new ArrayList<>(hookahs.values());
                    myCallback.call();
                    addMarkersCallBack.call();
                }
            }
        });
    }

    public static void getImage(FirebaseHookah h, String fileName, HookahsAdapterForRecyclerView.ViewHolder viewHolder) {
        File imagesDirectory = new File(internalStorageDir + "/HookahsImages/");
        if (!imagesDirectory.exists()) {
            imagesDirectory.mkdir();
        }
        System.out.println(imagesDirectory.getAbsolutePath());
        File image = new File(internalStorageDir + "/HookahsImages/" + fileName);
        System.out.println(image.getAbsolutePath());
        if (image.exists()) {
            viewHolder.setGeneralImage(Drawable.createFromPath(imagesDirectory + "/" + fileName));
        } else {
            try {
                image.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            System.out.println(h.getImages());
            storageReference.child("HookahsImages/" + h.getId() + "/" + h.getImages().get(0)).getBytes(1024 * 1024).addOnSuccessListener(bytes -> {
                try {
                    System.out.println("downloading " + image.getAbsolutePath());
                    FileOutputStream fileOutputStream = new FileOutputStream(image);
                    fileOutputStream.write(bytes);
                    viewHolder.setGeneralImage(Drawable.createFromPath(imagesDirectory + "/" + fileName));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }
    }

    public static void setAddMarkersCallBack(GoogleMapFragment.AddMarkersCallBack callBack){
        addMarkersCallBack = callBack;
    }

    public static void uploadImage(String hookahID, byte[] data, HookahPage.SetImageCallback callback) {
        String fileName = randomString(10) + ".jpg";
        UploadTask uploadTask = FirebaseStorage.getInstance().getReference().child("HookahsImages/" + hookahID + "/" + fileName).putBytes(data);
        FirebaseHookah hookah = Firebase.getHookahs().get(hookahID);
        hookah.getImages().add(fileName);

        uploadTask.addOnSuccessListener(taskSnapshot -> FirebaseFirestore.getInstance().collection(hookah.getCity()).document(hookahID).set(hookah).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            callback.call();
            }
        }));
    }

    public static void addToFireBase(FirebaseHookah h, Context context) {
/*
        Geocoder geocoder = new Geocoder(context);
        List<Address> coordinates;
        HashMap<String, Object> toAdd = new HashMap<>();
        try {
            coordinates = geocoder.getFromLocationName(h.getCity() + " " + h.getStreet() + " " + h.getHouseNumber(), 1);
            toAdd.put("Longtiude", coordinates.get(0).getLongitude());
            toAdd.put("Latitude", coordinates.get(0).getLatitude());
        } catch (IOException ex) {
            toAdd.put("Longtiude", 0.0);
            toAdd.put("Latitude", 0.0);
        }
        */
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection(h.getCity()).document();
        h.setId(documentReference.getId());
        documentReference.set(h)
                .addOnSuccessListener(aVoid -> System.out.println("succesfull added: " + h.getClubName()))
                .addOnFailureListener(e -> System.out.println("fail!"));
    }

    public static void importHookahBase(ArrayList<FirebaseHookah> hookahsForImport, Context context) {
        ArrayList<String> ids = new ArrayList<>();
        for (FirebaseHookah current : hookahsForImport) {
            DocumentReference dr = FirebaseFirestore.getInstance().collection("hookahclubs").document();
            String id = dr.getId();
            ids.add(id);
            current.setId(id);
            dr.set(current).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    System.out.println("Succesfully hookah added (" + id + ")");
                }
            });
        }
        Path outputPath = Paths.get(context.getFilesDir() + "/importLog.txt");
        try {
            Files.createFile(outputPath);
            FileWriter fileWriter = new FileWriter(outputPath.toFile());
            IOUtils.writeLines(ids, "\n", fileWriter);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    private static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

}
