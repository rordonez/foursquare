package rafael.ordonez.places;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.springframework.web.client.RestOperations;
import rafael.ordonez.foursquare.api.venues.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
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

    @Test
    public void shouldMapVenuesCorrectly() throws Exception {
        String place = "any";
        Response response = responseWithVenues();
        Mockito.when(operations.getForObject(anyString(), any(), anyMap())).thenReturn(response);

        List<Place> places = placesService.getPlaces(place);

        assertThat(places, is(notNullValue()));
        assertThat(places, hasSize(1));
    }

    private Response responseWithNoResults() {
        Response response = Mockito.mock(Response.class);
        VenuesExploreResponse venuesExploreResponse = Mockito.mock(VenuesExploreResponse.class);
        Mockito.when(venuesExploreResponse.getGroups()).thenReturn(new ArrayList<>());
        Mockito.when(response.getResponse()).thenReturn(venuesExploreResponse);
        return response;
    }

    private Response responseWithVenues() {
        Response response = Mockito.mock(Response.class);
        VenuesExploreResponse venuesExploreResponse = Mockito.mock(VenuesExploreResponse.class);
        Group group = Mockito.mock(Group.class);
        Item item = Mockito.mock(Item.class);
        Mockito.when(item.getVenue()).thenReturn(new Venue());
        Mockito.when(group.getItems()).thenReturn(Arrays.asList(item));
        Mockito.when(venuesExploreResponse.getGroups()).thenReturn(Arrays.asList(group));
        Mockito.when(response.getResponse()).thenReturn(venuesExploreResponse);
        return response;
    }

}
