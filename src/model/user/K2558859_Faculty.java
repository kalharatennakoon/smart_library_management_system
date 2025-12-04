package model.user;

import model.user.fines.K2558859_FineStrategy;
import model.user.fines.K2558859_FacultyFineStrategy;

// K2558859_Faculty class
public class K2558859_Faculty extends K2558859_User {

    // Constructor for K2558859_Faculty
    public K2558859_Faculty(String userId, String name, String email, String contactNumber) {
        super(userId, name, email, contactNumber);
    }

    // Returns the borrow period in days for faculty
    @Override
    public int getBorrowPeriodInDays() {
        return 30;
    }

    // Returns the fine calculation strategy for faculty
    @Override
    public K2558859_FineStrategy getFineStrategy() {
        return new K2558859_FacultyFineStrategy();
    }

    // Gets the maximum number of books a faculty member can borrow simultaneously
    @Override
    public int getMaxBorrowCapacity() {
        return 10;
    }
}
