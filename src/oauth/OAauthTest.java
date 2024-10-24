package oauth;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import pojo.Api;
import pojo.WebAutomation;
import pojo.GetCourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class OAauthTest {

    public static void main(String[] args){

        String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
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
        GetCourse detailsResponse =
                given().
                    queryParam("access_token",access_token).
                when().
                    get("oauthapi/getCourseDetails").as(GetCourse.class);

        // print linkedin
        System.out.println(detailsResponse.getLinkedIn());

        System.out.println(detailsResponse.getCourses().getApi().get(1).getCourseTitle());
        List<Api> apiCourses = detailsResponse.getCourses().getApi();

        for(int i=0; i<apiCourses.size();i++){
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUi Webservices testing")){
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        System.out.println("SOUT with normal for");
        List<WebAutomation> webCourse = detailsResponse.getCourses().getWebAutomation();
        for(int i = 0; i<webCourse.size();i++){
            System.out.println(webCourse.get(i).getCourseTitle());
        }

        ArrayList<String> a = new ArrayList<String>();
        for(WebAutomation course : webCourse){
            a.add(course.getCourseTitle());
        }

        List<String> expectedList = Arrays.asList(courseTitles);
        Assert.assertTrue(a.equals(expectedList));
    }
}
