package command;

import model.book.Book;
import model.user.User;

/**
 * Concrete command for borrowing a book.
 * Encapsulates the borrow action as a command object.
 */
public class BorrowCommand implements Command {
    private Book book;
    private User user;

    /**
     * Constructor for BorrowCommand.
     * @param book The book to be borrowed
     * @param user The user borrowing the book
     */
    public BorrowCommand(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    /**
     * Executes the borrow command.
     * Delegates to the user's borrowBook method.
     */
    @Override
    public void execute() {
        try {
            user.borrowBook(book);
        } catch (exception.LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}