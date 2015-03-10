package sample.service;

import sample.domain.ProcessEventRequest;
import sample.domain.ProcessEventResponse;

/**
 * Sample service to be implemented
 *
 * @author dgoetsch
 */
public interface SampleService {

    public ProcessEventResponse process(ProcessEventRequest request);
}
