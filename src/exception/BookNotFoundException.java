package exception;

// Exception thrown when a requested book is not found in the library system
public class BookNotFoundException extends LibraryException {
    
    // Constructs a new BookNotFoundException with the specified book ID
    public BookNotFoundException(String bookId) {
        super("Book with ID '" + bookId + "' not found.");
    }
    
    // Constructs a new BookNotFoundException with a custom message
    public BookNotFoundException(String message, boolean isCustomMessage) {
        super(message);
    }
}
