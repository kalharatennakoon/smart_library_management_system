package model.book.state;

import model.book.Book;
import model.user.User;
import model.borrow.BorrowRecord;
import java.time.LocalDate;
import java.util.UUID;

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
        // Calculate due date based on user type
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(user.getBorrowPeriodInDays());
        
        // Create borrow record
        BorrowRecord record = new BorrowRecord(
            "BR-" + UUID.randomUUID().toString().substring(0, 8), // Use UUID for unique IDs
            book,
            user,
            borrowDate,
            dueDate
        );
        
        // Add the record to the user and the book's history
        user.addBorrowRecord(record);
        book.getBorrowHistoryInternal().add(record);
        // Add the record to the global borrowRecords list if possible
        try {
            service.LibraryManagementSystem.getInstance().addBorrowRecord(record);
        } catch (Exception e) {
            // If singleton or static access is not available, this will be a no-op
        }
        
        // Transition to BorrowedState
        book.setState(new BorrowedState());
        System.out.println("\nBook '" + book.getTitle() + "' has been borrowed by " + user.getName());
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