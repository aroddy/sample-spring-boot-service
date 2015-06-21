package sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.joda.time.DateTime;

/**
 * Process event response.  Contains request information and processing information
 *
 * @author dgoetsch
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder(alphabetic=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProcessEventResponse {
    private final ProcessEventRequest request;
    //private final DateTime processTime;

    public ProcessEventResponse(ProcessEventRequest request, DateTime processTime) {
        //this.processTime = processTime;
        this.request = request;
    }

    public ProcessEventRequest getRequest() {
        return request;
    }
}
