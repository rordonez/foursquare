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
import rafael.ordonez.foursquare.api.venues.Group;
import rafael.ordonez.foursquare.api.venues.Item;
import rafael.ordonez.foursquare.api.venues.VenuesExploreResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by rafa on 22/6/16.
 */
@RestController
@RequestMapping("/places")
public class PlacesController {

    private PlacesService places;

    @Autowired
    public PlacesController(PlacesService places) {
        this.places = places;
    }

    @RequestMapping(method = GET, value = "/{name}", produces = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Place>> getPlacesBy(@PathVariable String name) {
        return new ResponseEntity<>(places.getPlaces(name), getHttpHeaders(), HttpStatus.OK);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        return headers;
    }
}
