package model.book.decorator;

import model.book.K2558859_Book;

/**
 * K2558859_RecommendedDecorator - Concrete decorator for marking books as "Recommended".
 * Adds a "Recommended" tag to the book's description.
 */
public class K2558859_RecommendedDecorator extends K2558859_BookDecorator {

    /**
     * Constructor for K2558859_RecommendedDecorator.
     * @param decoratedBook The book to be decorated as recommended
     */
    public K2558859_RecommendedDecorator(K2558859_Book decoratedBook) {
        super(decoratedBook);
    }

    /**
     * Enhances the book description with a "Recommended" tag.
     * @return The decorated description with Recommended label
     */
    @Override
    public String getDescription() {
        return decoratedBook.getDescription() + " [RECOMMENDED]";
    }
}
