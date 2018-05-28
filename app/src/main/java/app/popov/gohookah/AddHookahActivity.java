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

import app.popov.gohookah.logic.Address;
import app.popov.gohookah.logic.Hookah;
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

        List<String> lines;
        try {
            lines = org.apache.commons.io.IOUtils.readLines(getAssets().open("cities.txt"));
        } catch (IOException ex) {
            lines = null;
        }

        ArrayAdapter<String> citiesList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lines);
        autoCompleteTextView.setAdapter(citiesList);
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> lines = null;
                try {
                    lines = IOUtils.readLines(getAssets().open("spbmetro.txt"));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddHookahActivity.super.getApplicationContext(), android.R.layout.simple_list_item_1, lines);
                    editMetro.setAdapter(arrayAdapter);
                } catch (IOException ex) {
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hookah hookah = new Hookah(editClubName.getText().toString(), new Address( autoCompleteTextView.getText().toString(),
                        editMetro.getText().toString(), editStreet.getText().toString(), editHouseNumber.getText().toString()));
                Firebase.addToFireBase(hookah);
                autoCompleteTextView.setText("");
                editStreet.setText("");
                editHouseNumber.setText("");
                editClubName.setText("");
                editMetro.setText("");
                result.setText("Кальянный клуб добавлен");
            }
        });
    }

}