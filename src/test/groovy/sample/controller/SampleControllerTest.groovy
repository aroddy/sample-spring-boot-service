package sample.controller

import org.joda.time.DateTime
import sample.domain.ProcessEventRequest
import sample.domain.ProcessEventResponse
import sample.service.SampleService
import spock.lang.Specification

/**
 * Created by devyn on 3/9/15.
 */
class SampleControllerTest extends Specification {
    SampleSpringController controller
    ProcessEventRequest request = Mock()
    ProcessEventResponse response = Mock()
    SampleService service = Mock()

    def setup() {
        //easy dependency injection on private methods
        controller = new SampleSpringController(service : service)
    }

    def "process an event"() {
        ProcessEventResponse actualResponse

        when:
        actualResponse = controller.processEvent(request)

        then:
        1 * service.process(request) >> response
        response == actualResponse
    }
}
