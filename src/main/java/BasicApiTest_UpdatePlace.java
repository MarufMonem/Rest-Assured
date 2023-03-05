import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicApiTest_UpdatePlace {
    public static void main(String[] args) {

//    1. **Given**: it takes all the input details needed for an API
//    2. **When**: Submit the API
//    3. **There**: Validate the response

        RestAssured.baseURI="https://rahulshettyacademy.com";
//        to use given we need to add the static package
        String response = given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.addPlace())
                .when()
                .post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",  equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(response);

//        For parsing JSON
        JsonPath js = new JsonPath(response);
        String place_id = js.getString("place_id");
        System.out.println("place ID: " + place_id);

//        Update place name---------------
        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(payload.updatePlace(place_id))
        .when()
                .put("/maps/api/place/update/json")
        .then()
                .log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
    }
}
