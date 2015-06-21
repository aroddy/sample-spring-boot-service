package sample.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.http.HttpHeaders
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import sample.Application
import sample.controller.SampleSpringController
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


/**
 * Created by devyn on 6/21/15.
 */
@ContextConfiguration(classes = [Application], loader = SpringApplicationContextLoader)
@ActiveProfiles("integration")
class SampleServiceIntegrationSpec extends Specification {

    @Autowired
    SampleSpringController controller
    MockMvc mockMvc

    def setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    def "test send request"() {
        given:
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json")

        when:
        ResultActions results = mockMvc.perform(post("/process")
                .content("{\"id\":1234,\"message\":\"my message\"}")
                .headers(headers))

        then:
        results.andExpect(status().isOk())
        results.andReturn().getResponse().getContentAsString() == "{\"request\":{\"id\":1234,\"message\":\"my message\"}}"
    }
    def "send request that does not have a message"() {
        given:
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json")

        when:
        ResultActions results = mockMvc.perform(post("/process")
                .content("{\"id\":1234}")
                .headers(headers))

        then:
        results.andExpect(status().isOk())
        results.andReturn().getResponse().getContentAsString() == "{\"request\":{\"id\":1234}}"
    }
    def "send request that does not have an id"() {
        given:
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json")

        when:
        ResultActions results = mockMvc.perform(post("/process")
                .content("{\"message\":\"i am a message\"}")
                .headers(headers))

        then:
        results.andExpect(status().isOk())
        results.andReturn().getResponse().getContentAsString() == "{\"request\":{\"message\":\"i am a message\"}}"
    }
    def "send request with extra fields"() {
        given:
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json")

        when:
        ResultActions results = mockMvc.perform(post("/process")
                .content("{\"id\":1234,\"message\":\"my message\",\"extra\":\"field\"}")
                .headers(headers))

        then:
        results.andExpect(status().isOk())
        results.andReturn().getResponse().getContentAsString() == "{\"request\":{\"id\":1234,\"message\":\"my message\"}}"
    }
    def "process invalid json"() {
        given:
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json")

        when:
        ResultActions results = mockMvc.perform(post("/process")
                .content("{{\"id\":::1234,\"message\":\"my message\",\"extra\":\"field\"}")
                .headers(headers))

        then:
        results.andExpect(status().isBadRequest())
    }
}
