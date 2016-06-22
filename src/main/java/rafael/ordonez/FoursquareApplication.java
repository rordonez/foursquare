package rafael.ordonez;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoursquareApplication {

	@Value("${clientId}")
	private String clientId;
	@Value("${clientSecret}")
	private String clientSecret;

	public static void main(String[] args) {
		SpringApplication.run(FoursquareApplication.class, args);
	}
}
