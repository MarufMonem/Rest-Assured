package Serialization;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class test {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response res = given()
                .queryParam("key", "qaclick123")
        .when()
                .post("/maps/api/place/add/json")
        .then()
                .assertThat().statusCode(200).extract().response();






    }
}
