package model.book;

import model.book.state.K2558859_BookState;
import model.book.state.K2558859_AvailableState;
import model.borrow.K2558859_BorrowRecord;
import model.user.K2558859_User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Abstract K2558859_Book class representing a book in the library system
public abstract class K2558859_Book {
    protected String bookId;
    protected String title;
    protected String author;
    protected String category;
    protected String isbn;
    protected K2558859_BookState availabilityStatus;
    protected List<K2558859_BorrowRecord> borrowHistory;
    protected List<String> metadata;

    // Constructor for K2558859_Book
    protected K2558859_Book(String bookId, String title, String author, String category, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.availabilityStatus = new K2558859_AvailableState();
        this.borrowHistory = new ArrayList<>();
        this.metadata = new ArrayList<>();
    }

    // Constructor with metadata (used by K2558859_BookBuilder)
    protected K2558859_Book(String bookId, String title, String author, String category, String isbn, List<String> metadata) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.availabilityStatus = new K2558859_AvailableState();
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

    public String getIsbn() {
        return isbn;
    }

    public K2558859_BookState getAvailabilityStatus() {
        return availabilityStatus;
    }

    public List<K2558859_BorrowRecord> getBorrowHistory() {
        return new ArrayList<>(borrowHistory);
    }

    // Internal method for states to modify history
    public List<K2558859_BorrowRecord> getBorrowHistoryInternal() {
        return this.borrowHistory;
    }

    // Gets the metadata of the book
    public List<String> getMetadata() {
        return metadata;
    }

    // Sets the availability status of the book
    public void setState(K2558859_BookState state) {
        this.availabilityStatus = state;
    }

    // Adds a borrow record to the book's history
    public void addBorrowRecord(K2558859_BorrowRecord record) {
        this.borrowHistory.add(record);
    }

    // Abstract methods to be implemented by concrete book classes
    public abstract String getDescription();

    // --- State-Delegated Methods ---
    public void borrow(K2558859_User user) {
        availabilityStatus.borrow(this, user);
    }

    public void returnBook() {
        availabilityStatus.returnBook(this);
    }

    public void reserve(K2558859_User user) {
        availabilityStatus.reserve(this, user);
    }

    // Static inner class K2558859_BookBuilder for Builder Pattern
    public static class K2558859_BookBuilder {
        private String bookId;
        private String title;
        private String author;
        private String category;
        private String isbn;
        private List<String> metadata;
        private int year;
        private String genre;

        // Constructor for K2558859_BookBuilder with required parameters
        public K2558859_BookBuilder(String bookId, String title, String author, String category, String isbn) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.category = category;
            this.isbn = isbn;
            this.metadata = new ArrayList<>();
        }

        // Adds optional metadata to the book (reviews, tags, editions, etc.)
        public K2558859_BookBuilder addMetadata(String metadataItem) {
            this.metadata.add(metadataItem);
            return this;
        }

        // Setter for year
        public K2558859_BookBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        // Setter for genre
        public K2558859_BookBuilder setGenre(String genre) {
            this.genre = genre;
            return this;
        }

        // Builds and returns a K2558859_BasicBook instance
        public K2558859_BasicBook build() {
            return new K2558859_BasicBook(bookId, title, author, category, isbn, metadata);
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
}
