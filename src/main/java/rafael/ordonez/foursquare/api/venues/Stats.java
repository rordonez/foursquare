package rafael.ordonez.foursquare.api.venues;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {
    private int checkinsCount;
    private int usersCounts;
    private int tipCount;

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(int checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public int getUsersCounts() {
        return usersCounts;
    }

    public void setUsersCounts(int usersCounts) {
        this.usersCounts = usersCounts;
    }

    public int getTipCount() {
        return tipCount;
    }

    public void setTipCount(int tipCount) {
        this.tipCount = tipCount;
    }
}
