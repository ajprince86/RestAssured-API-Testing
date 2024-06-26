package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeDelete() throws IOException {

        StepDefinition stepDefinition = new StepDefinition();

        if (StepDefinition.place_id == null) {

            stepDefinition.addPlacePayloadWith("Bluey", "Swahili", "353-22 North Board St");

            stepDefinition.userCallsWithHttpRequest("AddPlaceAPI", "POST");

            stepDefinition.verifyPlace_idCreatedMapsToUsingThe("Bluey", "GetPlaceAPI");

        }
    }
}
