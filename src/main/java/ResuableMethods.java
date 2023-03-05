import io.restassured.path.json.JsonPath;

public class ResuableMethods {

    public static JsonPath rawToJson(String response){
        return new JsonPath(response);
    }
}
