package com.example.evgenysobko.maps;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@TargetApi(Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap fullMap;
    LatLng MainBuilding = new LatLng(60.007468, 30.372995);

    List<List<String>> normalInfo;
    List<LatLng> coordinates = new ArrayList<>();

    @Override
    public void onMapReady(GoogleMap googleMap) {

        fullMap = googleMap;

        for (List<String> lst : normalInfo) {
            LatLng marker = new LatLng(Double.parseDouble(lst.get(1)), Double.parseDouble(lst.get(2)));
            fullMap.addMarker(new MarkerOptions().position(marker).title(lst.get(0)));
            coordinates.add(marker);
        }

        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MainBuilding, 13f));

        fullMap.setOnInfoWindowClickListener(name -> {
            if (name != null) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstantState) {

        String buildings = getResources().getString(R.string.buildings);
        normalInfo = Arrays.stream(buildings.split("; ")).map(b ->
                Arrays.stream(b.split(", ")).collect(Collectors.toList())).collect(Collectors.toList());

        super.onCreate(savedInstantState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.buildings_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates.get(selectedItemPosition), selectedItemPosition == 0 ? 13f : 17.5f));
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}