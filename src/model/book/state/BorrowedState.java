package model.book.state;

import model.book.Book;
import model.user.User;
import model.borrow.BorrowRecord;
import java.time.LocalDate;
import java.util.List;

/**
 * BorrowedState - Concrete state representing a borrowed book.
 * In this state, books can be returned or reserved, but not borrowed again.
 */
public class BorrowedState implements BookState {

    /**
     * Cannot borrow a book that is already borrowed.
     */
    @Override
    public void borrow(Book book, User user) {
        System.out.println("Error: Book '" + book.getTitle() + "' is already borrowed and unavailable.");
    }

    /**
     * Allows returning when the book is borrowed.
     * Transitions the book to AvailableState.
     */
    @Override
    public void returnBook(Book book) {
        System.out.println("Book '" + book.getTitle() + "' has been returned successfully.");
        
        // Update the return date in the latest borrow record
        List<BorrowRecord> history = book.getBorrowHistory();
        if (!history.isEmpty()) {
            BorrowRecord latestRecord = history.get(history.size() - 1);
            latestRecord.setReturnDate(LocalDate.now());
            
            // Calculate and display fine if overdue
            double fine = latestRecord.calculateFine(
                latestRecord.getUser().getFineStrategy(), 
                LocalDate.now()
            );
            if (fine > 0) {
                System.out.println("Overdue fine: LKR " + fine);
            }
        }
        
        // Transition to AvailableState
        book.setAvailabilityStatus(new AvailableState());
    }

    /**
     * Allows reserving a borrowed book.
     * Transitions the book to ReservedState.
     */
    @Override
    public void reserve(Book book, User user) {
        System.out.println("Book '" + book.getTitle() + "' has been reserved by " + user.getName());
        
        // Transition to ReservedState
        book.setAvailabilityStatus(new ReservedState());
    }

    @Override
    public String getStateName() {
        return "Borrowed";
    }
}