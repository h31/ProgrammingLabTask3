package app.popov.gohookah;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

import app.popov.gohookah.MainActivity;
import app.popov.gohookah.logic.database.Firebase;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;


public class SplashActivity extends AppCompatActivity {

    private String[] needPermissions = {ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION};
    private String locale;
    private FusedLocationProviderClient mFusedLocationClient;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, needPermissions[0]) + ActivityCompat.checkSelfPermission(this, needPermissions[1]) == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                System.out.println(location);
                if (location != null) {
                    startWithLocation(location);
                } else {
                    startWithOutLocation();
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, needPermissions, 1337);
           startWithOutLocation();
        }
    }

    void startWithOutLocation(){
        Location location = new Location("passive");
        location.setLongitude(0.0);
        location.setLatitude(0.0);
        startActivity(new Intent(context, MainActivity.class));
        finish();
    }

    void startWithLocation(Location location){
        Intent intent = new Intent(context, MainActivity.class);
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        try {
            Locale aLocale = new Locale.Builder().setLanguage("ru").setScript("Cyrl").setRegion("RU").build();
            Geocoder geo = new Geocoder(context, aLocale);
            Address address = geo.getFromLocation(latitude, longitude, 1).get(0);
            locale = address.getLocality();
        } catch (IOException io) {
            locale = null;
        }
        intent.putExtra("locale", locale);
        startActivity(intent);
        finish();
    }

}
