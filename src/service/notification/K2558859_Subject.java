package service.notification;

import model.book.K2558859_Book;

// K2558859_Subject interface for Observer Pattern
public interface K2558859_Subject {
    
    // Registers an observer to receive notifications
    void registerObserver(K2558859_Observer observer);
    
    // Removes an observer from the notification list
    void removeObserver(K2558859_Observer observer);
    
    // Notifies all registered observers of a change
    void notifyObservers(K2558859_Book book, String message);
}
