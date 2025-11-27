package service.notification;

import model.book.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * NotificationService - Concrete implementation of Subject for Observer Pattern.
 * Manages notifications for book-related events (due dates, overdue books, reservations).
 * Maintains a list of observers and notifies them when events occur.
 */
public class NotificationService implements Subject {
    private List<Observer> observers;
    private Book currentBook;
    private String currentMessage;

    /**
     * Constructor for NotificationService.
     * Initializes the observer list.
     */
    public NotificationService() {
        this.observers = new ArrayList<>();
    }

    /**
     * Registers an observer to receive notifications.
     * @param observer The observer to be registered
     */
    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer registered: " + observer.getClass().getSimpleName());
        }
    }

    /**
     * Removes an observer from the notification list.
     * @param observer The observer to be removed
     */
    @Override
    public void removeObserver(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("Observer removed: " + observer.getClass().getSimpleName());
        }
    }

    /**
     * Notifies all registered observers of the current notification.
     * Each observer receives the current book and message.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(currentBook, currentMessage);
        }
    }

    /**
     * Adds a notification and immediately notifies all observers.
     * This is the main method for sending notifications.
     * @param notification The notification message to send
     */
    public void addNotification(String notification) {
        this.currentMessage = notification;
        System.out.println("\n[Notification System] Broadcasting: " + notification);
        notifyObservers();
    }

    /**
     * Sends a book-specific notification to all observers.
     * @param book The book related to the notification
     * @param message The notification message
     */
    public void sendBookNotification(Book book, String message) {
        this.currentBook = book;
        this.currentMessage = message;
        System.out.println("\n[Notification System] Broadcasting about '" + book.getTitle() + "': " + message);
        notifyObservers();
    }

    /**
     * Sends a due date reminder to observers.
     * @param book The book that is due soon
     * @param daysUntilDue Number of days until the book is due
     */
    public void sendDueDateReminder(Book book, int daysUntilDue) {
        String message = "Reminder: Book '" + book.getTitle() + "' is due in " + daysUntilDue + " days.";
        sendBookNotification(book, message);
    }

    /**
     * Sends an overdue notification to observers.
     * @param book The book that is overdue
     * @param daysOverdue Number of days the book is overdue
     */
    public void sendOverdueNotification(Book book, int daysOverdue) {
        String message = "Alert: Book '" + book.getTitle() + "' is overdue by " + daysOverdue + " days.";
        sendBookNotification(book, message);
    }

    /**
     * Sends a reservation available notification to observers.
     * @param book The book that is now available for the reservation holder
     */
    public void sendReservationAvailableNotification(Book book) {
        String message = "Your reserved book '" + book.getTitle() + "' is now available for pickup!";
        sendBookNotification(book, message);
    }

    /**
     * Gets the current number of registered observers.
     * @return The count of registered observers
     */
    public int getObserverCount() {
        return observers.size();
    }

    /**
     * Clears all registered observers.
     * Useful for testing or resetting the notification system.
     */
    public void clearObservers() {
        observers.clear();
        System.out.println("All observers have been cleared.");
    }
}
