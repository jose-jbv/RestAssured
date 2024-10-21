package Oauth;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class oauthTest {

    public static void main(String[] args){

        RestAssured.baseURI = "https://rahulshettyacademy.com/";

        // Get Token
        String tokenResponse =
                given().
                        formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                        formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").
                        formParams("grant_type", "client_credentials").
                        formParams("scope", "trust").
                when().
                        post("oauthapi/oauth2/resourceOwner/token").asString();

        JsonPath js = new JsonPath(tokenResponse);
        String access_token = js.getString("access_token");

        // Get Details
        String detailsResponse =
                given().
                    queryParam("access_token",access_token).
                when().
                    get("oauthapi/getCourseDetails").asString();
        System.out.println(detailsResponse);

    }
}
