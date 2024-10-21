package Jira;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static io.restassured.RestAssured.*;

public class bugTest {

    public static void main(String[] args){

        RestAssured.baseURI = "https://kimet.atlassian.net/";
        String createIssueResponse =
                given().
                header("Content-Type","application/json").
                header("Authorization","Basic am9zZUBraW1ldC5jbDpBVEFUVDN4RmZHRjBydk5yaFFnUVlBM3NIVGllNk0tejQ2c2JKY3p4U0tsaG90RzFLLXdfSjJyZHQ4ZUFvYUtLVXFxVGpueFRXdnJIMW9rS1UwaWY2NWtYQlJrbkVZc1JNdzZPOElDUVozQlBQQ01xd252emdJNGdKSUdwaWp1MG9JeXMtVHNlT3BoVFQ0U0xLOEp2bmFkVl9Lb3k4UlpsbHZ3aTFGa2l2LS1HSFVIaWEyeW5FY009RjgxMjM0OTc=").
                body("{\n" +
                        "    \"fields\": {\n" +
                        "        \"project\": {\n" +
                        "            \"key\": \"SCRUM\"\n" +
                        "        },\n" +
                        "        \"summary\": \"Creating test from RestAssured\",\n" +
                        "        \"issuetype\": {\n" +
                        "            \"name\": \"Bug\"\n" +
                        "        }\n" +
                        "    }\n" +
                        "}").
                log().all().
                when().
                    post("rest/api/3/issue").
                then().
                        assertThat().statusCode(201).
                extract().response().asString();

        JsonPath js = new JsonPath(createIssueResponse);
        String issueID = js.getString("id");

        // Add Attachment
        given().
                header("X-Atlassian-Token","no-check").
                header("Authorization","Basic am9zZUBraW1ldC5jbDpBVEFUVDN4RmZHRjBydk5yaFFnUVlBM3NIVGllNk0tejQ2c2JKY3p4U0tsaG90RzFLLXdfSjJyZHQ4ZUFvYUtLVXFxVGpueFRXdnJIMW9rS1UwaWY2NWtYQlJrbkVZc1JNdzZPOElDUVozQlBQQ01xd252emdJNGdKSUdwaWp1MG9JeXMtVHNlT3BoVFQ0U0xLOEp2bmFkVl9Lb3k4UlpsbHZ3aTFGa2l2LS1HSFVIaWEyeW5FY009RjgxMjM0OTc=").
                pathParam("key",issueID).
                multiPart("file",new File("C:\\Users\\Kimet\\Downloads\\blink.jpg")).
        when().
                post("rest/api/3/issue/{key}/attachments").
        then().
                assertThat().statusCode(200);
    }
}
