package model.user;

import exception.LibraryException;
import model.book.K2558859_Book;
import model.borrow.K2558859_BorrowRecord;
import model.reservation.K2558859_Reservation;
import model.user.fines.K2558859_FineStrategy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Abstract K2558859_User class representing a library user
public abstract class K2558859_User {
    private String userId;
    private String name;
    private String email;
    private String contactNumber;
    private List<K2558859_BorrowRecord> borrowedBooks;
    private List<K2558859_Reservation> reservations;

    // Constructor for K2558859_User
    public K2558859_User(String userId, String name, String email, String contactNumber) {
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

    public List<K2558859_BorrowRecord> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    public List<K2558859_Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public int getActiveBorrowCount() {
        return (int) borrowedBooks.stream()
            .filter(record -> record.getReturnDate() == null)
            .count();
    }

    // Public Methods
    // Borrows a book for the user
    public void borrowBook(K2558859_Book book) throws LibraryException {
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

    // Returns a borrowed book
    public void returnBook(K2558859_Book book) throws LibraryException {
        // Find the active borrow record for this book
        K2558859_BorrowRecord recordToUpdate = borrowedBooks.stream()
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

    // Reserves a book for the user
    public void reserveBook(K2558859_Book book) throws LibraryException {
        // Check if user already has a reservation for this book
        boolean alreadyReserved = reservations.stream()
            .anyMatch(reservation -> reservation.getBook().getBookId().equals(book.getBookId()));
        
        if (alreadyReserved) {
            throw new LibraryException(name + " has already reserved this book.");
        }

        // Delegate to book's state pattern
        book.reserve(this);
    }

    // Adds a reservation to the user's reservation list
    public void addReservation(K2558859_Reservation reservation) {
        reservations.add(reservation);
    }

    // Cancels a reservation for a book
    public void cancelReservation(K2558859_Book book) throws LibraryException {
        // Find the reservation using a stream for consistency
        K2558859_Reservation toRemove = reservations.stream()
            .filter(reservation -> reservation.getBook().getBookId().equals(book.getBookId()))
            .findFirst()
            .orElseThrow(() -> new LibraryException("No reservation found for book '" + book.getTitle() + "'."));

        toRemove.cancel();
        reservations.remove(toRemove);
    }

    // Adds a borrow record to the user's borrowed books list
    public void addBorrowRecord(K2558859_BorrowRecord record) {
        borrowedBooks.add(record);
    }

    // Abstract methods to be implemented by subclasses (Strategy Pattern)
    
    // Gets the borrow period in days based on membership type
    public abstract int getBorrowPeriodInDays();

    // Gets the maximum number of books that can be borrowed simultaneously
    public abstract int getMaxBorrowCapacity();

    // Gets the fine calculation strategy based on membership type
    public abstract K2558859_FineStrategy getFineStrategy();

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
