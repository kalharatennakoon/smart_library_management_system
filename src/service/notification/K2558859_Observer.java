package service.notification;

import model.book.K2558859_Book;

// K2558859_Observer interface for Observer Pattern
public interface K2558859_Observer {
    
    // Called when the subject's state changes
    void update(K2558859_Book book, String message);
}
