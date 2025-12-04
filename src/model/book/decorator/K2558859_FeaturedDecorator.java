package model.book.decorator;

import model.book.K2558859_Book;

// K2558859_FeaturedDecorator - Concrete decorator for marking books as "Featured"
public class K2558859_FeaturedDecorator extends K2558859_BookDecorator {

    // Constructor for K2558859_FeaturedDecorator
    public K2558859_FeaturedDecorator(K2558859_Book decoratedBook) {
        super(decoratedBook);
    }

    // Enhances the book description with a "Featured" tag
    @Override
    public String getDescription() {
        return decoratedBook.getDescription() + " [FEATURED]";
    }
}
