package data_utils;

import placeCreation.Location;
import placeCreation.AddPlace;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayload(String name, String language, String address) {

        AddPlace newPlace = new AddPlace();
        List<String> types = new ArrayList<>();
        Location location = new Location(90.88, 22.89);
        types.add("Sports Bar");
        types.add("Club");

        newPlace.setAccuracy(89);
        newPlace.setAddress(address);
        newPlace.setLanguage(language);
        newPlace.setPhone_number("698-334-9733");
        newPlace.setWebsite("www.bats.com");
        newPlace.setName(name);
        newPlace.setTypes(types);
        newPlace.setLocation(location);

        return newPlace;
    }

    public String deletePlacePayload(String place_id) {

        return "{\n    \"place_id\":\"" + place_id + "\"\n}";

    }
}
