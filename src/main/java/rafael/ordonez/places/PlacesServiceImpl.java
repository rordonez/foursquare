package rafael.ordonez.places;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestOperations;
import rafael.ordonez.foursquare.api.venues.Group;
import rafael.ordonez.foursquare.api.venues.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Place> getPlaces(String place) {
        Response response = exploreVenuesFor(place);

        return response.getResponse().getGroups().stream()
                    .map(Group::getItems)
                    .flatMap(List::stream)
                    .map(x -> x.getVenue())
                    .map(venue -> new Place(venue.getName(), venue.getUrl(), venue.getRating()))
                    .collect(Collectors.toList());
    }

    private Response exploreVenuesFor(String name) {
        Response response;
        try {
            response = template.getForObject("https://api.foursquare.com/v2/venues/explore?client_id={client_id}&client_secret={client_secret}&near={near}", Response.class, getQueryParams(name));
        }
        catch (HttpStatusCodeException e) {
            throw new VenuesExploreException(e.getStatusCode(), e.getStatusText());
        }
        return response;
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
