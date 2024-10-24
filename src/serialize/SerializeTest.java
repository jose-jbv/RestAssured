package serialize;

import io.restassured.RestAssured;

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

    }
}
