package model.user;

import model.user.fines.FineStrategy;
import model.user.fines.GuestFineStrategy;

/**
 * Guest class - Concrete user type representing a guest member.
 * Guests have:
 * - Borrow limit: 2 books
 * - Loan period: 7 days
 * - Fine rate: LKR 100/day
 */
public class Guest extends User {
    private int borrowLimit;

    /**
     * Constructor for Guest.
     * @param userId Unique identifier for the guest
     * @param name Name of the guest
     * @param email Email address of the guest
     * @param contactNumber Contact number of the guest
     */
    public Guest(String userId, String name, String email, String contactNumber) {
        super(userId, name, email, contactNumber);
        this.borrowLimit = 2; // Guests can borrow up to 2 books
    }

    /**
     * Returns the borrow limit for guests.
     * Also represents the loan period in days (7 days for guests).
     * @return Maximum number of days for borrowing
     */
    @Override
    public int getBorrowLimit() {
        return 7; // 7 days loan period for guests
    }

    /**
     * Returns the fine calculation strategy for guests.
     * @return GuestFineStrategy instance (LKR 100/day)
     */
    @Override
    public FineStrategy getFineStrategy() {
        return new GuestFineStrategy();
    }

    /**
     * Gets the maximum number of books a guest can borrow simultaneously.
     * @return Maximum book borrowing capacity
     */
    public int getMaxBorrowCapacity() {
        return borrowLimit;
    }
}