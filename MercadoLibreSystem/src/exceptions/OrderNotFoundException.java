package exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("No orders found with that criteria.");
    }
}
