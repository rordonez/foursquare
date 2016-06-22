package rafael.ordonez.places;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rafa on 22/6/16.
 */
@RestController
@RequestMapping("/places")
public class PlacesController {


    @RequestMapping("/{name}")
    public String getPlacesBy(@PathVariable String name) {
        return "";
    }
}
