package Deserialization;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Test {
    public static void main(String[] args) {

//        Google doesnt support automation to login so we have manually do it then get the link and run it through rest assured


        String urlForCode = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AWtgzh7IvY_5xA_nYp-rhhaxB8GMaM2OPcamsz4JOTUOsViuCJ9o0CcEirNOFHXBfKJywA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";

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
        GetCourse gc = given()
            .queryParam("access_token", accessTokenValue)
            .expect().defaultParser(Parser.JSON)
        .when()
            . get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

        System.out.println("-----------------------------");

        System.out.println("LinkedIn: " + gc.getLinkedIn());
        System.out.println("Instructor name: " + gc.getInstructor());
        System.out.println("Expertise: " + gc.getExpertise());
//        System.out.println("Expertise: " + gc.getCourses());

//        Print courses in API
//       String apiCourseTitle =  gc.getCourses().getApi().get(1).getCourseTitle();

        List<Api> apiOject = gc.getCourses().getApi();
        for (int i=0; i <apiOject.size(); i++){
            String courseTitle = apiOject.get(i).getCourseTitle();
            if(courseTitle.contains("Soap")){

                System.out.println(courseTitle + " - " + apiOject.get(i).getPrice());
            }
        }


        List<WebAutomation> webAutomationOject = gc.getCourses().getWebAutomation();
        for (int i=0; i <apiOject.size(); i++){
            String courseTitle = apiOject.get(i).getCourseTitle();
                System.out.println(courseTitle + " - " + apiOject.get(i).getPrice());
        }







    }
}
