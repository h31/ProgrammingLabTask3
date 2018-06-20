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
        db.collection(city).get().addOnCompleteListener(task -> {
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
        });
    }

    public static void setAddMarkersCallBack(GoogleMapFragment.AddMarkersCallBack callBack) {
        addMarkersCallBack = callBack;
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
}
