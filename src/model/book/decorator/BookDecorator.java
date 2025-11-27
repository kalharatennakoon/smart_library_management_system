package model.book.decorator;

import model.book.Book;
import model.book.state.BookState;
import model.borrow.BorrowRecord;
import model.user.User;
import java.util.List;

/**
 * Abstract BookDecorator class for Decorator Pattern.
 * Wraps a Book object and delegates all operations to it.
 * Subclasses can enhance the book's description with additional features.
 */
public abstract class BookDecorator extends Book {
    protected Book decoratedBook;

    /**
     * Constructor for BookDecorator.
     * @param decoratedBook The book object to be decorated
     */
    public BookDecorator(Book decoratedBook) {
        super(decoratedBook.getBookId(), 
              decoratedBook.getTitle(), 
              decoratedBook.getAuthor(), 
              decoratedBook.getCategory(), 
              decoratedBook.getISBN());
        this.decoratedBook = decoratedBook;
    }

    /**
     * Delegates borrow operation to the decorated book.
     */
    @Override
    public void borrow(User user) {
        decoratedBook.borrow(user);
    }

    /**
     * Delegates return operation to the decorated book.
     */
    @Override
    public void returnBook() {
        decoratedBook.returnBook();
    }

    /**
     * Delegates reserve operation to the decorated book.
     */
    @Override
    public void reserve(User user) {
        decoratedBook.reserve(user);
    }

    /**
     * Gets the description from the decorated book.
     * Subclasses will override this to add decoration tags.
     */
    @Override
    public String getDescription() {
        return decoratedBook.getDescription();
    }

    /**
     * Override getters to delegate to decorated book for accurate state.
     */
    @Override
    public BookState getAvailabilityStatus() {
        return decoratedBook.getAvailabilityStatus();
    }

    @Override
    public List<BorrowRecord> getBorrowHistory() {
        return decoratedBook.getBorrowHistory();
    }

    @Override
    public void setAvailabilityStatus(BookState state) {
        decoratedBook.setAvailabilityStatus(state);
    }

    @Override
    public void addBorrowRecord(BorrowRecord record) {
        decoratedBook.addBorrowRecord(record);
    }
}