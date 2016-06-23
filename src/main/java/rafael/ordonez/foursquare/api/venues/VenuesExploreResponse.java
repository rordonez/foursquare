package rafael.ordonez.foursquare.api.venues;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

/**
 * Created by rafa on 22/6/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VenuesExploreResponse {
    private SuggestedFilters suggestedFilters;
    private GeoCode geocode;
    private String headerLocation;
    private String headerFullLocation;
    private String headerLocationGranularity;
    private int totalResults;
    private Map<String, GeoPoint> suggestedBounds;
    private List<Group> groups;

    public SuggestedFilters getSuggestedFilters() {
        return suggestedFilters;
    }

    public void setSuggestedFilters(SuggestedFilters suggestedFilters) {
        this.suggestedFilters = suggestedFilters;
    }

    public GeoCode getGeocode() {
        return geocode;
    }

    public void setGeocode(GeoCode geocode) {
        this.geocode = geocode;
    }

    public String getHeaderLocation() {
        return headerLocation;
    }

    public void setHeaderLocation(String headerLocation) {
        this.headerLocation = headerLocation;
    }

    public String getHeaderFullLocation() {
        return headerFullLocation;
    }

    public void setHeaderFullLocation(String headerFullLocation) {
        this.headerFullLocation = headerFullLocation;
    }

    public String getHeaderLocationGranularity() {
        return headerLocationGranularity;
    }

    public void setHeaderLocationGranularity(String headerLocationGranularity) {
        this.headerLocationGranularity = headerLocationGranularity;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public Map<String, GeoPoint> getSuggestedBounds() {
        return suggestedBounds;
    }

    public void setSuggestedBounds(Map<String, GeoPoint> suggestedBounds) {
        this.suggestedBounds = suggestedBounds;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
