package exceptions;

public class InvalidDateFormatException extends RuntimeException {
    public InvalidDateFormatException() {
        super("Invalid date format. Please enter a date in the format YYYY-MM-DD.");
    }
}
