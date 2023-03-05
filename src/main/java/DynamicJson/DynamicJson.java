package DynamicJson;

import ResuableItems.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJson {

    @Test
    public void addBook(){
        RestAssured.baseURI= "http://216.10.245.166";

        String response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(DynamicJsonPayload.newBook())
        .when()
                .post("Library/Addbook.php")
        .then()
                .assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ResuableMethods.rawToJson(response);
        String bookID = js.getString("ID");
        System.out.println(bookID);

    }
}
