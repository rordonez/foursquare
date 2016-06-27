package rafael.ordonez.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rafael.ordonez.places.VenuesExploreException;

/**
 * Created by rafa on 27/6/16.
 */
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FoursquareError> userNotFoundException(Exception e) {
        return error((VenuesExploreException) e);
    }

    private <E extends VenuesExploreException> ResponseEntity<FoursquareError> error(E e) {
        String message = e.getMessage()!= null ? e.getMessage() : e.getClass().getSimpleName();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new FoursquareError(e.getStatus().value(), message), httpHeaders, e.getStatus());
    }
}
