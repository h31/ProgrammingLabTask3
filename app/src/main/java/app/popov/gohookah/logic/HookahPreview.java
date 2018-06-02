package app.popov.gohookah.logic;

public class HookahPreview {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Float averageRating) {
        this.averageRating = averageRating;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
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

    String id;
    String name;
    Float averageRating;
    String imageURI;

    Double longtiude;
    Double latitude;

}
