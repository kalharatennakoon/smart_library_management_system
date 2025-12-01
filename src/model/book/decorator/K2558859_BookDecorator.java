package model.book.decorator;

import model.book.K2558859_Book;
import model.book.state.K2558859_BookState;
import model.borrow.K2558859_BorrowRecord;
import model.user.K2558859_User;
import java.util.List;

/**
 * Abstract K2558859_BookDecorator class for Decorator Pattern.
 * Wraps a K2558859_Book object and delegates all operations to it.
 * Subclasses can enhance the book's description with additional features.
 */
public abstract class K2558859_BookDecorator extends K2558859_Book {
    protected K2558859_Book decoratedBook;

    /**
     * Constructor for K2558859_BookDecorator.
     * @param decoratedBook The book object to be decorated
     */
    public K2558859_BookDecorator(K2558859_Book decoratedBook) {
        super(decoratedBook.getBookId(), 
              decoratedBook.getTitle(), 
              decoratedBook.getAuthor(), 
              decoratedBook.getCategory(), 
              decoratedBook.getIsbn(),
              decoratedBook.getMetadata());
        this.decoratedBook = decoratedBook;
    }

    /**
     * Delegates borrow operation to the decorated book.
     */
    @Override
    public void borrow(K2558859_User user) {
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
    public void reserve(K2558859_User user) {
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
    public K2558859_BookState getAvailabilityStatus() {
        return decoratedBook.getAvailabilityStatus();
    }

    @Override
    public List<K2558859_BorrowRecord> getBorrowHistory() {
        return decoratedBook.getBorrowHistory();
    }

    @Override
    public void setState(K2558859_BookState state) {
        decoratedBook.setState(state);
    }

    @Override
    public void addBorrowRecord(K2558859_BorrowRecord record) {
        decoratedBook.addBorrowRecord(record);
    }

    @Override
    public List<K2558859_BorrowRecord> getBorrowHistoryInternal() {
        return decoratedBook.getBorrowHistoryInternal();
    }
}
