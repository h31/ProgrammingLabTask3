package app.popov.gohookah;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.List;

import app.popov.gohookah.R;
import app.popov.gohookah.logic.database.Firebase;

public class SearchCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.selectCity);

        List<String> lines;
        try {
            lines = org.apache.commons.io.IOUtils.readLines(getAssets().open("cities.txt"));
        } catch (IOException ex) {
            lines = null;
        }

        ArrayAdapter citiesList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lines);
        autoCompleteTextView.setAdapter(citiesList);
        autoCompleteTextView.setOnItemClickListener((parent, arg1, position, arg3) -> {
            String locale = parent.getItemAtPosition(position).toString();
           finish();
        });
        ImageButton back = (ImageButton) findViewById(R.id.backfromsearch);
        back.setOnClickListener(v -> finish());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
