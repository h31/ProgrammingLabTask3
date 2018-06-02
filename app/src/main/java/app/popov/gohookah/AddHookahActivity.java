package app.popov.gohookah;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.logic.HookahAddress;
import app.popov.gohookah.logic.database.Firebase;

public class AddHookahActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_hookah_activity);


        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.City);
        final EditText editStreet = (EditText) findViewById(R.id.Street);
        final EditText editHouseNumber = (EditText) findViewById(R.id.HouseNumber);
        final EditText editClubName = (EditText) findViewById(R.id.hookahClubName);
        final AutoCompleteTextView editMetro = (AutoCompleteTextView) findViewById(R.id.MetroStation);
        final Button button = (Button) findViewById(R.id.DoneButton);
        final TextView result = (TextView) findViewById(R.id.result);

        List lines;
        try {
            lines = org.apache.commons.io.IOUtils.readLines(getAssets().open("cities.txt"));
        } catch (IOException ex) {
            lines = null;
        }

        ArrayAdapter citiesList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lines);
        autoCompleteTextView.setAdapter(citiesList);
        autoCompleteTextView.setOnClickListener(v -> {
            List lines1;
            try {
                lines1 = IOUtils.readLines(getAssets().open("spbmetro.txt"));
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddHookahActivity.super.getApplicationContext(), android.R.layout.simple_list_item_1, lines1);
                editMetro.setAdapter(arrayAdapter);
            } catch (IOException ex) {
            }
        });

        button.setOnClickListener(v -> {
            Hookah hookah = new Hookah();
            hookah.setName(editClubName.getText().toString());
            hookah.setMetro(editMetro.getText().toString());
            hookah.setCountry("Россия");
            hookah.setStreet(editStreet.getText().toString());
            hookah.setHouseNumber(editHouseNumber.getText().toString());
            hookah.setCity(autoCompleteTextView.getText().toString());
            Firebase.addToFireBase(hookah, getApplicationContext());
            autoCompleteTextView.setText("");
            editStreet.setText("");
            editHouseNumber.setText("");
            editClubName.setText("");
            editMetro.setText("");
        });
    }

}