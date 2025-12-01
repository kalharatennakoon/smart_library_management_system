package model.user.fines;

import model.borrow.K2558859_BorrowRecord;
import java.time.LocalDate;

/**
 * K2558859_StudentFineStrategy - Concrete strategy for calculating student fines.
 * Students are charged LKR 50 per day for overdue books.
 */
public class K2558859_StudentFineStrategy implements K2558859_FineStrategy {
    
    private static final double FINE_RATE_PER_DAY = 50.0; // LKR 50/day for students
    
    /**
     * Calculates the fine for a student's overdue book.
     * @param borrowRecord The borrow record to calculate fine for
     * @param currentDate The current date
     * @return Fine amount in LKR (LKR 50 per overdue day)
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
