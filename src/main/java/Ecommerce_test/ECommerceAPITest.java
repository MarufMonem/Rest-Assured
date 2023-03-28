package Ecommerce_test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ECommerceAPITest {
    public static void main(String[] args) {

        RequestSpecification baseLoginReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

//        Setting username and password
        loginReq lr = new loginReq();
        lr.setUserEmail("maruftest@yahoo.com");
        lr.setUserPassword("Test123456");

//        Making the request
        RequestSpecification reqlogin = given().log().all().spec(baseLoginReq).body(lr);
        loginResponse loginResponse = reqlogin.when().log().all().post("/api/ecom/auth/login").then().extract().response().as(loginResponse.class);

        System.out.println("The token is: " + loginResponse.getToken());

//        Create product
//        ------------- FORM PARAMETERS ----------------
        RequestSpecification addProductBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", loginResponse.getToken()).build();

        RequestSpecification addProductReq = given().spec(addProductBaseReq)
                .param("productName", "Laptop")
                .param("productAddedBy", loginResponse.getUserId())
                .param("productCategory", "Tech")
                .param("productSubCategory", "Portable")
                .param("productPrice", "6969")
                .param("productDescription", "HP")
                .param("productFor", "Students")
                .multiPart("productImage", new File("C:\\Users\\Anik\\Desktop\\camera.jpg"));

        Product newlyCreatedProduct = addProductReq.when().post("api/ecom/product/add-product").then().log().all().extract().as(Product.class);

        Assert.assertEquals("Product Added Successfully", newlyCreatedProduct.getMessage());

        System.out.println("The product ID is: " + newlyCreatedProduct.getProductId());

//        Creating order
        RequestSpecification orderBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", loginResponse.getToken()).build();

//        Order details creation
        OrderDetails od = new OrderDetails();
        od.setCountry("India");
        od.setProductOrderedId(newlyCreatedProduct.getProductId());

        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
        orderDetailsList.add(od);

        Order fullOrder = new Order();
        fullOrder.setOrders(orderDetailsList);

        RequestSpecification orderReq = given().spec(orderBaseReq).body(fullOrder);
        OrderResponse or = orderReq.when().post("/api/ecom/order/create-order").then().extract().as(OrderResponse.class);

        System.out.println("Order ID: " + or.getProductOrderId()[0]);
        System.out.println(or.getMessage());
        Assert.assertEquals("Order Placed Successfully", or.getMessage());

//        Deleting the product created
        RequestSpecification deleteBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", loginResponse.getToken()).build();

        RequestSpecification deleteReq =  given().spec(deleteBaseReq).pathParam("productID",newlyCreatedProduct.getProductId() );

        String deleteResponse = deleteReq.when().delete("/api/ecom/product/delete-product/{productID}").then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(deleteResponse);
        String deleteMessage = js.get("message");
        System.out.println(deleteMessage);
        Assert.assertEquals("Product Deleted Successfully", deleteMessage);

        System.out.println("-------------DONE--------------");


    }
}
