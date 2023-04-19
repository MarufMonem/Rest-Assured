package LibraryAPI;
import ResuableItems.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AddBook {
    public static void main(String[] args) {
        RestAssured.baseURI= "http://216.10.245.166";
        Response response = given().header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"name\": \"Learn Appium Automation with Java1\",\n" +
                        "    \"isbn\": \"abcdef\",\n" +
                        "    \"aisle\": \"0192\",\n" +
                        "    \"author\": \"John foe1\"\n" +
                        "}")
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response();
        JsonPath js = ResuableMethods.rawToJson(response.asString());
        String id = js.get("ID");
        System.out.println(id);

    }
}
