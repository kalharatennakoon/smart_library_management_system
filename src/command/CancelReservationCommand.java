package command;

import model.book.Book;
import model.user.User;

/**
 * Concrete command for canceling a book reservation.
 * Encapsulates the cancel reservation action as a command object.
 */
public class CancelReservationCommand implements Command {
    private Book book;
    private User user;

    /**
     * Constructor for CancelReservationCommand.
     * @param book The book whose reservation is to be canceled
     * @param user The user canceling the reservation
     */
    public CancelReservationCommand(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    /**
     * Executes the cancel reservation command.
     * Delegates to the user's cancelReservation method.
     */
    @Override
    public void execute() {
        user.cancelReservation(book);
    }
}