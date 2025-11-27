package model.user;

import model.user.fines.FineStrategy;
import model.user.fines.StudentFineStrategy;

/**
 * Student class - Concrete user type representing a student member.
 * Students have:
 * - Borrow limit: 5 books
 * - Loan period: 14 days
 * - Fine rate: LKR 50/day
 */
public class Student extends User {
    private int borrowLimit;

    /**
     * Constructor for Student.
     * @param userId Unique identifier for the student
     * @param name Name of the student
     * @param email Email address of the student
     * @param contactNumber Contact number of the student
     */
    public Student(String userId, String name, String email, String contactNumber) {
        super(userId, name, email, contactNumber);
        this.borrowLimit = 5; // Students can borrow up to 5 books
    }

    /**
     * Returns the borrow limit for students.
     * Also represents the loan period in days (14 days for students).
     * @return Maximum number of books a student can borrow
     */
    @Override
    public int getBorrowLimit() {
        return 14; // 14 days loan period for students
    }

    /**
     * Returns the fine calculation strategy for students.
     * @return StudentFineStrategy instance (LKR 50/day)
     */
    @Override
    public FineStrategy getFineStrategy() {
        return new StudentFineStrategy();
    }

    /**
     * Gets the maximum number of books a student can borrow simultaneously.
     * @return Maximum book borrowing capacity
     */
    public int getMaxBorrowCapacity() {
        return borrowLimit;
    }
}