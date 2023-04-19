package BasicTests;

import ResuableItems.ResuableMethods;
import files.ComplexJsonTestPayload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJsonParseTest {
    public static void main(String[] args) {

        JsonPath js = ResuableMethods.rawToJson(ComplexJsonTestPayload.coursePrice());

        System.out.println("LibraryAPI.Test 1");
        System.out.println("----------");
        int courseArraySize = js.getInt("courses.size()");
        System.out.println("Number of courses: " + courseArraySize);

        System.out.println("----------");
        System.out.println("LibraryAPI.Test 2");
        System.out.println("----------");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Total purchase amount: " + purchaseAmount);

        System.out.println("----------");
        System.out.println("LibraryAPI.Test 3");
        System.out.println("----------");
        String firstCourse = js.getString("courses[0].title");
        System.out.println("First course name is: " + firstCourse);

        System.out.println("----------");
        System.out.println("LibraryAPI.Test 4");
        System.out.println("----------");
        for (int i =0; i<courseArraySize; i++){
            System.out.println("Course " + (i+1) + ": " + js.getString("courses["+i+"].title") + " - price: " + js.getString("courses["+i+"].price"));
        }

        System.out.println("----------");
        System.out.println("LibraryAPI.Test 5");
        System.out.println("----------");
        for (int i =0; i<courseArraySize; i++){
            String courseTitle = js.getString("courses["+i+"].title");
            if (courseTitle.equalsIgnoreCase("RPA")){
                System.out.println("RPA course sold: " + js.getString("courses["+i+"].copies")+ " copies");
                break;
            }
        }

        System.out.println("----------");
        System.out.println("LibraryAPI.Test 6");
        System.out.println("----------");
        int sum=0;
        for (int i =0; i<courseArraySize; i++){
            int priceOfEach = js.getInt("courses["+i+"].price");
            int amount = js.getInt("courses["+i+"].copies");
            sum = sum + (priceOfEach*amount);
        }
        System.out.println("Total calculated earnings; " + sum);
//        if (sum==purchaseAmount){
//            System.out.println("Matched");
//        }else{
//            System.out.println("Did not match");
//        }
        Assert.assertEquals(sum,purchaseAmount);


    }

}
