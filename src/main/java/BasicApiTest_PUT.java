import files.BasicApiTestPayload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicApiTest_PUT {
    public static void main(String[] args) {

//    1. **Given**: it takes all the input details needed for an API
//    2. **When**: Submit the API
//    3. **There**: Validate the response

        RestAssured.baseURI="https://rahulshettyacademy.com";
//        to use given we need to add the static package
        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(BasicApiTestPayload.addPlace())
                .when()
                .post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",  equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)");


    }
}
