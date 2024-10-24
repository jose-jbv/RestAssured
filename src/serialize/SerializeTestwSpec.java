package serialize;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTestwSpec {
    public static void main(String[] args){

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

        RequestSpecification spec =
                new RequestSpecBuilder().
                    setBaseUri("https://rahulshettyacademy.com").
                    addQueryParam("key","qaclick123").
                    setContentType("application/json").
                    build();

        ResponseSpecification resSpec =
                new ResponseSpecBuilder().
                        expectStatusCode(200).
                        expectContentType(ContentType.JSON).
                        build();

        RequestSpecification req =
                given().
                        spec(spec).
                        body(place);

        String response =
                req.when().
                        post("/maps/api/place/add/json").
                then().
                        spec(resSpec).
                        extract().response().asString();

        System.out.println(response);

    }
}
