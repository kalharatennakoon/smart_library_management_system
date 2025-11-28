package model.user;

import model.user.fines.FineStrategy;
import model.user.fines.FacultyFineStrategy;

/**
 * Faculty class - Concrete user type representing a faculty member.
 * Faculty members have:
 * - Borrow limit: 10 books
 * - Loan period: 30 days
 * - Fine rate: LKR 20/day
 */
public class Faculty extends User {

    /**
     * Constructor for Faculty.
     * @param userId Unique identifier for the faculty member
     * @param name Name of the faculty member
     * @param email Email address of the faculty member
     * @param contactNumber Contact number of the faculty member
     */
    public Faculty(String userId, String name, String email, String contactNumber) {
        super(userId, name, email, contactNumber);
    }

    /**
     * Returns the borrow period in days for faculty.
     * @return Loan period in days (30 days for faculty)
     */
    @Override
    public int getBorrowPeriodInDays() {
        return 30; // 30 days loan period for faculty
    }

    /**
     * Returns the fine calculation strategy for faculty.
     * @return FacultyFineStrategy instance (LKR 20/day)
     */
    @Override
    public FineStrategy getFineStrategy() {
        return new FacultyFineStrategy();
    }

    /**
     * Gets the maximum number of books a faculty member can borrow simultaneously.
     * NOTE: This value (10) is an assumption.
     * @return Maximum book borrowing capacity (10 books)
     */
    @Override
    public int getMaxBorrowCapacity() {
        return 10; // Faculty can borrow up to 10 books
    }
}