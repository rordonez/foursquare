package rafael.ordonez.places;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import rafael.ordonez.foursquare.api.venues.Item;
import rafael.ordonez.foursquare.api.venues.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
class PlacesServiceImpl implements PlacesService {

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${version}")
    private String version;


    private RestOperations template;

    @Autowired
    public PlacesServiceImpl(RestOperations template) {
        this.template = template;
    }

    @Override
    public List<Place> getPlaces(String name) {
        Response response = template.getForObject("https://api.foursquare.com/v2/venues/explore?client_id={client_id}&client_secret={client_secret}&v={v}&near={near}", Response.class, getQueryParams(name));

        List<Place> places = new ArrayList<>();

        if(!response.getResponse().getGroups().isEmpty()) {
            for (Item item : response.getResponse().getGroups().get(0).getItems()) {
                places.add(new Place(item.getVenue().getName(), item.getVenue().getUrl(), item.getVenue().getRating()));
            }
        }
        return places;
    }

    private Map<String, Object> getQueryParams(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("v", version);
        params.put("near", name);
        return params;
    }
}