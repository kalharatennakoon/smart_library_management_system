package service.notification;

import model.book.K2558859_Book;

/**
 * K2558859_Observer interface for Observer Pattern.
 * Defines the contract for objects that want to be notified of changes.
 * Observers receive updates about books and notification messages.
 */
public interface K2558859_Observer {
    
    /**
     * Called when the subject's state changes.
     * @param book The book related to the notification
     * @param message The notification message (e.g., "Book is now available", "Book is overdue")
     */
    void update(K2558859_Book book, String message);
}
