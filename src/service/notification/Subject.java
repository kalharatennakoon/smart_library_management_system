package service.notification;

/**
 * Subject interface for Observer Pattern.
 * Defines the contract for objects that can be observed.
 * Subjects maintain a list of observers and notify them of state changes.
 */
public interface Subject {
    
    /**
     * Registers an observer to receive notifications.
     * @param observer The observer to be registered
     */
    void registerObserver(Observer observer);
    
    /**
     * Removes an observer from the notification list.
     * @param observer The observer to be removed
     */
    void removeObserver(Observer observer);
    
    /**
     * Notifies all registered observers of a state change.
     */
    void notifyObservers();
}
