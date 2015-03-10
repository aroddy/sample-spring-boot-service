package sample.domain;

/**
 * Rquests the service to proces an event
 *
 * @author dgoetsch
 */
public class ProcessEventRequest {
    private Long id;
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
