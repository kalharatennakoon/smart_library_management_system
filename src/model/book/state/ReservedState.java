package model.book.state;

import model.book.Book;
import model.user.User;
import model.borrow.BorrowRecord;
import java.time.LocalDate;
import java.util.List;

/**
 * ReservedState - Concrete state representing a reserved book.
 * In this state, books can be returned (by the current borrower), but cannot be borrowed or reserved again.
 */
public class ReservedState implements BookState {

    /**
     * Cannot borrow a book that is reserved.
     * The book must be returned first, then the reservation holder can borrow it.
     */
    @Override
    public void borrow(Book book, User user) {
        System.out.println("Error: Book '" + book.getTitle() + "' is currently reserved and cannot be borrowed.");
    }

    /**
     * Allows returning when the book is in reserved state (still borrowed but has a reservation).
     * Transitions the book to AvailableState and notifies the reservation holder.
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
        // Note: In a complete system, this would trigger notification to the reservation holder
        book.setAvailabilityStatus(new AvailableState());
        System.out.println("Notification: Reserved book is now available for pickup.");
    }

    /**
     * Cannot reserve a book that is already reserved.
     */
    @Override
    public void reserve(Book book, User user) {
        System.out.println("Error: Book '" + book.getTitle() + "' is already reserved by another user.");
    }

    @Override
    public String getStateName() {
        return "Reserved";
    }
}