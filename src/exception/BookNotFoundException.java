package exception;

/**
 * Exception thrown when a requested book is not found in the library system.
 * This is a specialized LibraryException for book-related lookup failures.
 */
public class BookNotFoundException extends LibraryException {
    
    /**
     * Constructs a new BookNotFoundException with the specified book ID.
     * @param bookId The ID of the book that was not found
     */
    public BookNotFoundException(String bookId) {
        super("Book with ID '" + bookId + "' not found.");
    }
    
    /**
     * Constructs a new BookNotFoundException with a custom message.
     * @param message The detail message
     * @param isCustomMessage Flag to indicate custom message (not used in logic, just for overloading)
     */
    public BookNotFoundException(String message, boolean isCustomMessage) {
        super(message);
    }
}
