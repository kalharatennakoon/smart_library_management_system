package model.book.state;

import model.book.K2558859_Book;
import model.user.K2558859_User;
import model.borrow.K2558859_BorrowRecord;
import model.reservation.K2558859_Reservation;
import service.K2558859_LibraryManagementSystem;
import java.time.LocalDate;
import java.util.List;

// K2558859_BorrowedState - Concrete state representing a borrowed book
public class K2558859_BorrowedState implements K2558859_BookState {

    // Cannot borrow a book that is already borrowed
    @Override
    public void borrow(K2558859_Book book, K2558859_User user) {
        System.out.println("Error: Book '" + book.getTitle() + "' is already borrowed and unavailable.");
    }

    // Allows returning when the book is borrowed
    @Override
    public void returnBook(K2558859_Book book) {
        // The K2558859_User class handles updating the borrow record.
        // This state's only job is to transition the book's state.
        // Transition to K2558859_AvailableState
        book.setState(new K2558859_AvailableState());
    }

    // Allows reserving a borrowed book
    @Override
    public void reserve(K2558859_Book book, K2558859_User user) {
        // Auto-generate reservation ID
        String reservationId = "RES-" + System.currentTimeMillis();
        K2558859_Reservation reservation = new K2558859_Reservation(
            reservationId, book, user, LocalDate.now());
        
        user.addReservation(reservation);
        
        // Add to library system's reservations list
        K2558859_LibraryManagementSystem library = K2558859_LibraryManagementSystem.getInstance();
        if (library != null) {
            library.getReservations().add(reservation);
        }
        
        // Transition to K2558859_ReservedState
        book.setState(new K2558859_ReservedState());
        System.out.println("Book '" + book.getTitle() + "' reserved successfully for " + user.getName() + ".");
    }

    @Override
    public String getStateName() {
        return "Borrowed";
    }
}
