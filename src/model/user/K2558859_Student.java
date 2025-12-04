package model.user;

import model.user.fines.K2558859_FineStrategy;
import model.user.fines.K2558859_StudentFineStrategy;

// K2558859_Student class - Concrete user type representing a student member
public class K2558859_Student extends K2558859_User {

    // Constructor for K2558859_Student
    public K2558859_Student(String userId, String name, String email, String contactNumber) {
        super(userId, name, email, contactNumber);
    }

    // Returns the borrow period in days for students
    @Override
    public int getBorrowPeriodInDays() {
        return 14; // 14 days loan period for students
    }

    // Returns the fine calculation strategy for students
    @Override
    public K2558859_FineStrategy getFineStrategy() {
        return new K2558859_StudentFineStrategy();
    }

    // Gets the maximum number of books a student can borrow simultaneously
    @Override
    public int getMaxBorrowCapacity() {
        return 5; // Students can borrow up to 5 books
    }
}
