package exception;

/**
 * Base exception class for all library-specific exceptions.
 * This is the parent class for specialized exceptions like BookNotFoundException,
 * UserNotFoundException, InvalidOperationException, and ValidationException.
 * 
 * Design Pattern: Exception Hierarchy Pattern
 * Provides a consistent error handling mechanism across the library system.
 */
public class LibraryException extends Exception {
    
    /**
     * Constructs a new LibraryException with the specified detail message.
     * @param message The detail message explaining the exception
     */
    public LibraryException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new LibraryException with the specified detail message and cause.
     * @param message The detail message explaining the exception
     * @param cause The cause of the exception (for exception chaining)
     */
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}