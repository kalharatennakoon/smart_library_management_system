package service.notification;

import model.book.K2558859_Book;
import model.user.K2558859_User;

// K2558859_UserNotificationObserver - Concrete observer for user notifications
public class K2558859_UserNotificationObserver implements K2558859_Observer {
    private K2558859_User user;

    // Constructor for K2558859_UserNotificationObserver
    public K2558859_UserNotificationObserver(K2558859_User user) {
        this.user = user;
    }

    // Receives and processes notification updates
    @Override
    public void update(K2558859_Book book, String message) {
        System.out.println("Notification to " + user.getName() + 
                         " (" + user.getEmail() + "): " + 
                         message + " - '" + book.getTitle() + "'");
    }

    // Gets the user associated with this observer
    public K2558859_User getUser() {
        return user;
    }
}
