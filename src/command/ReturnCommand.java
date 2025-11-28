package command;

import model.book.Book;
import model.user.User;
import exception.LibraryException;

/**
 * Concrete command for returning a book.
 * Encapsulates the return action as a command object.
 */
public class ReturnCommand implements Command {
    private Book book;
    private User user;

    /**
     * Constructor for ReturnCommand.
     * @param book The book to be returned
     * @param user The user returning the book
     */
    public ReturnCommand(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    /**
     * Executes the return command.
     * Delegates to the user's returnBook method.
     */
    @Override
    public void execute() {
        try {
            user.returnBook(book);
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}