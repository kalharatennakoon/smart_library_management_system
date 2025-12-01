package model.book.state;

import model.book.K2558859_Book;
import model.user.K2558859_User;
import model.borrow.K2558859_BorrowRecord;
import java.time.LocalDate;
import java.util.List;

/**
 * K2558859_ReservedState - Concrete state representing a reserved book.
 * In this state, books can be returned (by the current borrower), but cannot be borrowed or reserved again.
 */
public class K2558859_ReservedState implements K2558859_BookState {

    /**
     * Cannot borrow a book that is reserved.
     * The book must be returned first, then the reservation holder can borrow it.
     */
    @Override
    public void borrow(K2558859_Book book, K2558859_User user) {
        System.out.println("Error: Book '" + book.getTitle() + "' is currently reserved and cannot be borrowed.");
    }

    /**
     * Allows returning when the book is in reserved state (still borrowed but has a reservation).
     * Transitions the book to K2558859_AvailableState and notifies the reservation holder.
     */
    @Override
    public void returnBook(K2558859_Book book) {
        // Transition to K2558859_AvailableState
        // Note: In a complete system, this would trigger notification to the reservation holder
        book.setState(new K2558859_AvailableState());
    }

    /**
     * Cannot reserve a book that is already reserved.
     */
    @Override
    public void reserve(K2558859_Book book, K2558859_User user) {
        System.out.println("Error: Book '" + book.getTitle() + "' is already reserved by another user.");
    }

    @Override
    public String getStateName() {
        return "Reserved";
    }
}
