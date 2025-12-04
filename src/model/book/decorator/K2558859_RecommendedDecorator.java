package model.book.decorator;

import model.book.K2558859_Book;

// K2558859_RecommendedDecorator - Concrete decorator for marking books as "Recommended"
public class K2558859_RecommendedDecorator extends K2558859_BookDecorator {

    // Constructor for K2558859_RecommendedDecorator
    public K2558859_RecommendedDecorator(K2558859_Book decoratedBook) {
        super(decoratedBook);
    }

    // Enhances the book description with a "Recommended" tag
    @Override
    public String getDescription() {
        return decoratedBook.getDescription() + " [RECOMMENDED]";
    }
}
