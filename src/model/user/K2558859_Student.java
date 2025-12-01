package model.user;

import model.user.fines.K2558859_FineStrategy;
import model.user.fines.K2558859_StudentFineStrategy;

/**
 * K2558859_Student class - Concrete user type representing a student member.
 * Students have:
 * - Borrow limit: 5 books
 * - Loan period: 14 days
 * - Fine rate: LKR 50/day
 */
public class K2558859_Student extends K2558859_User {

    /**
     * Constructor for K2558859_Student.
     * @param userId Unique identifier for the student
     * @param name Name of the student
     * @param email Email address of the student
     * @param contactNumber Contact number of the student
     */
    public K2558859_Student(String userId, String name, String email, String contactNumber) {
        super(userId, name, email, contactNumber);
    }

    /**
     * Returns the borrow period in days for students.
     * @return Loan period in days (14 days for students)
     */
    @Override
    public int getBorrowPeriodInDays() {
        return 14; // 14 days loan period for students
    }

    /**
     * Returns the fine calculation strategy for students.
     * @return K2558859_StudentFineStrategy instance (LKR 50/day)
     */
    @Override
    public K2558859_FineStrategy getFineStrategy() {
        return new K2558859_StudentFineStrategy();
    }

    /**
     * Gets the maximum number of books a student can borrow simultaneously.
     * NOTE: This value (5) is an assumption.
     * @return Maximum book borrowing capacity (5 books)
     */
    @Override
    public int getMaxBorrowCapacity() {
        return 5; // Students can borrow up to 5 books
    }
}
