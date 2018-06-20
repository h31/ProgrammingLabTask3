package app.popov.gohookah.logic.storage;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import app.popov.gohookah.HookahPage;
import app.popov.gohookah.adapters.HookahsAdapterForRecyclerView;
import app.popov.gohookah.logic.RandomString;
import app.popov.gohookah.logic.database.Firebase;
import app.popov.gohookah.logic.database.FirebaseHookah;

public class Storage {

    public static void getImage(FirebaseHookah h, ImageView forSet) {
        String fileName = h.getImages().get(0);
        File imagesDirectory = new File(Firebase.getInternalStorageDir() + "/HookahsImages/");
        if (!imagesDirectory.exists()) {
            imagesDirectory.mkdir();
        }
        File image = new File(Firebase.getInternalStorageDir() + "/HookahsImages/" + fileName);
        if (image.exists()) {
            forSet.setImageDrawable(Drawable.createFromPath(imagesDirectory + "/" + fileName));
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
                    forSet.setImageDrawable(Drawable.createFromPath(imagesDirectory + "/" + fileName));
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }
    }

    public static void uploadImage(String hookahID, byte[] data, HookahPage.SetImageCallback callback) {
        String fileName = RandomString.get(10) + ".jpg";
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


}
