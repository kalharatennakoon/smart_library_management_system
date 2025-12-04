package service.notification;

import model.book.K2558859_Book;
import java.util.ArrayList;
import java.util.List;

// K2558859_NotificationService class implements Observer Pattern (Subject)
public class K2558859_NotificationService implements K2558859_Subject {
    private List<K2558859_Observer> observers;

    // Constructor for K2558859_NotificationService
    public K2558859_NotificationService() {
        this.observers = new ArrayList<>();
    }

    // Registers an observer
    @Override
    public void registerObserver(K2558859_Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer registered for notifications.");
        }
    }

    // Removes an observer
    @Override
    public void removeObserver(K2558859_Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("Observer removed from notifications.");
        }
    }

    // Notifies all observers about a book event
    @Override
    public void notifyObservers(K2558859_Book book, String message) {
        for (K2558859_Observer observer : observers) {
            observer.update(book, message);
        }
    }
}
