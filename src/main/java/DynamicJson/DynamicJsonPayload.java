package DynamicJson;

public class DynamicJsonPayload {

    public static String newBook(String isbn, String aisle){

        String bookDetails = "{\n" +
                "  \"name\": \"Learn Appium Automation with Java\",\n" +
                "  \"isbn\": \""+isbn+"\",\n" +
                "  \"aisle\": \""+aisle+"\",\n" +
                "  \"author\": \"John foe\"\n" +
                "}";
        return bookDetails;
    }

    public static String deleteBook(String isbn, String aisle){
        String id = isbn+aisle;
        String bookDetails = "{\n" +
                "  \"ID\" : \""+id+"\"\n" +
                "}\n" +
                "\n";
        return bookDetails;
    }
}
