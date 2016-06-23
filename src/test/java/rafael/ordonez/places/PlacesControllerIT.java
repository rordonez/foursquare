package rafael.ordonez.places;

import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import rafael.ordonez.FoursquareApplication;

import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FoursquareApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PlacesControllerIT {

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = serverPort;
    }

    @Test
    public void shouldInvokeFoursquarePlacesAPICorrectly() throws Exception {
        String place = "London";
        when().
                get("/places/{name}", place).
        then().
                statusCode(HttpStatus.SC_OK);
    }
}
