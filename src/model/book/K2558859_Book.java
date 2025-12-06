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
    // Fields
    protected String bookId;
    protected String title;
    protected String author;
    protected String category;
    protected String isbn;
    protected K2558859_BookState availabilityStatus;
    protected List<K2558859_BorrowRecord> borrowHistory;
    protected List<String> metadata;

    // Constructors
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

    public List<K2558859_BorrowRecord> getBorrowHistoryInternal() {
        return this.borrowHistory;
    }

    public List<String> getMetadata() {
        return metadata;
    }

    // Setters
    public void setState(K2558859_BookState state) {
        this.availabilityStatus = state;
    }

    // Public Methods
    public void addBorrowRecord(K2558859_BorrowRecord record) {
        this.borrowHistory.add(record);
    }

    public void borrow(K2558859_User user) {
        availabilityStatus.borrow(this, user);
    }

    public void returnBook() {
        availabilityStatus.returnBook(this);
    }

    public void reserve(K2558859_User user) {
        availabilityStatus.reserve(this, user);
    }

    // Abstract Methods
    public abstract String getDescription();

    // Inner Builder Class
    public static class K2558859_BookBuilder {
        private String bookId;
        private String title;
        private String author;
        private String category;
        private String isbn;
        private List<String> metadata;
        private int year;
        private String genre;

        public K2558859_BookBuilder(String bookId, String title, String author, String category, String isbn) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.category = category;
            this.isbn = isbn;
            this.metadata = new ArrayList<>();
        }

        public K2558859_BookBuilder addMetadata(String metadataItem) {
            this.metadata.add(metadataItem);
            return this;
        }

        public K2558859_BookBuilder setYear(int year) {
            this.year = year;
            this.metadata.add("Year: " + year);
            return this;
        }

        public K2558859_BookBuilder setGenre(String genre) {
            this.genre = genre;
            this.metadata.add("Genre: " + genre);
            return this;
        }

        public K2558859_BasicBook build() {
            return new K2558859_BasicBook(bookId, title, author, category, isbn, metadata);
        }
    }
}
