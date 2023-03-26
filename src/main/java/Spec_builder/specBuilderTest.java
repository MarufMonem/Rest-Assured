package Spec_builder;

import Serialization.AddPlace;
import Serialization.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class specBuilderTest {
    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder().setContentType(ContentType.JSON)
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON)
                .build();

        ResponseSpecification resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

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

//        Creating the whole request
        RequestSpecification res = given().spec(req).body(ap);

        String responseJson = res.when()
                .post("/maps/api/place/add/json")
                .then().spec(resSpec).extract().response().asString();


        System.out.println(responseJson);




    }
}
