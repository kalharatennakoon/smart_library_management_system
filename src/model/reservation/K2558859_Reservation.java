package model.reservation;

import model.book.K2558859_Book;
import model.user.K2558859_User;
import java.time.LocalDate;
import java.util.Random;

// K2558859_Reservation class represents a book reservation made by a user
public class K2558859_Reservation {
    private String reservationId;
    private K2558859_Book book;
    private K2558859_User user;
    private LocalDate reservationDate;
    private boolean isNotified;

    // Constructor for K2558859_Reservation
    public K2558859_Reservation(String reservationId, K2558859_Book book, K2558859_User user, LocalDate reservationDate) {
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

    public K2558859_Book getBook() {
        return book;
    }

    public K2558859_User getUser() {
        return user;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public boolean isNotified() {
        return isNotified;
    }

    // Notifies the user that their reserved book is available
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

    // Cancels the reservation
    public void cancel() {
        System.out.println("Reservation " + reservationId + " for book '" + 
                         book.getTitle() + "' by " + user.getName() + " has been cancelled.");
        
        // Note: The actual removal from user's reservation list 
        // should be handled by the K2558859_User class or K2558859_LibraryManagementSystem
    }

    @Override
    public String toString() {
        return "K2558859_Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", book=" + book.getTitle() +
                ", user=" + user.getName() +
                ", reservationDate=" + reservationDate +
                ", isNotified=" + isNotified +
                '}';
    }
}
