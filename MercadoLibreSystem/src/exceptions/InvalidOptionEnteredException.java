package exceptions;

public class InvalidOptionEnteredException extends RuntimeException {
    public InvalidOptionEnteredException() {
        super("Invalid option entered. Please try again.");
    }
}
