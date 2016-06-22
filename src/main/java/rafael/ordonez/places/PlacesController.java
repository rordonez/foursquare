package rafael.ordonez.places;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by rafa on 22/6/16.
 */
@RestController
@RequestMapping("/places")
public class PlacesController {


    @RequestMapping(method = GET, value = "/{name}", produces = {APPLICATION_JSON_UTF8_VALUE})
    public String getPlacesBy(@PathVariable String name) {
        return "";
    }
}
