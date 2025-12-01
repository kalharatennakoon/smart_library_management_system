package exception;

/**
 * Exception thrown when an invalid library operation is attempted.
 * Examples include borrowing a book that's already borrowed, returning a book that wasn't borrowed,
 * or removing a user with active borrows.
 * This is a specialized LibraryException for invalid state transitions or business rule violations.
 */
public class InvalidOperationException extends LibraryException {
    
    /**
     * Constructs a new InvalidOperationException with the specified detail message.
     * @param message The detail message explaining what operation was invalid and why
     */
    public InvalidOperationException(String message) {
        super(message);
    }
}
