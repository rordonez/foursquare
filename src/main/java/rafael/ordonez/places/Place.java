package rafael.ordonez.places;

public class Place {
    private String name;
    private String url;
    private double rating;

    public Place(String name, String url, double rating) {
        this.name = name;
        this.url = url;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public double getRating() {
        return rating;
    }
}
