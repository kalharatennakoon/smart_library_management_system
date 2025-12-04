package command;

import model.user.K2558859_User;
import model.book.K2558859_Book;
import exception.LibraryException;

// K2558859_ReserveCommand - Concrete command for reserving a book
public class K2558859_ReserveCommand implements K2558859_Command {
    private K2558859_User user;
    private K2558859_Book book;

    // Constructor for K2558859_ReserveCommand
    public K2558859_ReserveCommand(K2558859_User user, K2558859_Book book) {
        this.user = user;
        this.book = book;
    }

    // Executes the reserve command
    @Override
    public void execute() {
        try {
            user.reserveBook(book);
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
