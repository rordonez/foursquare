package rafael.ordonez.errors;

/**
 * Created by rafa on 27/6/16.
 */
public class FoursquareError {
    private int status;
    private String message;

    public FoursquareError(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
