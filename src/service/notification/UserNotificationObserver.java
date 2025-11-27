package service.notification;

import model.book.Book;
import model.user.User;

/**
 * UserNotificationObserver - Concrete implementation of Observer.
 * Represents a user who receives notifications about books.
 * Each user can be notified about due dates, overdue books, and reserved book availability.
 */
public class UserNotificationObserver implements Observer {
    private User user;

    /**
     * Constructor for UserNotificationObserver.
     * @param user The user who will receive notifications
     */
    public UserNotificationObserver(User user) {
        this.user = user;
    }

    /**
     * Called when a notification is sent from the subject.
     * Processes the notification for this specific user.
     * @param book The book related to the notification
     * @param message The notification message
     */
    @Override
    public void update(Book book, String message) {
        // Check if this notification is relevant to this user
        if (isRelevantToUser(book)) {
            displayNotification(book, message);
        }
    }

    /**
     * Checks if the notification about a book is relevant to this user.
     * A notification is relevant if the user has borrowed or reserved the book.
     * @param book The book in the notification
     * @return true if the notification is relevant, false otherwise
     */
    private boolean isRelevantToUser(Book book) {
        if (book == null) {
            return true; // General notifications
        }

        // Check if user has borrowed this book (and not returned it)
        boolean hasBorrowed = user.getBorrowedBooks().stream()
            .anyMatch(record -> record.getBook().getBookId().equals(book.getBookId()) 
                             && record.getReturnDate() == null);

        // Check if user has reserved this book
        boolean hasReserved = user.getReservations().stream()
            .anyMatch(reservation -> reservation.getBook().getBookId().equals(book.getBookId()));

        return hasBorrowed || hasReserved;
    }

    /**
     * Displays the notification to the user.
     * In a real system, this could send an email, SMS, or push notification.
     * @param book The book related to the notification
     * @param message The notification message
     */
    private void displayNotification(Book book, String message) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("NOTIFICATION TO: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("----------------------------------------------------------------------");
        
        if (book != null) {
            System.out.println("Book: " + book.getTitle());
            System.out.println("Status: " + book.getAvailabilityStatus().getStateName());
            System.out.println("----------------------------------------------------------------------");
        }
        
        System.out.println("Message: " + message);
        System.out.println("----------------------------------------------------------------------");
        System.out.println();
    }

    /**
     * Gets the user associated with this observer.
     * @return The user object
     */
    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "UserNotificationObserver{user=" + user.getName() + " (" + user.getUserId() + ")}";
    }
}
