package BasicTests;

import ResuableItems.ResuableMethods;
import files.BasicApiTestPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicApiTest_UpdateAndValidatePlace {
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
                .body(BasicApiTestPayload.addPlace())
                .when()
                .post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",  equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(response);

//        For parsing JSON
        JsonPath js = ResuableMethods.rawToJson(response);
        String place_id = js.getString("place_id");
        System.out.println("place ID: " + place_id);

//        Update place name---------------
        String addressValue = "Maruf Monem";
        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(BasicApiTestPayload.updatePlace(place_id, addressValue))
        .when()
                .put("/maps/api/place/update/json")
        .then()
                .log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //    Validate the value
        String getResponse = given()
                .log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", place_id)
        .when()
                .get("/maps/api/place/get/json")
        .then()
                .log().all().assertThat().statusCode(200).body("address", equalTo("Maruf Monem")).extract().response().asString();

        JsonPath jsGET = ResuableMethods.rawToJson(getResponse);
        String addressGET = jsGET.get("address");
        Assert.assertEquals(addressGET, addressValue);

    }

}
