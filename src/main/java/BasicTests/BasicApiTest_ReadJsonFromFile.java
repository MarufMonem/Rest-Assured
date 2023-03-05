package BasicTests;

import files.BasicApiTestPayload;
import io.restassured.RestAssured;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicApiTest_ReadJsonFromFile {
    public static void main(String[] args) throws IOException {

//    1. **Given**: it takes all the input details needed for an API
//    2. **When**: Submit the API
//    3. **There**: Validate the response

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String path = "X:\\Career\\Learning\\API\\Rest-Assured\\src\\main\\java\\files\\newBook.Json";
//        to use given we need to add the static package
        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get(path))))
                .when()
                .post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",  equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)");


    }
}
