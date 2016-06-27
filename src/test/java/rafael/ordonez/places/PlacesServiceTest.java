package rafael.ordonez.places;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.springframework.web.client.RestOperations;
import rafael.ordonez.foursquare.api.venues.Response;
import rafael.ordonez.foursquare.api.venues.VenuesExploreResponse;

import java.util.ArrayList;

import static org.mockito.Matchers.*;
import static org.mockito.junit.MockitoJUnit.rule;

/**
 * Created by rafa on 27/6/16.
 */
public class PlacesServiceTest {

    @Rule
    public MockitoRule rule = rule();

    @InjectMocks
    private PlacesServiceImpl placesService;

    @Mock
    private RestOperations operations;

    @Test
    public void shouldInvokeFoursquare() throws Exception {
        String place = "any";
        Response response = responseWithNoResults();
        Mockito.when(operations.getForObject(anyString(), any(), anyMap())).thenReturn(response);

        placesService.getPlaces(place);

        Mockito.verify(operations).getForObject(anyString(), any(), anyMap());
    }

    private Response responseWithNoResults() {
        Response response = Mockito.mock(Response.class);
        VenuesExploreResponse venuesExploreResponse = Mockito.mock(VenuesExploreResponse.class);
        Mockito.when(venuesExploreResponse.getGroups()).thenReturn(new ArrayList<>());
        Mockito.when(response.getResponse()).thenReturn(venuesExploreResponse);
        return response;
    }


}
