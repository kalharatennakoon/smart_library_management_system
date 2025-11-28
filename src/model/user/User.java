package model.user;

import exception.LibraryException;
import model.book.Book;
import model.borrow.BorrowRecord;
import model.reservation.Reservation;
import model.user.fines.FineStrategy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract User class representing a library user.
 * Uses Strategy Pattern for fine calculation based on membership type.
 * Subclasses: Student, Faculty, Guest.
 */
public abstract class User {
    private String userId;
    private String name;
    private String email;
    private String contactNumber;
    private List<BorrowRecord> borrowedBooks;
    private List<Reservation> reservations;

    /**
     * Constructor for User.
     * @param userId Unique identifier for the user
     * @param name Name of the user
     * @param email Email address of the user
     * @param contactNumber Contact number of the user
     */
    public User(String userId, String name, String email, String contactNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.borrowedBooks = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public List<BorrowRecord> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    /**
     * Borrows a book for the user.
     * Checks borrow limit before allowing the operation.
     * @param book The book to be borrowed
     */
    public void borrowBook(Book book) throws LibraryException {
        // Check if user has reached borrow limit
        long activeBorrows = borrowedBooks.stream()
            .filter(record -> record.getReturnDate() == null)
            .count();
        
        if (activeBorrows >= getMaxBorrowCapacity()) {
            throw new LibraryException(name + " has reached the borrow limit of " + getMaxBorrowCapacity() + " books.");
        }

        // Delegate to book's state pattern
        book.borrow(this);
        
        // Add to user's borrowed books (will be done through addBorrowRecord)
    }

    /**
     * Returns a borrowed book.
     * @param book The book to be returned
     */
    public void returnBook(Book book) throws LibraryException {
        // Find the active borrow record for this book
        BorrowRecord recordToUpdate = borrowedBooks.stream()
            .filter(record -> record.getBook().getBookId().equals(book.getBookId()) && record.getReturnDate() == null)
            .findFirst()
            .orElse(null);
        
        if (recordToUpdate == null) {
            throw new LibraryException(name + " has not borrowed this book or has already returned it.");
        }

        // Delegate to book's state pattern
        book.returnBook();

        // Mark the borrow record as returned
        recordToUpdate.setReturnDate(LocalDate.now());
        System.out.println("Book '" + book.getTitle() + "' returned by " + name + ".");
    }

    /**
     * Reserves a book for the user.
     * @param book The book to be reserved
     */
    public void reserveBook(Book book) throws LibraryException {
        // Check if user already has a reservation for this book
        boolean alreadyReserved = reservations.stream()
            .anyMatch(reservation -> reservation.getBook().getBookId().equals(book.getBookId()));
        
        if (alreadyReserved) {
            throw new LibraryException(name + " has already reserved this book.");
        }

        // Delegate to book's state pattern, which returns a reservation record on success
        // The book's state will call back to add the reservation to the user.
        book.reserve(this);
    }

    /**
     * Cancels a reservation for a book.
     * @param book The book whose reservation should be cancelled
     */
    public void cancelReservation(Book book) throws LibraryException {
        // Find the reservation using a stream for consistency
        Reservation toRemove = reservations.stream()
            .filter(reservation -> reservation.getBook().getBookId().equals(book.getBookId()))
            .findFirst()
            .orElseThrow(() -> new LibraryException("No reservation found for book '" + book.getTitle() + "'."));

        toRemove.cancel();
        reservations.remove(toRemove);
    }

    /**
     * Adds a borrow record to the user's borrowed books list.
     * Called by the book's state when borrowing is successful.
     * @param record The borrow record to add
     */
    public void addBorrowRecord(BorrowRecord record) {
        borrowedBooks.add(record);
    }

    /**
     * Gets the number of currently active (unreturned) borrowed books.
     * @return Count of active borrows
     */
    public int getActiveBorrowCount() {
        return (int) borrowedBooks.stream()
            .filter(record -> record.getReturnDate() == null)
            .count();
    }

    // Abstract methods to be implemented by subclasses (Strategy Pattern)
    
    /**
     * Gets the borrow period in days based on membership type.
     * @return Number of days a book can be borrowed
     */
    public abstract int getBorrowPeriodInDays();

    /**
     * Gets the maximum number of books that can be borrowed simultaneously.
     * @return Maximum borrowing capacity
     */
    public abstract int getMaxBorrowCapacity();

    /**
     * Gets the fine calculation strategy based on membership type.
     * @return FineStrategy instance for this user type
     */
    public abstract FineStrategy getFineStrategy();

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", membershipType='" + this.getClass().getSimpleName() + '\'' +
                ", activeBorrows=" + getActiveBorrowCount() +
                '}';
    }
}