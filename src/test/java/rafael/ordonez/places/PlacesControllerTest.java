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
import rafael.ordonez.foursquare.api.venues.VenuesExploreResponse;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.junit.MockitoJUnit.rule;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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


}
