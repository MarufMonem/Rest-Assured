package Ecommerce_test;

public class OrderResponse {
    private String[] orders;
    private String message;
    private String[] productOrderId;

    public String[] getOrders() {
        return orders;
    }

    public void setOrders(String[] orders) {
        this.orders = orders;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(String[] productOrderId) {
        this.productOrderId = productOrderId;
    }
}
