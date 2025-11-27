package model.reservation;

import model.book.Book;
import model.user.User;
import java.time.LocalDate;
import java.util.Random;

/**
 * Reservation class represents a book reservation made by a user.
 * Tracks reservation details and handles user notifications.
 */
public class Reservation {
    private String reservationId;
    private Book book;
    private User user;
    private LocalDate reservationDate;
    private boolean isNotified;

    /**
     * Constructor for Reservation.
     * @param reservationId Unique identifier for the reservation
     * @param book The book being reserved
     * @param user The user making the reservation
     * @param reservationDate The date when the reservation was made
     */
    public Reservation(String reservationId, Book book, User user, LocalDate reservationDate) {
        this.reservationId = reservationId;
        this.book = book;
        this.user = user;
        this.reservationDate = reservationDate;
        this.isNotified = false;
    }

    private String generateReservationId() {
        return "R" + String.format("%04d", new Random().nextInt(10000));
    }

    // Getters
    public String getReservationId() {
        return reservationId;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public boolean isNotified() {
        return isNotified;
    }

    /**
     * Notifies the user that their reserved book is available.
     * Uses the Observer Pattern through the notification system.
     */
    public void notifyUser() {
        if (!isNotified) {
            System.out.println("Notification sent to " + user.getName() + 
                             " (" + user.getEmail() + "): Your reserved book '" + 
                             book.getTitle() + "' is now available for pickup.");
            isNotified = true;
        } else {
            System.out.println("User " + user.getName() + " has already been notified about this reservation.");
        }
    }

    /**
     * Cancels the reservation.
     * Removes the reservation from the system and updates the book state if needed.
     */
    public void cancel() {
        System.out.println("Reservation " + reservationId + " for book '" + 
                         book.getTitle() + "' by " + user.getName() + " has been cancelled.");
        
        // Note: The actual removal from user's reservation list 
        // should be handled by the User class or LibraryManagementSystem
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", book=" + book.getTitle() +
                ", user=" + user.getName() +
                ", reservationDate=" + reservationDate +
                ", isNotified=" + isNotified +
                '}';
    }
}