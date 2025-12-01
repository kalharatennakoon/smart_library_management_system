package model.user;

import model.user.fines.K2558859_FineStrategy;
import model.user.fines.K2558859_GuestFineStrategy;

/**
 * K2558859_Guest class - Concrete user type representing a guest member.
 * Guests have:
 * - Borrow limit: 3 books
 * - Loan period: 7 days
 * - Fine rate: LKR 100/day
 */
public class K2558859_Guest extends K2558859_User {

    /**
     * Constructor for K2558859_Guest.
     * @param userId Unique identifier for the guest
     * @param name Name of the guest
     * @param email Email address of the guest
     * @param contactNumber Contact number of the guest
     */
    public K2558859_Guest(String userId, String name, String email, String contactNumber) {
        super(userId, name, email, contactNumber);
    }

    /**
     * Returns the borrow period in days for guests.
     * @return Loan period in days (7 days for guests)
     */
    @Override
    public int getBorrowPeriodInDays() {
        return 7; // 7 days loan period for guests
    }

    /**
     * Returns the fine calculation strategy for guests.
     * @return K2558859_GuestFineStrategy instance (LKR 100/day)
     */
    @Override
    public K2558859_FineStrategy getFineStrategy() {
        return new K2558859_GuestFineStrategy();
    }

    /**
     * Gets the maximum number of books a guest can borrow simultaneously.
     * NOTE: This value (3) is an assumption.
     * @return Maximum book borrowing capacity (3 books)
     */
    @Override
    public int getMaxBorrowCapacity() {
        return 3; // Guests can borrow up to 3 books
    }
}
