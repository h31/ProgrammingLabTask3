package app.popov.gohookah.logic.database;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import app.popov.gohookah.HookahPage;
import app.popov.gohookah.MainActivity;
import app.popov.gohookah.adapters.HookahsAdapterForRecyclerView;

import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.logic.HookahAddress;

public class Firebase {
    //private static ArrayList<Drawable> images = new ArrayList<>();
    private static Map<String, Hookah> hookahs = new LinkedHashMap<>();
    private static ArrayList<Hookah> hookahsForAdapter = new ArrayList<>();

    public static Map<String, Hookah> getHookahs() {
        return hookahs;
    }

    public static void getHookahsFromFireBase(Location location) {
        FirebaseFirestore.getInstance().collection("hookahclubs").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot ds : task.getResult().getDocuments()) {
                    Hookah current = new Hookah(ds);
                    current.setDistance(location);
                    hookahs.put(ds.getId(), current);

                }
                setHookahsForAdapter(new ArrayList<>(hookahs.values()));
            }
        });

    }

    public static Hookah getHookahForAdapter(int i) {
        return hookahsForAdapter.get(i);
    }

    public static void setHookahsForAdapter(ArrayList<Hookah> listOfHookah) {
        hookahsForAdapter = listOfHookah;
        MainActivity.setHookahListAdapter(hookahsForAdapter);
    }

    public static void downloadMainImage(HookahsAdapterForRecyclerView.ViewHolder viewHolder, String name, String id){

        FirebaseStorage.getInstance().getReference().child("HookahsImages/" + id + "/" + name)
                .getBytes(1024 * 1024).addOnSuccessListener(bytes -> {
                    viewHolder.setGeneralImage(Drawable.createFromStream(new ByteArrayInputStream(bytes), name));
        });
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
        try {
            coordinates = geocoder.getFromLocationName(h.getCity() + " " + h.getStreet() + " " + h.getHouseNumber(), 1);
        } catch (IOException ex) {
        }
        HashMap<String, Object> toAdd = new HashMap<>();
        toAdd.put("Name", h.getName());
        toAdd.put("Longtiude", coordinates.get(0).getLongitude());
        toAdd.put("Latitude", coordinates.get(0).getLatitude());
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
