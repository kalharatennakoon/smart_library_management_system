package model.book.state;

import model.book.K2558859_Book;
import model.user.K2558859_User;

/**
 * K2558859_BookState interface for State Pattern.
 * Defines the contract for different availability states of a book.
 * Each state implements different behavior for borrow, return, and reserve operations.
 */
public interface K2558859_BookState {
    
    /**
     * Handles the borrow operation based on the current state.
     * @param book The book being borrowed
     * @param user The user borrowing the book
     */
    void borrow(K2558859_Book book, K2558859_User user);
    
    /**
     * Handles the return operation based on the current state.
     * @param book The book being returned
     */
    void returnBook(K2558859_Book book);
    
    /**
     * Handles the reserve operation based on the current state.
     * @param book The book being reserved
     * @param user The user reserving the book
     */
    void reserve(K2558859_Book book, K2558859_User user);
    
    /**
     * Returns the name of the current state.
     * @return String representation of the state (e.g., "Available", "Borrowed", "Reserved")
     */
    String getStateName();
}
