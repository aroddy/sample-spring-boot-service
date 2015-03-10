package sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sample.domain.ProcessEventRequest;
import sample.domain.ProcessEventResponse;
import sample.service.SampleService;

/**
 * Simple controller to handle requests and errors
 * @author dgoetsch
 **/
@RestController
public class SampleSpringController {
    private static final Logger LOG = LoggerFactory.getLogger(SampleSpringController.class);

    @Autowired
    private SampleService service;

    @RequestMapping(value = "/process", method = { RequestMethod.POST }, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProcessEventResponse processEvent(@RequestBody ProcessEventRequest request) {
        return service.process(request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Payload does not have all requisite fields")
    public void illegalBody(final IllegalArgumentException ex) {
        LOG.error("Invalid payload", ex);
    }
}
