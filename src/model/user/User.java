package model.user;

import model.book.Book;
import model.borrow.BorrowRecord;
import model.reservation.Reservation;
import model.user.fines.FineStrategy;
import java.time.LocalDate;
import java.util.ArrayList;
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
        return new ArrayList<>(borrowedBooks);
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    /**
     * Borrows a book for the user.
     * Checks borrow limit before allowing the operation.
     * @param book The book to be borrowed
     */
    public void borrowBook(Book book) {
        // Check if user has reached borrow limit
        long activeBorrows = borrowedBooks.stream()
            .filter(record -> record.getReturnDate() == null)
            .count();
        
        if (activeBorrows >= getBorrowLimit()) {
            System.out.println("Error: " + name + " has reached the borrow limit of " + getBorrowLimit() + " books.");
            return;
        }

        // Delegate to book's state pattern
        book.borrow(this);
        
        // Add to user's borrowed books (will be done through addBorrowRecord)
    }

    /**
     * Returns a borrowed book.
     * @param book The book to be returned
     */
    public void returnBook(Book book) {
        // Check if user has actually borrowed this book and not returned it yet
        boolean hasBorrowed = borrowedBooks.stream()
            .anyMatch(record -> record.getBook().getBookId().equals(book.getBookId()) 
                             && record.getReturnDate() == null);
        
        if (!hasBorrowed) {
            System.out.println("Error: " + name + " has not borrowed this book or has already returned it.");
            return;
        }

        // Delegate to book's state pattern
        book.returnBook();
    }

    /**
     * Reserves a book for the user.
     * @param book The book to be reserved
     */
    public void reserveBook(Book book) {
        // Check if user already has a reservation for this book
        boolean alreadyReserved = reservations.stream()
            .anyMatch(reservation -> reservation.getBook().getBookId().equals(book.getBookId()));
        
        if (alreadyReserved) {
            System.out.println("Error: " + name + " has already reserved this book.");
            return;
        }

        // Delegate to book's state pattern
        book.reserve(this);
        
        // Create and add reservation
        Reservation reservation = new Reservation(
            "RES-" + System.currentTimeMillis(),
            book,
            this,
            LocalDate.now()
        );
        reservations.add(reservation);
    }

    /**
     * Cancels a reservation for a book.
     * @param book The book whose reservation should be cancelled
     */
    public void cancelReservation(Book book) {
        Reservation toRemove = null;
        
        for (Reservation reservation : reservations) {
            if (reservation.getBook().getBookId().equals(book.getBookId())) {
                toRemove = reservation;
                break;
            }
        }
        
        if (toRemove != null) {
            toRemove.cancel();
            reservations.remove(toRemove);
        } else {
            System.out.println("Error: No reservation found for book '" + book.getTitle() + "'.");
        }
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
     * Gets the borrow limit based on membership type.
     * @return Maximum number of books that can be borrowed simultaneously
     */
    public abstract int getBorrowLimit();

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