package com.example.evgenysobko.maps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap fullMap;
    LatLng MainBuilding;
    LatLng first;
    LatLng second;
    LatLng third;
    LatLng fourth;
    LatLng eleventh;
    LatLng twentieth;
    LatLng chemical;
    LatLng mechanical;
    LatLng gk1;
    LatLng gk2;
    LatLng hydroTower;
    LatLng labs;
    LatLng ran;
    LatLng iknt;
    LatLng sk;
    LatLng nik;
    LatLng obshaga18;
    LatLng obshaga17;
    LatLng obshagaLesnaya;
    LatLng obshagaMuzhestva;
    LatLng teu;
    LatLng imop;

    @Override
    public void onMapReady(GoogleMap googleMap) {

        fullMap = googleMap;

        MainBuilding = new LatLng(60.007468, 30.372995);
        fullMap.addMarker(new MarkerOptions().position(MainBuilding).title("Главное здание"));

        first = new LatLng(60.008847, 30.372948);
        fullMap.addMarker(new MarkerOptions().position(first).title("1 корпус"));

        chemical = new LatLng(60.006701, 30.376467);
        fullMap.addMarker(new MarkerOptions().position(chemical).title("Химический корпус"));

        mechanical = new LatLng(60.008154, 30.376714);
        fullMap.addMarker(new MarkerOptions().position(mechanical).title("Механический корпус"));

        gk1 = new LatLng(60.005875, 30.381676);
        fullMap.addMarker(new MarkerOptions().position(gk1).title("Гидрокорпус-1"));

        gk2 = new LatLng(60.006637, 30.383596);
        fullMap.addMarker(new MarkerOptions().position(gk2).title("Гидрокорпус-2"));

        hydroTower = new LatLng(60.005577, 30.374050);
        fullMap.addMarker(new MarkerOptions().position(hydroTower).title("Фаблаб"));

        labs = new LatLng(60.007604, 30.379962);
        fullMap.addMarker(new MarkerOptions().position(labs).title("Лабораторный корпус"));

        ran = new LatLng(60.002094, 30.373953);
        fullMap.addMarker(new MarkerOptions().position(ran).title
                ("Санкт-Петербургский национальный исследовательский Академический университет Российской академии наук"));

        second = new LatLng(60.008569, 30.374665);
        fullMap.addMarker(new MarkerOptions().position(second).title("2 корпус"));

        third = new LatLng(60.007269, 30.381452);
        fullMap.addMarker(new MarkerOptions().position(third).title("3 корпус"));

        fourth = new LatLng(60.007312, 30.376753);
        fullMap.addMarker(new MarkerOptions().position(fourth).title("4 корпус"));

        iknt = new LatLng(60.000857, 30.366622);
        fullMap.addMarker(new MarkerOptions().position(iknt).title("9 корпус"));

        sk = new LatLng(60.002635, 30.370215);
        fullMap.addMarker(new MarkerOptions().position(sk).title("СК Политехник"));

        eleventh = new LatLng(60.009098, 30.377779);
        fullMap.addMarker(new MarkerOptions().position(eleventh).title("11 корпус"));

        nik = new LatLng(60.006111, 30.379228);
        fullMap.addMarker(new MarkerOptions().position(nik).title("НИК"));

        obshaga18 = new LatLng(60.022052, 30.387287);
        fullMap.addMarker(new MarkerOptions().position(obshaga18).title("18 общежитие"));

        obshaga17 = new LatLng(60.021754, 30.388296);
        fullMap.addMarker(new MarkerOptions().position(obshaga17).title("17 общежитие"));

        obshagaLesnaya = new LatLng(59.986683, 30.348092);
        fullMap.addMarker(new MarkerOptions().position(obshagaLesnaya).title("Комплекс общежитий на Лесной"));

        teu = new LatLng(59.994848, 30.358214);
        fullMap.addMarker(new MarkerOptions().position(teu).title("Торговый институт (50 корпус)"));

        imop = new LatLng(60.007101, 30.390162);
        fullMap.addMarker(new MarkerOptions().position(imop).title("ИМОП"));

        obshagaMuzhestva = new LatLng(59.998924, 30.374437);
        fullMap.addMarker(new MarkerOptions().position(obshagaMuzhestva).title("Комплекс общежитий на Площади Мужества"));

        twentieth = new LatLng(60.007968, 30.389936);
        fullMap.addMarker(new MarkerOptions().position(twentieth).title("20 корпус"));

        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MainBuilding, 13f));


        fullMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker name) {
                if (name != null) {
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.buildings, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.buildings);
                switch (selectedItemPosition) {
                    case 0: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MainBuilding, 13f));
                        break;
                    }
                    case 1: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(MainBuilding, 17.5f));
                        break;
                    }
                    case 2: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gk1, 17.5f));
                        break;
                    }
                    case 3: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gk2, 17.5f));
                        break;
                    }
                    case 4: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hydroTower, 17.5f));
                        break;
                    }
                    case 5: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(labs, 17.5f));
                        break;
                    }
                    case 6: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mechanical, 17.5f));
                        break;
                    }
                    case 7: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(chemical, 17.5f));
                        break;
                    }
                    case 8: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(first, 17.5f));
                        break;
                    }
                    case 9: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(second, 17.5f));
                        break;
                    }
                    case 10: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(third, 17.5f));
                        break;
                    }
                    case 11: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(fourth, 17.5f));
                        break;
                    }
                    case 12: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(iknt, 17.5f));
                        break;
                    }
                    case 13: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(eleventh, 17.5f));
                        break;
                    }
                    case 14: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(twentieth, 17.5f));
                        break;
                    }
                    case 15: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sk, 17.5f));
                        break;
                    }
                    case 16: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(nik, 17.5f));
                        break;
                    }
                    case 17: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(obshaga17, 17.5f));
                        break;
                    }
                    case 18: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(obshaga18, 17.5f));
                        break;
                    }
                    case 19: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(obshagaLesnaya, 17.5f));
                        break;
                    }
                    case 20: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(obshagaMuzhestva, 17.5f));
                        break;
                    }
                    case 21: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(teu, 17.5f));
                        break;
                    }
                    case 22: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(imop, 17.5f));
                        break;
                    }
                    case 23: {
                        fullMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ran, 17.5f));
                        break;
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}