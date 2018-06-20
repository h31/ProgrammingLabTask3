package app.popov.gohookah.logic;

/**
 * Created by Ferrero on 16.05.2018.
 */

public class HookahAddress {
    private String city;
    private String metro;
    private String street;
    private String houseNumber;


    public HookahAddress(String city, String metro, String street, String houseNumber) {
        this.city = city;
        this.metro = metro;
        this.street = street;
        this.houseNumber = houseNumber;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String area) {
        this.metro = area;
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


    @Override
    public String toString(){
        return new StringBuilder()
                .append(" ").append(getCity()).toString();
    }
}
