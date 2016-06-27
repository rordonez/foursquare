package rafael.ordonez.places;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import rafael.ordonez.FoursquareApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.junit.MockitoJUnit.rule;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Created by rafa on 22/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FoursquareApplication.class)
@WebAppConfiguration
public class PlacesControllerTest {

    private MockMvc mockMvc;

    @Rule
    public MockitoRule rule = rule();

    @InjectMocks
    private PlacesController placeController;

    @Mock
    private PlacesService places;

    @Captor
    private ArgumentCaptor<Map<String, Object>> paramsMap;

    @Captor
    private ArgumentCaptor<String> url;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(placeController).build();
    }

    @Test
    public void shouldGetPlacesByName() throws Exception {
        mockMvc.perform(get("/places/{name}", "empty"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldProduceJsonFormat() throws Exception {
        mockMvc.perform(get("/places/{name}", "empty").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    public void shouldInvokeFoursquareAPIVenuesExplore() throws Exception {
        String placeName = "somewhere";
        when(places.getPlaces(anyString())).thenReturn(Arrays.asList());

        mockMvc.perform(get("/places/{name}", placeName));

        verify(places).getPlaces(placeName);
    }

    @Test
    public void shouldReturnAModelWithNameUrlAndRating() throws Exception {
        when(places.getPlaces(anyString())).thenReturn(create(2));
        String placeName = "somewhere";

        mockMvc.perform(get("/places/{name}", placeName))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Place 1")))
                .andExpect(jsonPath("$[0].url", is("http://example1.com")))
                .andExpect(jsonPath("$[0].rating", is(1.0)))
                .andExpect(jsonPath("$[1].name", is("Place 2")))
                .andExpect(jsonPath("$[1].url", is("http://example2.com")))
                .andExpect(jsonPath("$[1].rating", is(2.0)));
    }

    private List<Place> create(int number) {
        return IntStream.range(1,number+1)
                .boxed()
                .map(this::mockPlace)
                .collect(Collectors.toList());
    }

    private Place mockPlace(int id) {
        return new Place("Place " + id, "http://example" + id + ".com", id);
    }
}
