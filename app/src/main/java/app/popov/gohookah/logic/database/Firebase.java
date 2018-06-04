package app.popov.gohookah.logic.database;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import app.popov.gohookah.HookahPage;
import app.popov.gohookah.MainActivity;
import app.popov.gohookah.adapters.HookahsAdapterForRecyclerView;
import app.popov.gohookah.logic.Hookah;

public class Firebase {
    private static Map<String, Hookah> hookahs = new LinkedHashMap<>();
    private static Map<String, Hookah> hookahsFromCache = new LinkedHashMap<>();

    private static ArrayList<Hookah> hookahsForAdapter = new ArrayList<>();

    public static Map<String, Hookah> getHookahs() {
        return hookahs;
    }

    public static Map<String, Drawable> mainDrawables = new HashMap<>();
    public static int hookahSize;
    public static void setHookahs(Map<String, Hookah> hookahs) {
        Firebase.hookahs = hookahs;
    }

    public static void refreshHookahList(Location location, Context context) {

        FirebaseFirestore.getInstance().collection("hookahclubs").get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();
                        hookahSize = task.getResult().getDocuments().size();
                        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                        for (DocumentSnapshot ds : documentSnapshots) {
                            Hookah current = new Hookah(ds);
                            current.setDistance(location);
                            hookahs.put(ds.getId(), current);
                            if (current.getImagesNames().size() != 0) {
                                storageReference.child("HookahsImages/" + ds.getId() + "/" + current.getImagesNames().get(0)).getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        mainDrawables.put(ds.getId(), Drawable.createFromStream(new ByteArrayInputStream(bytes), ds.getId()));
                                        if (mainDrawables.size() == hookahSize){
                                            setHookahsForAdapter(new ArrayList<>(hookahs.values()), context, location);
                                        }
                                    }
                                });
                            } else {
                                mainDrawables.put(ds.getId(), null);
                                if (mainDrawables.size() == hookahSize){
                                    setHookahsForAdapter(new ArrayList<>(hookahs.values()), context, location);
                                }
                            }
                        }
                    }
                }
        );
    }


    public static Hookah getHookahForAdapter(int i) {
        return hookahsForAdapter.get(i);
    }

    public static void setHookahsForAdapter(ArrayList<Hookah> listOfHookah, Context context, Location location) {
            hookahsForAdapter = listOfHookah;
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("altitude", location.getAltitude());
            intent.putExtra("longitude", location.getLongitude());
            context.startActivity(intent);
    }

    public static ArrayList<Hookah> getHookahsForAdapter() {
        return hookahsForAdapter;
    }

    public static void setMainDrawables(ByteArrayInputStream bytesStream, String id) {

    }


    public static void getImagesForHookah(Hookah hookah) {
        if (hookah.getImagesNames().size() != 0) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            storageReference.child("HookahsImages/" + hookah.getId() + "/" + hookah.getImagesNames().get(0)).getBytes(1024 * 1024).addOnSuccessListener(bytes -> {
                HookahPage.setImage(new ByteArrayInputStream(bytes));
            });
        }
    }

    public static Drawable getFromId(String id) {
        return mainDrawables.get(id);
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
