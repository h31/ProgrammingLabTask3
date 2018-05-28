package app.popov.gohookah.logic;

import java.util.ArrayList;
import java.util.HashMap;

import app.popov.gohookah.AddHookahActivity;

/**
 * Created by Ferrero on 16.05.2018.
 */

public class Hookah {

    public String id;

    private String name;

    private Address address;

    private Rating rating;

    private String phoneNumber;

    private HashMap<String, String> socialNetworks = new HashMap<>();

    private ArrayList<String> gallery = new ArrayList<>();

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

    public HashMap<String, String> getSocialNetworks() {
        return socialNetworks;
    }

    public void setSocialNetworks(HashMap<String, String> socialNetworks) {
        this.socialNetworks = socialNetworks;
    }

    public ArrayList<String> getGallery() {
        return gallery;
    }

    public void setGallery(ArrayList<String> gallery) {
        this.gallery = gallery;
    }

    public Hookah(String name, Address address, HashMap<String, String> socialNetworks){
        this(name, address);
        this.socialNetworks = socialNetworks;
    }

    public Hookah(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Hookah(String id, String name, Address address) {
        this(name, address);
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        return "Hookah{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", rating=" + rating +
                '}';
    }
}
