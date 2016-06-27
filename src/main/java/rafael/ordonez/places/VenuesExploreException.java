package rafael.ordonez.places;

import org.springframework.http.HttpStatus;

public class VenuesExploreException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public VenuesExploreException(HttpStatus statusCode, String statusText) {
        super();
        this.status = statusCode;
        this.message = statusText;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
