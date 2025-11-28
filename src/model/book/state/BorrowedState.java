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
        // The User class handles updating the borrow record.
        // This state's only job is to transition the book's state.
        // Transition to AvailableState
        book.setState(new AvailableState());
    }

    /**
     * Allows reserving a borrowed book.
     * Transitions the book to ReservedState.
     */
    @Override
    public void reserve(Book book, User user) {
        System.out.println("Book '" + book.getTitle() + "' has been reserved by " + user.getName());
        
        // Transition to ReservedState
        book.setState(new ReservedState());
    }

    @Override
    public String getStateName() {
        return "Borrowed";
    }
}