package rafael.ordonez.places;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by rafa on 22/6/16.
 */
@RestController
@RequestMapping("/places")
public class PlacesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlacesController.class);

    private PlacesService places;

    @Autowired
    public PlacesController(PlacesService places) {
        this.places = places;
    }

    @RequestMapping(method = GET, value = "/{name}", produces = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<List<Place>> getPlacesBy(@PathVariable String name) {
        LOGGER.info("Getting recommended places for {}", name);
        return new ResponseEntity<>(places.getPlaces(name), getHttpHeaders(), HttpStatus.OK);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        return headers;
    }
}
