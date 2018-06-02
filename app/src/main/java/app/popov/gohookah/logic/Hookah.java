package app.popov.gohookah.logic;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.view.View;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

import app.popov.gohookah.AddHookahActivity;
import app.popov.gohookah.adapters.HookahsAdapterForRecyclerView;

/**
 * Created by Ferrero on 16.05.2018.
 */

public class Hookah {

    public Hookah(DocumentSnapshot ds){
        this.id = ds.getId();
        this.name = ds.get("Name").toString();
        this.country = ds.get("Country").toString();
        this.street = ds.get("Street").toString();
        this.street = ds.get("Metro").toString();
        this.houseNumber = ds.get("HouseNumber").toString();
        if ((Double) ds.get("Longtiude") + (Double) ds.get("Latitude") != 0.0){
            this.locationOfClub = new Location("passive");
            this.locationOfClub.setLongitude((Double) ds.get("Longtiude"));
            this.locationOfClub.setLatitude((Double) ds.get("Latitude"));
        }
        this.imagesNames = (ArrayList<String>) ds.get("ImagesNames");
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String id;

    private String name;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Location getLocationOfClub() {
        return locationOfClub;
    }

    public void setLocationOfClub(Location locationOfClub) {
        this.locationOfClub = locationOfClub;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    private String country;
    private String city;
    private String street;
    private String metro;
    private String houseNumber;


    private Rating rating;

    private String phoneNumber;

    private Double longtiude;
    private Double latitude;

    private Location locationOfClub;

    private Float distance;

    public Hookah(){
    }

    public Double getLongtiude() {
        return longtiude;
    }

    public void setLongtiude(Double longtiude) {
        this.longtiude = longtiude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Float getDistance() {
        return distance;
    }

    public void setLocationOfClub(String latitude, String longtiude){
        locationOfClub = new Location("passive");

        locationOfClub.setLatitude(Double.parseDouble(latitude));
        locationOfClub.setLongitude(Double.parseDouble(longtiude));
        System.out.println(locationOfClub);
    }

    public void setDistance(HookahsAdapterForRecyclerView.ViewHolder viewHolder){
        viewHolder.distance.setText(getDistance() == 10000F ? "" : getKilometers(getDistance()));
        if (viewHolder.distance.getText() == "") {
            viewHolder.geoIcon.setVisibility(View.INVISIBLE);
        }
    }

    public String getKilometers(Float distance){
        System.out.println("Distance "+ distance);
        return "fuck";
    }

    public void setDistance(Location location) {
        if (location != null && getLatitude() != null && getLongtiude() != null) {
            distance = locationOfClub.distanceTo(location);
        } else {distance = 10000F;}
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private ArrayList<String> imagesNames = new ArrayList<>();

    public ArrayList<String> getImagesNames() {
        return imagesNames;
    }

    public void setImagesNames(ArrayList<String> imagesNames) {
        this.imagesNames = imagesNames;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
