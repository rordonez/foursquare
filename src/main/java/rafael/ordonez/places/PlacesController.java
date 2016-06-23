package rafael.ordonez.places;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import rafael.ordonez.foursquare.api.venues.VenuesExploreResponse;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by rafa on 22/6/16.
 */
@RestController
@RequestMapping("/places")
public class PlacesController {

    @Value("${clientId}")
    private String clientId;

    @Value("${clientSecret}")
    private String clientSecret;

    @Value("${version}")
    private String version;

    private RestOperations template;

    @Autowired
    public PlacesController(RestOperations template) {
        this.template = template;
    }

    @RequestMapping(method = GET, value = "/{name}", produces = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<VenuesExploreResponse> getPlacesBy(@PathVariable String name) {
        return new ResponseEntity<>(template.getForObject("https://api.foursquare.com/v2/venues/explore?client_id={client_id}&client_secret={client_secret}&v={v}&near={near}", VenuesExploreResponse.class, getQueryParams(name)), getHttpHeaders(), HttpStatus.OK);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        return headers;
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
