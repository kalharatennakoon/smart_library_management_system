package model.borrow;

import model.book.Book;
import model.user.User;
import model.user.fines.FineStrategy;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * BorrowRecord class represents a record of a book borrowing transaction.
 * Tracks borrowing details and calculates fines using Strategy Pattern.
 */
public class BorrowRecord {
    private String recordId;
    private Book book;
    private User user;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    /**
     * Constructor for BorrowRecord.
     * @param recordId Unique identifier for the borrow record
     * @param book The book being borrowed
     * @param user The user borrowing the book
     * @param borrowDate The date when the book was borrowed
     * @param dueDate The date when the book should be returned
     */
    public BorrowRecord(String recordId, Book book, User user, LocalDate borrowDate, LocalDate dueDate) {
        this.recordId = recordId;
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null; // Not returned yet
    }

    private String generateBorrowRecordId() {
        return "BR" + String.format("%04d", new Random().nextInt(10000));
    }

    // Getters
    public String getRecordId() {
        return recordId;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the return date when the book is returned.
     * @param returnDate The date when the book was returned
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Calculates the fine for this borrow record using the Strategy Pattern.
     * @param fineStrategy The fine calculation strategy based on user type
     * @param currentDate The current date for fine calculation
     * @return The calculated fine amount in LKR
     */
    public double calculateFine(FineStrategy fineStrategy, LocalDate currentDate) {
        return fineStrategy.calculateFine(this, currentDate);
    }

    /**
     * Checks if the book is overdue.
     * @param currentDate The current date to check against
     * @return true if the book is overdue, false otherwise
     */
    public boolean isOverdue(LocalDate currentDate) {
        // If already returned, check if it was returned late
        if (returnDate != null) {
            return returnDate.isAfter(dueDate);
        }
        // If not returned, check if current date is past due date
        return currentDate.isAfter(dueDate);
    }

    /**
     * Gets the number of days overdue.
     * @param currentDate The current date to calculate from
     * @return Number of overdue days (0 if not overdue)
     */
    public long getOverdueDays(LocalDate currentDate) {
        if (!isOverdue(currentDate)) {
            return 0;
        }
        
        LocalDate comparisonDate = (returnDate != null) ? returnDate : currentDate;
        return ChronoUnit.DAYS.between(dueDate, comparisonDate);
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "recordId='" + recordId + '\'' +
                ", book=" + book.getTitle() +
                ", user=" + user.getName() +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + (returnDate != null ? returnDate : "Not returned") +
                '}';
    }
}
