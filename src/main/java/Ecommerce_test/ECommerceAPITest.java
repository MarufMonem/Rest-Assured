package Ecommerce_test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class ECommerceAPITest {
    public static void main(String[] args) {

        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

//        Setting username and password
        loginReq lr = new loginReq();
        lr.setUserEmail("maruftest@yahoo.com");
        lr.setUserPassword("Test123456");

//        Making the request
        RequestSpecification reqlogin = given().log().all().spec(req).body(lr);
        loginResponse response = reqlogin.when().log().all().post("/api/ecom/auth/login").then().extract().response().as(loginResponse.class);

        System.out.println("The token is: " + response.getToken());


    }
}
