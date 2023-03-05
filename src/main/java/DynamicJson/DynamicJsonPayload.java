package DynamicJson;

public class DynamicJsonPayload {

    public static String newBook(){
        String bookDetails = "{\n" +
                "  \"name\": \"Learn Appium Automation with Java\",\n" +
                "  \"isbn\": \"bcd\",\n" +
                "  \"aisle\": \"22kkj7\",\n" +
                "  \"author\": \"John foe\"\n" +
                "}";
        return bookDetails;
    }
}
