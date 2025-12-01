package model.user.fines;

import model.borrow.K2558859_BorrowRecord;
import java.time.LocalDate;

/**
 * K2558859_GuestFineStrategy - Concrete strategy for calculating guest fines.
 * Guests are charged LKR 100 per day for overdue books.
 */
public class K2558859_GuestFineStrategy implements K2558859_FineStrategy {
    
    private static final double FINE_RATE_PER_DAY = 100.0; // LKR 100/day for guests
    
    /**
     * Calculates the fine for a guest's overdue book.
     * @param borrowRecord The borrow record to calculate fine for
     * @param currentDate The current date
     * @return Fine amount in LKR (LKR 100 per overdue day)
     */
    @Override
    public double calculateFine(K2558859_BorrowRecord borrowRecord, LocalDate currentDate) {
        if (!borrowRecord.isOverdue(currentDate)) {
            return 0.0;
        }
        
        long overdueDays = borrowRecord.getOverdueDays(currentDate);
        return overdueDays * FINE_RATE_PER_DAY;
    }
}
