package app.popov.gohookah.logic.database;

import android.content.Context;
import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.popov.gohookah.AddHookahActivity;
import app.popov.gohookah.HookahPage;
import app.popov.gohookah.MainActivity;
import app.popov.gohookah.logic.Hookah;

public class Firebase {
    public List<DocumentSnapshot> getDocumentSnapshots() {
        return documentSnapshots;
    }

    public void setDocumentSnapshots(List<DocumentSnapshot> documentSnapshots) {
        this.documentSnapshots = documentSnapshots;
    }

    static List<DocumentSnapshot> documentSnapshots = new ArrayList<>();
    static String id;

    public static void setAdapterFromFireBase(final Context context) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("hookahclubs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int size = task.getResult().getDocuments().size();
                    setDocumentSnapshotList(task.getResult().getDocuments());
        setID(String.valueOf(Integer.valueOf(task.getResult().getDocuments().get(size - 1).getId()) + 1));
                    ArrayList<String> names = new ArrayList<>();
                    for (DocumentSnapshot ds : getDocumentSnaphots()) {
                        Object toAdd = ds.get("Имя клуба");
                        if (toAdd != null) {
                            names.add(toAdd.toString());
                        }
                    }
                    MainActivity.setHookahListAdapter(names, context);
                }
            }
        });
    }

    public static void setID(String forSet) {
        id = forSet;
    }

    public static void getID() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("hookahclubs").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    int size = task.getResult().getDocuments().size();
                    setID(task.getResult().getDocuments().get(size - 1).getId());
                }
            }
        });
    }

    public static void setDocumentSnapshotList(List<DocumentSnapshot> documentSnapshotsToSet) {
        documentSnapshots = documentSnapshotsToSet;
    }

    public static List<DocumentSnapshot> getDocumentSnaphots() {
        return documentSnapshots;
    }

    public static void getFromFireBase(final String document) {
        FirebaseFirestore.getInstance().collection("hookahclubs").document(document).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                HookahPage.setText(task.getResult().getData().get("Адрес").toString());
            }
        });
    }

    public static void addToFireBase(Hookah h) {
        getID();
        HashMap<String, Object> toAdd = new HashMap<>();
        toAdd.put("Имя клуба", h.getName());
        HashMap<String, Object> addr = new HashMap<>();
        addr.put("Страна", "Россия");
        addr.put("Город", h.getAddress().getCity());
        addr.put("Улица", h.getAddress().getStreet());
        addr.put("Метро", h.getAddress().getMetro());
        addr.put("Номер здания", h.getAddress().getHouseNumber());
        toAdd.put("Адрес", addr);
        FirebaseFirestore.getInstance().collection("hookahclubs").document(id)
                .set(toAdd)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("succesfull");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("fail!");
                    }
                });
    }

    class AsyncDataAdder extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}
