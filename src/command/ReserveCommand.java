package command;

import model.book.Book;
import model.user.User;
import exception.LibraryException;

/**
 * Concrete command for reserving a book.
 * Encapsulates the reserve action as a command object.
 */
public class ReserveCommand implements Command {
    private Book book;
    private User user;

    /**
     * Constructor for ReserveCommand.
     * @param book The book to be reserved
     * @param user The user reserving the book
     */
    public ReserveCommand(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    /**
     * Executes the reserve command.
     * Delegates to the user's reserveBook method.
     */
    @Override
    public void execute() {
        try {
            user.reserveBook(book);
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}