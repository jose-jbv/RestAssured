package eComm;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.File;

public class ECommAPI {
    public static void main(String[] args) {
        RequestSpecification req =
                new RequestSpecBuilder()
                        .setBaseUri("https://rahulshettyacademy.com")
                        .setContentType(ContentType.JSON)
                        .build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("jbtest@test.com");
        loginRequest.setUserPassword("Testing1!");

        RequestSpecification reqLogin =
                given().
                    spec(req).
                    body(loginRequest);

        LoginResponse resLogin =
                reqLogin.
                    when().
                        post("/api/ecom/auth/login").
                        then().
                        extract().response().as(LoginResponse.class);

        System.out.println(resLogin.getToken());
        System.out.println(resLogin.getUserId());

        // Add
        RequestSpecification addProductBase =
                new RequestSpecBuilder()
                        .setBaseUri("https://rahulshettyacademy.com")
                        .addHeader("Authorization",resLogin.getToken())
                        .build();

        RequestSpecification reqAddProd =
                given().
                    spec(addProductBase).
                    param("productName","Kimet").
                    param("productAddedBy",resLogin.getUserId()).
                    param("productCategory","fashion").
                    param("productSubCategory","shirts").
                    param("productPrice","182").
                    param("productDescription","KMT Test").
                    param("productFor","women").
                    multiPart("productImage",new File("C:\\Users\\Kimet\\Downloads\\blink.jpg"));

        String resAddProd =
                reqAddProd.
                    when().
                    post("/api/ecom/product/add-product").
                    then().extract().asString();

        JsonPath js = new JsonPath(resAddProd);
        String prodId = js.get("productId");
        System.out.println(prodId);
    }
}
