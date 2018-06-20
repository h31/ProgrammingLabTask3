package app.popov.gohookah.fragments;


import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

import app.popov.gohookah.R;
import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.logic.database.Firebase;
import app.popov.gohookah.logic.database.FirebaseHookah;

import static android.app.Activity.RESULT_OK;

public class RatingAndContactsFragment extends Fragment {
    private FirebaseHookah hookah;
    private View rootView;
    private AppCompatImageButton addPhoto;

    public RatingAndContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contactandrates, container, false);

        TextView vk = (TextView) rootView.findViewById(R.id.vkurl);
        TextView instagram = (TextView) rootView.findViewById(R.id.instagramurl);
        Bundle bundle = this.getArguments();
        hookah = Firebase.getHookahs().get(bundle.getString("id"));
        TextView address = (TextView) rootView.findViewById(R.id.addressText);
        ImageView addressIcon = (ImageView) rootView.findViewById(R.id.addressIcon);


        TextView metro = (TextView) rootView.findViewById(R.id.metroDescr);
        ImageView metroIcon = (ImageView) rootView.findViewById(R.id.metroDescrIcon);

        Context context = getContext();
        if (hookah.getVk().equals("")) {
            vk.setText("Добавить");
        } else {
            vk.setText(hookah.getVk());
            vk.setOnClickListener(v -> {
                Uri uri = Uri.parse("https://vk.com/" + hookah.getVk());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });
            vk.setOnLongClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", hookah.getVk());
                clipboard.setPrimaryClip(clip);
                makeCopiedSnack();
                return true;
            });
        }
        if (hookah.getInstagram().equals("")) {
            instagram.setText("Добавить");

        } else {
            instagram.setText(hookah.getInstagram());
            instagram.setOnClickListener(v -> {
                Uri uri = Uri.parse("https://instagram.com/" + hookah.getInstagram());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            });
            instagram.setOnLongClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", hookah.getInstagram());
                clipboard.setPrimaryClip(clip);
                makeCopiedSnack();
                return true;
            });
        }
        if (hookah.getStreet() == null) {
            address.setVisibility(View.INVISIBLE);
            addressIcon.setVisibility(View.INVISIBLE);
        } else {
            address.setText(hookah.getStreet() + ", " + hookah.getHouseNumber());
        }
        if (hookah.getMetro() == null) {
            metro.setVisibility(View.INVISIBLE);
            metroIcon.setVisibility(View.INVISIBLE);
        } else {
            metro.setText(hookah.getMetro());
        }
        return rootView;
    }


    private void makeCopiedSnack() {
        Snackbar.make(rootView, "Скопировано в буфер обмена", Snackbar.LENGTH_SHORT).show();
    }
}