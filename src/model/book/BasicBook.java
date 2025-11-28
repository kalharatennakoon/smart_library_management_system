package model.book;

import model.user.User;
import java.util.List;

/**
 * BasicBook class - Concrete implementation of Book.
 * Represents a standard book in the library system.
 * Acts as the ConcreteComponent in the Decorator Pattern.
 */
public class BasicBook extends Book {

    /**
     * Constructor for BasicBook with basic information.
     */
    public BasicBook(String bookId, String title, String author, String category, String isbn) {
        super(bookId, title, author, category, isbn);
    }

    /**
     * Constructor for BasicBook with metadata (used by BookBuilder).
     */
    public BasicBook(String bookId, String title, String author, String category, String isbn, List<String> metadata) {
        super(bookId, title, author, category, isbn, metadata);
    }

    /**
     * Returns the description of the book.
     * Can be enhanced by decorators.
     */
    @Override
    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append("Book ID: ").append(bookId)
                   .append(", Title: ").append(title)
                   .append(", Author: ").append(author)
                   .append(", Category: ").append(category)
                   .append(", ISBN: ").append(isbn)
                   .append(", Status: ").append(availabilityStatus.getStateName());
        
        // Add metadata if present
        if (!metadata.isEmpty()) {
            description.append(", Metadata: ").append(String.join(", ", metadata));
        }
        
        return description.toString();
    }
}