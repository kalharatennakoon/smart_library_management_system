package exception;

/**
 * Exception thrown when input validation fails.
 * Examples include invalid email format, invalid contact number format,
 * or missing required fields.
 * This is a specialized LibraryException for data validation failures.
 */
public class ValidationException extends LibraryException {
    
    /**
     * Constructs a new ValidationException with the specified detail message.
     * @param message The detail message explaining what validation failed
     */
    public ValidationException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ValidationException with field name and reason.
     * @param fieldName The name of the field that failed validation
     * @param reason The reason why validation failed
     */
    public ValidationException(String fieldName, String reason) {
        super("Validation failed for field '" + fieldName + "': " + reason);
    }
}
