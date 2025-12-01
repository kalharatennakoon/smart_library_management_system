package exception;

/**
 * Exception thrown when a requested user is not found in the library system.
 * This is a specialized LibraryException for user-related lookup failures.
 */
public class UserNotFoundException extends LibraryException {
    
    /**
     * Constructs a new UserNotFoundException with the specified user ID.
     * @param userId The ID of the user that was not found
     */
    public UserNotFoundException(String userId) {
        super("User with ID '" + userId + "' not found.");
    }
    
    /**
     * Constructs a new UserNotFoundException with a custom message.
     * @param message The detail message
     * @param isCustomMessage Flag to indicate custom message (not used in logic, just for overloading)
     */
    public UserNotFoundException(String message, boolean isCustomMessage) {
        super(message);
    }
}
