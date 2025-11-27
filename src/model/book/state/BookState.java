package model.book.state;

import model.book.Book;
import model.user.User;

/**
 * BookState interface for State Pattern.
 * Defines the contract for different availability states of a book.
 * Each state implements different behavior for borrow, return, and reserve operations.
 */
public interface BookState {
    
    /**
     * Handles the borrow operation based on the current state.
     * @param book The book being borrowed
     * @param user The user borrowing the book
     */
    void borrow(Book book, User user);
    
    /**
     * Handles the return operation based on the current state.
     * @param book The book being returned
     */
    void returnBook(Book book);
    
    /**
     * Handles the reserve operation based on the current state.
     * @param book The book being reserved
     * @param user The user reserving the book
     */
    void reserve(Book book, User user);
    
    /**
     * Returns the name of the current state.
     * @return String representation of the state (e.g., "Available", "Borrowed", "Reserved")
     */
    String getStateName();
}