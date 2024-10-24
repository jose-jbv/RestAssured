package serialize;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {
    public static void main(String[] args){

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress("Prueba");
        place.setLanguage("es");
        place.setPhone_number("123456");
        place.setWebsite("www.kimet.cl");
        place.setName("House");

        List<String> myTypes = new ArrayList<String>();
        myTypes.add("a");
        myTypes.add("b");
        place.setTypes(myTypes);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        place.setLocation(location);

        String response =
                given().
                        queryParam("key","qaclick123").
                        body(place).
                when().
                        post("/maps/api/place/add/json").
                then().
                        assertThat().statusCode(200).
                        extract().response().asString();

        System.out.println(response);

    }
}
