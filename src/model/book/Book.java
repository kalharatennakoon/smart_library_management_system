package model.book;

import model.book.state.BookState;
import model.book.state.AvailableState;
import model.borrow.BorrowRecord;
import model.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract Book class representing a book in the library system.
 * Uses State Pattern for availability status.
 * Serves as the Component in the Decorator Pattern.
 * Contains BookBuilder as a static inner class for the Builder Pattern.
 */
public abstract class Book {
    protected String bookId;
    protected String title;
    protected String author;
    protected String category;
    protected String isbn;
    protected BookState availabilityStatus;
    protected List<BorrowRecord> borrowHistory;
    protected List<String> metadata;

    /**
     * Constructor for Book.
     * Initializes a book with available state and empty borrow history.
     */
    protected Book(String bookId, String title, String author, String category, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.availabilityStatus = new AvailableState();
        this.borrowHistory = new ArrayList<>();
        this.metadata = new ArrayList<>();
    }

    /**
     * Constructor with metadata (used by BookBuilder).
     */
    protected Book(String bookId, String title, String author, String category, String isbn, List<String> metadata) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.availabilityStatus = new AvailableState();
        this.borrowHistory = new ArrayList<>();
        this.metadata = metadata != null ? new ArrayList<>(metadata) : new ArrayList<>();
    }

    // Getters
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getISBN() {
        return isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookState getAvailabilityStatus() {
        return availabilityStatus;
    }

    public List<BorrowRecord> getBorrowHistory() {
        return new ArrayList<>(borrowHistory);
    }

    /**
     * Gets the metadata of the book.
     * @return the metadata list
     */
    public List<String> getMetadata() {
        return metadata;
    }

    /**
     * Sets the availability status of the book.
     * Used by state objects to transition between states.
     */
    public void setAvailabilityStatus(BookState state) {
        this.availabilityStatus = state;
    }

    /**
     * Adds a borrow record to the book's history.
     */
    public void addBorrowRecord(BorrowRecord record) {
        this.borrowHistory.add(record);
    }

    // Abstract methods to be implemented by concrete book classes
    public abstract String getDescription();

    public abstract void borrow(User user);

    public abstract void returnBook();

    public abstract void reserve(User user);

    /**
     * Static inner class BookBuilder for Builder Pattern.
     * Allows creation of complex book objects with optional metadata.
     */
    public static class BookBuilder {
        private String bookId;
        private String title;
        private String author;
        private String category;
        private String isbn;
        private List<String> metadata;
        private int year;
        private String genre;

        /**
         * Constructor for BookBuilder with required parameters.
         */
        public BookBuilder(String bookId, String title, String author, String category, String isbn) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.category = category;
            this.isbn = isbn;
            this.metadata = new ArrayList<>();
        }

        /**
         * Adds optional metadata to the book (reviews, tags, editions, etc.).
         * @param metadataItem A metadata item to add
         * @return The BookBuilder instance for method chaining
         */
        public BookBuilder addMetadata(String metadataItem) {
            this.metadata.add(metadataItem);
            return this;
        }

        // Setter for year
        public BookBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        // Setter for genre
        public BookBuilder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        /**
         * Builds and returns a BasicBook instance.
         * @return A new BasicBook object with the configured properties
         */
        public BasicBook build() {
            return new BasicBook(bookId, title, author, category, isbn, metadata);
        }
    }

    // Setter for ISBN (optional, if needed)
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Setter for Metadata (optional, if needed)
    public void setMetadata(List<String> metadata) {
        this.metadata = metadata;
    }

    private String generateBookId() {
        return "B" + String.format("%04d", new Random().nextInt(10000));
    }
}