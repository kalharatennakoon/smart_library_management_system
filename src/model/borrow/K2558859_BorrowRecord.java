package model.borrow;

import model.book.K2558859_Book;
import model.user.K2558859_User;
import model.user.fines.K2558859_FineStrategy;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

// K2558859_BorrowRecord class represents a record of a book borrowing transaction
public class K2558859_BorrowRecord {
    private String recordId;
    private K2558859_Book book;
    private K2558859_User user;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    // Constructor for K2558859_BorrowRecord
    public K2558859_BorrowRecord(String recordId, K2558859_Book book, K2558859_User user, LocalDate borrowDate, LocalDate dueDate) {
        this.recordId = recordId;
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null; // Not returned yet
    }

    // Getters
    public String getRecordId() {
        return recordId;
    }

    public K2558859_Book getBook() {
        return book;
    }

    public K2558859_User getUser() {
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

    // Setters
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Public Methods
    // Calculates the fine for this borrow record using the Strategy Pattern
    public double calculateFine(K2558859_FineStrategy fineStrategy, LocalDate currentDate) {
        return fineStrategy.calculateFine(this, currentDate);
    }

    // Checks if the book is overdue
    public boolean isOverdue(LocalDate currentDate) {
        // If already returned, check if it was returned late
        if (returnDate != null) {
            return returnDate.isAfter(dueDate);
        }
        // If not returned, check if current date is past due date
        return currentDate.isAfter(dueDate);
    }

    public long getOverdueDays(LocalDate currentDate) {
        if (!isOverdue(currentDate)) {
            return 0;
        }
        
        LocalDate comparisonDate = (returnDate != null) ? returnDate : currentDate;
        return ChronoUnit.DAYS.between(dueDate, comparisonDate);
    }

    @Override
    public String toString() {
        return "K2558859_BorrowRecord{" +
                "recordId='" + recordId + '\'' +
                ", book=" + book.getTitle() +
                ", user=" + user.getName() +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + (returnDate != null ? returnDate : "Not returned") +
                '}';
    }
}
