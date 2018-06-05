package app.popov.gohookah.logic.database;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StreamDownloadTask;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import app.popov.gohookah.HookahPage;
import app.popov.gohookah.MainActivity;
import app.popov.gohookah.adapters.HookahsAdapterForRecyclerView;
import app.popov.gohookah.logic.Hookah;

public class Firebase {
    private static Map<String, Hookah> hookahs = new LinkedHashMap<>();

    private static ArrayList<Hookah> hookahsForAdapter = new ArrayList<>();

    public static Map<String, Hookah> getHookahs() {
        return hookahs;
    }

    private static File internalStorageDir;

    public static void setHookahs(Map<String, Hookah> hookahs) {
        Firebase.hookahs = hookahs;
    }

    public static void refreshHookahList(Location location, Context context) {
        internalStorageDir = context.getFilesDir();
       // FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final boolean[] isStartUp = {true};
        db.collection("hookahclubs").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    List<DocumentChange> documentSnapshots = queryDocumentSnapshots.getDocumentChanges();
                    for (DocumentChange ds : documentSnapshots) {
                        System.out.println(ds.getType());
                        switch (ds.getType()) {

                            case ADDED:
                            System.out.println(ds.getDocument().getMetadata());
                            Hookah current = new Hookah(ds.getDocument());
                            current.setDistance(location);
                            hookahs.put(ds.getDocument().getId(), current);

                            case REMOVED:
                                if (!isStartUp[0]) {
                                if ((MainActivity.positionOfHookahFromID(ds.getDocument().getId())) != -1) {
                                    MainActivity.removeFromAdapter(MainActivity.positionOfHookahFromID(ds.getDocument().getId()));
                                }
                            }
                            case MODIFIED:
                                Hookah modifed = new Hookah(ds.getDocument());
                                modifed.setDistance(location);
                                hookahs.put(ds.getDocument().getId(), modifed);
                        }
                    }
                    if (isStartUp[0]){
                        isStartUp[0] = false;
                    setHookahsForAdapter(new ArrayList<>(hookahs.values()));
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("altitude", location.getAltitude());
                    intent.putExtra("longitude", location.getLongitude());
                    context.startActivity(intent);
                } else {
                    MainActivity.addNewHookahToAdapter(new Hookah(documentSnapshots.get(0).getDocument()));
                    }
            }
        });
    }


    public static Hookah getHookahForAdapter(int i) {
        return hookahsForAdapter.get(i);
    }

    public static Drawable getImage(Hookah h, String fileName) {
        File imagesDirectory = new File(internalStorageDir + "/HookahsImages/");
        if (!imagesDirectory.exists()){
            imagesDirectory.mkdir();
        }
        File image = new File(imagesDirectory + fileName);
        if (image.exists()) {
            return Drawable.createFromPath(imagesDirectory + fileName);
        } else {
            try {
                image.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            storageReference.child("HookahsImages/" + h.getId() + "/" + h.getImagesNames().get(0)).getBytes(1024 * 1024).addOnSuccessListener(bytes -> {
                try {
                    System.out.println("downloading " + image.getAbsolutePath());
                    FileOutputStream fileOutputStream = new FileOutputStream(image);
                    fileOutputStream.write(bytes);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }
        return getImage(h, fileName);
    }

    public static void setHookahsForAdapter(ArrayList<Hookah> listOfHookah) {
        hookahsForAdapter = listOfHookah;
    }

    public static ArrayList<Hookah> getHookahsForAdapter() {
        return hookahsForAdapter;
    }

    public static void getImagesForHookah(Hookah hookah) {
        if (hookah.getImagesNames().size() != 0) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            storageReference.child("HookahsImages/" + hookah.getId() + "/" + hookah.getImagesNames().get(0)).getBytes(1024 * 1024).addOnSuccessListener(bytes -> {
                HookahPage.setImage(new ByteArrayInputStream(bytes));
            });
        }
    }

    public static void addToFireBase(Hookah h, Context context) {

        Geocoder geocoder = new Geocoder(context);
        List<Address> coordinates = new ArrayList<>();
        HashMap<String, Object> toAdd = new HashMap<>();
        try {
            coordinates = geocoder.getFromLocationName(h.getCity() + " " + h.getStreet() + " " + h.getHouseNumber(), 1);
            toAdd.put("Longtiude", coordinates.get(0).getLongitude());
            toAdd.put("Latitude", coordinates.get(0).getLatitude());

        } catch (IOException ex) {
            toAdd.put("Longtiude", 0.0);
            toAdd.put("Latitude", 0.0);
        }
        toAdd.put("Name", h.getName());
        toAdd.put("Country", "Россия");
        toAdd.put("City", h.getCity());
        toAdd.put("Street", h.getStreet());
        toAdd.put("Metro", h.getMetro());
        toAdd.put("HouseNumber", h.getHouseNumber());
        ArrayList<String> imagesNames = new ArrayList<>();
        toAdd.put("ImagesNames", imagesNames);
        FirebaseFirestore.getInstance().collection("hookahclubs").document()
                .set(toAdd)
                .addOnSuccessListener(aVoid -> System.out.println("succesfull"))
                .addOnFailureListener(e -> System.out.println("fail!"));
    }
}
