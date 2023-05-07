package exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super("No products found with that criteria.");
    }
}
