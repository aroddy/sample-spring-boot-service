Feature: The event-processor-oer-mid-json should parse a MID, decorate the event, and forward the json to the event ledger
  Scenario: Process an event, event is decorated and sent to m2m forwarder for publishing
    #Set up mock client
    And web client request body is this JSON:
    """
    {"id":123456,"message":"howdy doo da"}
    """

    #Set up event processor
    And console app "sample.Application" is running with args: --spring.config.location=classpath:/funTest.yml
    When sleep for 10 seconds
    #send event through event processor
    When the request is sent to "http://localhost:15623/sample/process" using method "POST"

    Then web client response status code should be 200
    And web client request payload should be the following JSON object:
    """
    {"request": {"id": 123456, "message": "howdy doo da"}}
    """