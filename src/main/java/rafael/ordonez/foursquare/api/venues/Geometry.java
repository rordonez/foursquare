package rafael.ordonez.foursquare.api.venues;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private Map<String, GeoPoint> bounds;

    public Map<String, GeoPoint> getBounds() {
        return bounds;
    }

    public void setBounds(Map<String, GeoPoint> bounds) {
        this.bounds = bounds;
    }
}
