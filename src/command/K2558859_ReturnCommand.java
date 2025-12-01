package command;

import model.user.K2558859_User;
import model.book.K2558859_Book;
import exception.LibraryException;

/**
 * K2558859_ReturnCommand - Concrete command for returning a book.
 * Implements the Command Pattern.
 */
public class K2558859_ReturnCommand implements K2558859_Command {
    private K2558859_User user;
    private K2558859_Book book;

    /**
     * Constructor for K2558859_ReturnCommand.
     * @param user The user returning the book
     * @param book The book to be returned
     */
    public K2558859_ReturnCommand(K2558859_User user, K2558859_Book book) {
        this.user = user;
        this.book = book;
    }

    /**
     * Executes the return command.
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
