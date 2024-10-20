package restAPI;

import files.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class basicsWithFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";

		// Add
		String response = 
		given()
			.queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body(new String (Files.readAllBytes(Paths.get("C:\\Users\\Kimet\\Documents\\eclipse\\addPlace.json"))))
		.when()
				.post("/maps/api/place/add/json")
		.then() // .log().all()
			.assertThat()
			.statusCode(200)
			.body("scope", equalTo("APP"))
			.header("server", "Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();

		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");

		// Update
		String newAddress = "70 Summer walk, USA";

		given()
		.queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\n" +
				"\"place_id\":\"" + placeId + "\",\n" +
				"\"address\":\"" + newAddress + "\",\n" +
				"\"key\":\"qaclick123\"\n" +
				"}\n")
		.when().put("/maps/api/place/update/json")
		.then()
		.assertThat()
		.statusCode(200)
		.body("msg", equalTo("Address successfully updated"))
		.header("server", "Apache/2.4.52 (Ubuntu)");

		// Get
		String getResponse =
		given()
		.queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("/maps/api/place/get/json")
		.then()
		.assertThat()
		.statusCode(200)
		.extract().asString();

		JsonPath js1 = new JsonPath(getResponse);
		String actualAddress = js1.getString("address");
		Assert.assertEquals(actualAddress,newAddress);
	}
}
