package sample.service;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import sample.domain.ProcessEventRequest;
import sample.domain.ProcessEventResponse;

/**
 * Sample Service Implementation
 *
 * @author dgoetsch
 */
@Service
public final class SampleServiceImpl implements SampleService {

    /**
     * Process a request
     * @param request
     * @return response
     */
    @Override
    public ProcessEventResponse process(ProcessEventRequest request) {
        return new ProcessEventResponse(request, new DateTime());
    }
}
