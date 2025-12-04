package model.user;

import model.user.fines.K2558859_FineStrategy;
import model.user.fines.K2558859_GuestFineStrategy;

// K2558859_Guest class - Concrete user type representing a guest member
public class K2558859_Guest extends K2558859_User {
    private static final int BORROW_PERIOD_DAYS = 7;
    private static final int MAX_BORROW_CAPACITY = 3;

    // Constructor for K2558859_Guest
    public K2558859_Guest(String userId, String name, String email, String contactNumber) {
        super(userId, name, email, contactNumber);
    }

    // Returns the borrow period in days for guests
    @Override
    public int getBorrowPeriodInDays() {
        return BORROW_PERIOD_DAYS;
    }

    // Returns the fine calculation strategy for guests
    @Override
    public K2558859_FineStrategy getFineStrategy() {
        return new K2558859_GuestFineStrategy();
    }

    // Gets the maximum number of books a guest can borrow simultaneously
    @Override
    public int getMaxBorrowCapacity() {
        return MAX_BORROW_CAPACITY;
    }
}
