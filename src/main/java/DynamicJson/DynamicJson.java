package DynamicJson;

import ResuableItems.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "booksData")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI= "http://216.10.245.166";

        String response = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(DynamicJsonPayload.newBook(isbn, aisle))
        .when()
                .post("Library/Addbook.php")
        .then()
                .assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ResuableMethods.rawToJson(response);
        String bookID = js.getString("ID");
        System.out.println(bookID);

    }

    @DataProvider(name="booksData")
    public Object[][] getData(){
        return new Object[][]{
                {"isbn1", "aisle1"},
                {"isbn2", "aisle2"},
                {"isbn3", "aisle3"}
        };
    }
}
