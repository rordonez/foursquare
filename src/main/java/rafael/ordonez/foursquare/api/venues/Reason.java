package rafael.ordonez.foursquare.api.venues;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reason {
    private int count;
    private List<ReasonItem> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ReasonItem> getItems() {
        return items;
    }

    public void setItems(List<ReasonItem> items) {
        this.items = items;
    }
}
