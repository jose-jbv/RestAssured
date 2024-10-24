package restAPI;
import files.Payloads;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com";

		// Add
		String response = 
		given()
		.queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payloads.addPlace())
		.when().post("/maps/api/place/add/json")
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
