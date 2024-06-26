Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if a place is being successfully added using the AddPlaceAPI
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then the API call is success with status code 200
    And "status" in the response body is "OK"
    And "scope" in the response body is "APP"
    And verify place_id created maps to "<name>" using the "GetPlaceAPI"

    Examples:
      | name        | language | address         |
      | Loung Lotus | English  | 22-64 Holy St   |
      | Mobile Brks | Spanish  | 35-99 Center St |


  @DeletePlace
  Scenario: Verify if Delete place functionality is working
    Given DeletePlace Payload
    When user calls "DeletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in the response body is "OK"



