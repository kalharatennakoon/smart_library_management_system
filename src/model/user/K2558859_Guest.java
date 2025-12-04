package model.user;

import model.user.fines.K2558859_FineStrategy;
import model.user.fines.K2558859_GuestFineStrategy;

// K2558859_Guest class - Concrete user type representing a guest member
public class K2558859_Guest extends K2558859_User {

    // Constructor for K2558859_Guest
    public K2558859_Guest(String userId, String name, String email, String contactNumber) {
        super(userId, name, email, contactNumber);
    }

    // Returns the borrow period in days for guests
    @Override
    public int getBorrowPeriodInDays() {
        return 7; // 7 days loan period for guests
    }

    // Returns the fine calculation strategy for guests
    @Override
    public K2558859_FineStrategy getFineStrategy() {
        return new K2558859_GuestFineStrategy();
    }

    // Gets the maximum number of books a guest can borrow simultaneously
    @Override
    public int getMaxBorrowCapacity() {
        return 3; // Guests can borrow up to 3 books
    }
}
