package model.book.decorator;

import model.book.Book;

/**
 * SpecialEditionDecorator - Concrete decorator for marking books as "Special Edition".
 * Adds a "Special Edition" tag to the book's description.
 */
public class SpecialEditionDecorator extends BookDecorator {

    /**
     * Constructor for SpecialEditionDecorator.
     * @param decoratedBook The book to be decorated as special edition
     */
    public SpecialEditionDecorator(Book decoratedBook) {
        super(decoratedBook);
    }

    /**
     * Enhances the book description with a "Special Edition" tag.
     * @return The decorated description with Special Edition label
     */
    @Override
    public String getDescription() {
        return decoratedBook.getDescription() + " [SPECIAL EDITION]";
    }
}