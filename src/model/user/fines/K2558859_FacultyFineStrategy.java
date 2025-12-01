package model.user.fines;

import model.borrow.K2558859_BorrowRecord;
import java.time.LocalDate;

/**
 * K2558859_FacultyFineStrategy - Concrete strategy for calculating faculty fines.
 * Faculty members are charged LKR 20 per day for overdue books.
 */
public class K2558859_FacultyFineStrategy implements K2558859_FineStrategy {
    
    private static final double FINE_RATE_PER_DAY = 20.0; // LKR 20/day for faculty
    
    /**
     * Calculates the fine for a faculty member's overdue book.
     * @param borrowRecord The borrow record to calculate fine for
     * @param currentDate The current date
     * @return Fine amount in LKR (LKR 20 per overdue day)
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
