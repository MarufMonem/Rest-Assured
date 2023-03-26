package Serialization;
import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class test {
    public static void main(String[] args) {
//        Creating the body
        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setAddress("Dhaka");
        ap.setLanguage("Bangla");
        ap.setName("Maruf");
        ap.setPhone_number("12345");
        ap.setWebsite("www.google.com");
        List<String> type = new ArrayList<String>();
        type.add("Phone");
        type.add("laptop");
        ap.setTypes(type);

        Location l = new Location();
        l.setLat(-38.3834);
        l.setLng(33.4273);
        ap.setLocation(l);


        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response res = given()
                .queryParam("key", "qaclick123")
        .when()
                .post("/maps/api/place/add/json")
        .then()
                .assertThat().statusCode(200).extract().response();








    }
}
