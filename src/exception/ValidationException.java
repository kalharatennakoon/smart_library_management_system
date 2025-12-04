package exception;

// Exception thrown when input validation fails
public class ValidationException extends LibraryException {
    
    // Constructs a new ValidationException with the specified detail message
    public ValidationException(String message) {
        super(message);
    }
    
    // Constructs a new ValidationException with field name and reason
    public ValidationException(String fieldName, String reason) {
        super("Validation failed for field '" + fieldName + "': " + reason);
    }
}
