package model.book.state;

import model.book.K2558859_Book;
import model.user.K2558859_User;
import model.borrow.K2558859_BorrowRecord;
import java.time.LocalDate;
import java.util.UUID;

// K2558859_AvailableState - Concrete state representing an available book
public class K2558859_AvailableState implements K2558859_BookState {

    // Allows borrowing when the book is available
    @Override
    public void borrow(K2558859_Book book, K2558859_User user) {
        // Calculate due date based on user type
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(user.getBorrowPeriodInDays());
        
        // Create borrow record
        K2558859_BorrowRecord record = new K2558859_BorrowRecord(
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
            service.K2558859_LibraryManagementSystem.getInstance().addBorrowRecord(record);
        } catch (Exception e) {
            // If singleton or static access is not available, this will be a no-op
        }
        
        // Transition to K2558859_BorrowedState
        book.setState(new K2558859_BorrowedState());
        System.out.println("\nBook '" + book.getTitle() + "' has been borrowed by " + user.getName());
    }

    // Cannot return a book that is already available
    @Override
    public void returnBook(K2558859_Book book) {
        System.out.println("Error: Book '" + book.getTitle() + "' is already available and cannot be returned.");
    }

    // Cannot reserve a book that is available - it should be borrowed directly
    @Override
    public void reserve(K2558859_Book book, K2558859_User user) {
        System.out.println("Error: Book '" + book.getTitle() + "' is available. Please borrow it directly instead of reserving.");
    }

    @Override
    public String getStateName() {
        return "Available";
    }
}
