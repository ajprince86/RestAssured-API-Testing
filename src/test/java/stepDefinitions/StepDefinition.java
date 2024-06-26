package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import data_utils.APIResources;
import data_utils.TestDataBuild;
import data_utils.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();

    JsonPath js;
    static String place_id;

    @Given("Add place payload")
    public void add_place_payload() throws IOException {

        res = given()
                .spec(requestSpecification())
                .body(data.addPlacePayload("who", "blank", "space"));
    }

    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {

        resspec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        response = res.when()
                .post("maps/api/place/add/json")
                .then()
                .spec(resspec)
                .extract()
                .response();
    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {

        Assert.assertEquals(response.getStatusCode(), int1);
    }

    @Then("{string} in the response body is {string}")
    public void in_the_response_body_is(String key, String expValue) {

        Assert.assertEquals(getJsonPath(response, key), expValue);

    }

    @Given("Add place payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {

        res = given()
                .spec(requestSpecification())
                .body(data.addPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource, String httpRequest) {

        APIResources resourceAPI = APIResources.valueOf(resource);

        resspec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        if (httpRequest.equalsIgnoreCase("POST")) {
            response = res.when()
                    .post(resourceAPI.getResource());

        } else if (httpRequest.equalsIgnoreCase("GET")) {

            response = res.when()
                    .get(resourceAPI.getResource());
        }

    }

    @And("verify place_id created maps to {string} using the {string}")
    public void verifyPlace_idCreatedMapsToUsingThe(String expectedName, String resource) throws IOException {

        place_id = getJsonPath(response, "place_id");

        res = given()
                .spec(requestSpecification())
                .queryParam("place_id", place_id);

        userCallsWithHttpRequest(resource, "GET");

        String actualName = getJsonPath(response, "name");

        Assert.assertEquals(actualName, expectedName);


    }

    @Given("DeletePlace Payload")
    public void deleteplacePayload() throws IOException {

        res = given()
                .spec(requestSpecification())
                .body(data.deletePlacePayload(place_id));
    }
}
