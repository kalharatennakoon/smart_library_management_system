package exception;

// Exception thrown when a requested user is not found in the library system
public class UserNotFoundException extends LibraryException {
    
    // Constructs a new UserNotFoundException with the specified user ID
    public UserNotFoundException(String userId) {
        super("User with ID '" + userId + "' not found.");
    }
    
    // Constructs a new UserNotFoundException with a custom message
    public UserNotFoundException(String message, boolean isCustomMessage) {
        super(message);
    }
}
