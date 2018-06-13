package app.popov.gohookah.fragments;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import app.popov.gohookah.HookahPage;
import app.popov.gohookah.R;
import app.popov.gohookah.fragments.MapViewFragment;
import app.popov.gohookah.logic.Hookah;
import app.popov.gohookah.logic.MyCallback;
import app.popov.gohookah.logic.database.Firebase;
import app.popov.gohookah.logic.database.FirebaseHookah;

public class GoogleMapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    private Context context;

    public GoogleMapFragment(){
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AddMarkersCallBack callBack = new AddMarkersCallBack();
        Firebase.setAddMarkersCallBack(callBack);
        View rootView = inflater.inflate(R.layout.activity_google_map, container, false);
        context = rootView.getContext();

        mMapView = (MapView) rootView.findViewById(R.id.mapViewFragment);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(mMap -> {
            googleMap = mMap;
            UiSettings uiSettings = mMap.getUiSettings();
            uiSettings.setZoomControlsEnabled(true);
        });
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public class AddMarkersCallBack implements MyCallback{
        @Override
        public void call(){
            ArrayList<FirebaseHookah> hookahArrayList = Firebase.getHookahsList();
            Double sumLatitude = 0.0;
            Double sumLongitude = 0.0;

            for (int i = 0; i < hookahArrayList.size(); i++) {
                FirebaseHookah currentHookah = hookahArrayList.get(i);
                LatLng currentLatLng = new LatLng(currentHookah.getLatitude(), currentHookah.getLongitude());
                Marker currentMarker = googleMap.addMarker(new MarkerOptions().position(currentLatLng).title(currentHookah.getClubName()));
                currentMarker.setTag(i);
                sumLatitude += currentLatLng.latitude;
                sumLongitude += currentLatLng.longitude;
            }

            googleMap.setOnInfoWindowClickListener(marker -> {
                Intent intent = new Intent(context, HookahPage.class);
                intent.putExtra("position", (int) marker.getTag());
                startActivity(intent);
            });
            LatLng average = new LatLng(sumLatitude / hookahArrayList.size(), sumLongitude / hookahArrayList.size());
            CameraPosition cameraPosition = new CameraPosition.Builder().target(average).zoom(10).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }
}