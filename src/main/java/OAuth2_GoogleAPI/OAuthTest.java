package OAuth2_GoogleAPI;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class OAuthTest {
    public static void main(String[] args) {

//        Google doesnt support automation to login so we have manually do it then get the link and run it through rest assured


        String urlForCode = "https://rahulshettyacademy.com/getCourse.php?code=4%XXXXX&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";

        String array1[] = urlForCode.split("code=");
        String array2[] = array1[1].split("&scope");
        String code = array2[0];

        //When passing the code rest assured would try to encode special characters. We need to stop that: urlEncodingEnabled(false)

        //Getting the access token
        String accessTokenResponse = given().urlEncodingEnabled(false)
                .log().all()
                .queryParam("code", code)
                .queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type", "authorization_code")
        .when()
                .log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(accessTokenResponse);
        String accessTokenValue = js.getString("access_token");


//        Using the access token to get the content
        String response = given()
            .log().all()
            . queryParam("access_token", accessTokenValue)
        .when()
            . get("https://rahulshettyacademy.com/getCourse.php").asString();

        System.out.println(response);


    }
}
