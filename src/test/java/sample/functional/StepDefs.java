package sample.functional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.sf.relish.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by devyn on 3/9/15.
 *
 * Json validation does not work very well in relish.
 */
public class StepDefs {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode requestBody;
    List<NameValuePair> headers;
    JsonNode responseBody;
    int responseCode;

    @Before
    public void setup() {
        headers = new ArrayList<>();
    }


    @Given("^web client request body is this JSON:$")
    public void webClientRequestBodyIs(String body) throws IOException {

        this.requestBody = mapper.readTree(body);
    }

    //  TODO implement me
//    @Given("^web client request uses these headers:$")
//    public void webClientRequestUsesHeaders(List<NameValuePair> headers) {
//        this.headers.addAll(headers);
//    }

    @When("^the request is sent to \"(ht" +
            "tp://.*)\" using method \"POST\"$")
    public void requestSentTo(String url) throws IOException {
        String body = mapper.writeValueAsString(requestBody);
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity input = new StringEntity(body);
        post.setEntity(input);
        post.setHeader(new BasicHeader("content-type", "application/json"));
        HttpResponse response = client.execute(post);
        responseBody = mapper.readTree(response.getEntity().getContent());
        responseCode = response.getStatusLine().getStatusCode();
    }

    @Then("^web client response status code should be (\\d{3})$")
    public void webClientResponseStatusCodeShouldBe(int expected) {
        assertEquals(expected, responseCode);
    }

    @Then("^web client request payload should be the following JSON object:$")
    public void webClientRequestBodyJsonShouldBe(String jsonPayload) throws IOException {
        JsonNode expectedResponseBody = mapper.readTree(jsonPayload);
        assertEquals(expectedResponseBody, responseBody);
    }

}
