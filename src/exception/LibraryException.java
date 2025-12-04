package exception;

// Base exception class for all library-specific exceptions
public class LibraryException extends Exception {
    
    // Constructs a new LibraryException with the specified detail message
    public LibraryException(String message) {
        super(message);
    }
    
    // Constructs a new LibraryException with the specified detail message and cause
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}