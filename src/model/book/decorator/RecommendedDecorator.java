package model.book.decorator;

import model.book.Book;

/**
 * RecommendedDecorator - Concrete decorator for marking books as "Recommended".
 * Adds a "Recommended" tag to the book's description.
 */
public class RecommendedDecorator extends BookDecorator {

    /**
     * Constructor for RecommendedDecorator.
     * @param decoratedBook The book to be decorated as recommended
     */
    public RecommendedDecorator(Book decoratedBook) {
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