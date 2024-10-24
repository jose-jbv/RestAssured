package restAPI;

import files.Payloads;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

        String response =
        given().
            header("Content-Type","application/json").
            body(Payloads.addBook(isbn,aisle)).
        when().
            post("Library/Addbook.php").
        then().
            assertThat().statusCode(200).
            extract().response().asString();

        JsonPath js = new JsonPath(response);
        System.out.println(js.getString("ID"));
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        return new Object[][] {{"asdfg","1234"},{"qwerty","5678"},{"zxcvb","0987"}};
    }
}
