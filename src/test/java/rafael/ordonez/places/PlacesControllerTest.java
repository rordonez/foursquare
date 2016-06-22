package rafael.ordonez.places;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rafael.ordonez.FoursquareApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rafa on 22/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FoursquareApplication.class)
@WebAppConfiguration
public class PlacesControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private PlacesController placeController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(placeController).build();

    }

    @Test
    public void shouldGetPlacesByName() throws Exception {
        mockMvc.perform(get("/places/{name}", "empty"))
                .andExpect(status().isOk());
    }

}
