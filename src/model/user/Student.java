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

    /**
     * Constructor for Student.
     * @param userId Unique identifier for the student
     * @param name Name of the student
     * @param email Email address of the student
     * @param contactNumber Contact number of the student
     */
    public Student(String userId, String name, String email, String contactNumber) {
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
     * @return StudentFineStrategy instance (LKR 50/day)
     */
    @Override
    public FineStrategy getFineStrategy() {
        return new StudentFineStrategy();
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