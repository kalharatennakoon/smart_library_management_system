package model.book.decorator;

import model.book.K2558859_Book;

/**
 * K2558859_FeaturedDecorator - Concrete decorator for marking books as "Featured".
 * Adds a "Featured" tag to the book's description.
 */
public class K2558859_FeaturedDecorator extends K2558859_BookDecorator {

    /**
     * Constructor for K2558859_FeaturedDecorator.
     * @param decoratedBook The book to be decorated as featured
     */
    public K2558859_FeaturedDecorator(K2558859_Book decoratedBook) {
        super(decoratedBook);
    }

    /**
     * Enhances the book description with a "Featured" tag.
     * @return The decorated description with Featured label
     */
    @Override
    public String getDescription() {
        return decoratedBook.getDescription() + " [FEATURED]";
    }
}
