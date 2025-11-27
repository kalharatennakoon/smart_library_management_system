package model.book.state;

import model.book.Book;
import model.user.User;
import model.borrow.BorrowRecord;
import java.time.LocalDate;

/**
 * AvailableState - Concrete state representing an available book.
 * In this state, books can be borrowed but not returned or reserved.
 */
public class AvailableState implements BookState {

    /**
     * Allows borrowing when the book is available.
     * Transitions the book to BorrowedState.
     */
    @Override
    public void borrow(Book book, User user) {
        System.out.println("Book '" + book.getTitle() + "' has been borrowed by " + user.getName());
        
        // Calculate due date based on user type
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(user.getBorrowLimit());
        
        // Create borrow record
        BorrowRecord record = new BorrowRecord(
            "BR-" + System.currentTimeMillis(),
            book,
            user,
            borrowDate,
            dueDate
        );
        
        // Add to book's history
        book.addBorrowRecord(record);
        
        // Transition to BorrowedState
        book.setAvailabilityStatus(new BorrowedState());
    }

    /**
     * Cannot return a book that is already available.
     */
    @Override
    public void returnBook(Book book) {
        System.out.println("Error: Book '" + book.getTitle() + "' is already available and cannot be returned.");
    }

    /**
     * Cannot reserve a book that is available - it should be borrowed directly.
     */
    @Override
    public void reserve(Book book, User user) {
        System.out.println("Error: Book '" + book.getTitle() + "' is available. Please borrow it directly instead of reserving.");
    }

    @Override
    public String getStateName() {
        return "Available";
    }
}