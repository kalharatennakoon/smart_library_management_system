package exception;

// Exception thrown when an invalid library operation is attempted
public class InvalidOperationException extends LibraryException {
    
    // Constructs a new InvalidOperationException with the specified detail message
    public InvalidOperationException(String message) {
        super(message);
    }
}
