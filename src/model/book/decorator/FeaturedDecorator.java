package model.book.decorator;

import model.book.Book;

/**
 * FeaturedDecorator - Concrete decorator for marking books as "Featured".
 * Adds a "Featured" tag to the book's description.
 */
public class FeaturedDecorator extends BookDecorator {

    /**
     * Constructor for FeaturedDecorator.
     * @param decoratedBook The book to be decorated as featured
     */
    public FeaturedDecorator(Book decoratedBook) {
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