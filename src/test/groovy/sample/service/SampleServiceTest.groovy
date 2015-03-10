package sample.service

import sample.domain.ProcessEventRequest
import sample.domain.ProcessEventResponse
import spock.lang.Specification

/**
 * Created by devyn on 3/9/15.
 */
class SampleServiceTest extends Specification {
    SampleService service

    def setup() {
        service = new SampleServiceImpl()
    }

    def "process request transforms into response"() {
        given:
        ProcessEventRequest request = new ProcessEventRequest()
        request.setId(123456L)
        request.setMessage("I am a message")
        ProcessEventResponse response

        when:
        response = service.process(request)

        then:
        response.request.id == 123456L
        response.request.message == "I am a message"
    }
}
