package sample.domain;

import org.joda.time.DateTime;

/**
 * Process event response.  Contains request information and processing information
 *
 * @author dgoetsch
 */
public class ProcessEventResponse {
    private final ProcessEventRequest request;
    private final DateTime processTime;

    public ProcessEventResponse(ProcessEventRequest request, DateTime processTime) {
        this.processTime = processTime;
        this.request = request;
    }

    public ProcessEventRequest getRequest() {
        return request;
    }
}
