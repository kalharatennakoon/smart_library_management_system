package command;

import model.user.K2558859_User;
import model.book.K2558859_Book;
import exception.LibraryException;

/**
 * K2558859_CancelReservationCommand - Concrete command for cancelling a reservation.
 * Implements the Command Pattern.
 */
public class K2558859_CancelReservationCommand implements K2558859_Command {
    private K2558859_User user;
    private K2558859_Book book;

    /**
     * Constructor for K2558859_CancelReservationCommand.
     * @param user The user cancelling the reservation
     * @param book The book whose reservation should be cancelled
     */
    public K2558859_CancelReservationCommand(K2558859_User user, K2558859_Book book) {
        this.user = user;
        this.book = book;
    }

    /**
     * Executes the cancel reservation command.
     */
    @Override
    public void execute() {
        try {
            user.cancelReservation(book);
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
