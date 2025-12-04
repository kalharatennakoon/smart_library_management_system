package model.book.decorator;

import model.book.K2558859_Book;

// K2558859_SpecialEditionDecorator - Concrete decorator for marking books as "Special Edition"
public class K2558859_SpecialEditionDecorator extends K2558859_BookDecorator {

    // Constructor for K2558859_SpecialEditionDecorator
    public K2558859_SpecialEditionDecorator(K2558859_Book decoratedBook) {
        super(decoratedBook);
    }

    // Enhances the book description with a "Special Edition" tag
    @Override
    public String getDescription() {
        return decoratedBook.getDescription() + " [SPECIAL EDITION]";
    }
}
