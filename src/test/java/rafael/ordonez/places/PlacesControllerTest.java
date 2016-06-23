package rafael.ordonez.places;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestOperations;
import rafael.ordonez.FoursquareApplication;
import rafael.ordonez.foursquare.api.venues.Group;
import rafael.ordonez.foursquare.api.venues.Item;
import rafael.ordonez.foursquare.api.venues.Venue;
import rafael.ordonez.foursquare.api.venues.VenuesExploreResponse;

import java.util.Arrays;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
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
    private RestOperations template;

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
        when(template.getForObject(anyString(), any(), anyMap())).thenReturn(new VenuesExploreResponse());
        String placeName = "somewhere";

        mockMvc.perform(get("/places/{name}", placeName));

        verify(template).getForObject(url.capture(), Mockito.eq(VenuesExploreResponse.class), paramsMap.capture());
        assertThat(url.getValue(), is("https://api.foursquare.com/v2/venues/explore"));
        assertParamsForVenuesAreValid();
    }

    private void assertParamsForVenuesAreValid() {
        assertTrue(paramsMap.getValue().containsKey("client_id"));
        assertTrue(paramsMap.getValue().containsKey("client_secret"));
        assertTrue(paramsMap.getValue().containsKey("v"));
        assertTrue(paramsMap.getValue().containsKey("near"));
    }

    @Test
    public void shouldReturnAModelWithNameUrlAndRating() throws Exception {
        when(template.getForObject(anyString(), any(), anyMap())).thenReturn(twoPlaces());
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

    private VenuesExploreResponse twoPlaces() {
        VenuesExploreResponse response = new VenuesExploreResponse();
        Group group = new Group();
        group.setItems(Arrays.asList(mockItem(mockVenue(1)),  mockItem(mockVenue(2))));
        response.setGroups(Arrays.asList(group));
        return response;
    }

    private Item mockItem(Venue venue) {
        Item item = new Item();
        item.setVenue(venue);
        return item;
    }

    private Venue mockVenue(int id) {
        Venue venue = new Venue();
        venue.setName("Place " + id);
        venue.setUrl("http://example" + id + ".com");
        venue.setRating((double)id);
        return venue;
    }
}
