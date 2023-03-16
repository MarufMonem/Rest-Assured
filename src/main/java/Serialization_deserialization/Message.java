package Serialization_deserialization;
//This is a POJO class example
public class Message {
    private String message;
    private String greet;


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setGreet(String greet) {
        this.greet = greet;
    }

    public String getGreet() {
        return greet;
    }
}
