package service.notification;

import model.book.K2558859_Book;

/**
 * K2558859_Subject interface for Observer Pattern.
 * Defines the contract for objects that can be observed.
 * Subjects maintain a list of observers and notify them of changes.
 */
public interface K2558859_Subject {
    
    /**
     * Registers an observer to receive notifications.
     * @param observer The observer to register
     */
    void registerObserver(K2558859_Observer observer);
    
    /**
     * Removes an observer from the notification list.
     * @param observer The observer to remove
     */
    void removeObserver(K2558859_Observer observer);
    
    /**
     * Notifies all registered observers of a change.
     * @param book The book related to the notification
     * @param message The notification message
     */
    void notifyObservers(K2558859_Book book, String message);
}
