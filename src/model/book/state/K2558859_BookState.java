package model.book.state;

import model.book.K2558859_Book;
import model.user.K2558859_User;

// K2558859_BookState interface for State Pattern
public interface K2558859_BookState {
    
    // Handles the borrow operation based on the current state
    void borrow(K2558859_Book book, K2558859_User user);
    
    // Handles the return operation based on the current state
    void returnBook(K2558859_Book book);
    
    // Handles the reserve operation based on the current state
    void reserve(K2558859_Book book, K2558859_User user);
    
    // Returns the name of the current state
    String getStateName();
}
