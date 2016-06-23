package rafael.ordonez.foursquare.api.venues;

public class Response {
    private Meta meta;
    private VenuesExploreResponse response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public VenuesExploreResponse getResponse() {
        return response;
    }

    public void setResponse(VenuesExploreResponse response) {
        this.response = response;
    }
}
