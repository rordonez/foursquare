package rafael.ordonez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FoursquareApplication {
	public static void main(String[] args) {
		SpringApplication.run(FoursquareApplication.class, args);
	}

	@Bean
	public RestOperations restTemplate() {
		return new RestTemplate();
	}
}
