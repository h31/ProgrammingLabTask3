package app.popov.gohookah.logic;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import app.popov.gohookah.AddHookahActivity;
import app.popov.gohookah.MainActivity;

import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class MyLocationListener implements LocationListener {
    public static Location current;

    public void SetUpLocationListener(Context context) {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    10,
                    locationListener);
            current = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return;
        }
    }

    @Override
    public void onLocationChanged(Location loc) {
        current = loc;

    }

    public boolean hasPermission(String perm, Context context) {
        return (PackageManager.PERMISSION_GRANTED == checkSelfPermission(context, perm));
    }

    public boolean canAccessLocation(Context context) {
        return (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION, context));
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
