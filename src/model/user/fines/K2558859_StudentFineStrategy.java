package model.user.fines;

import model.borrow.K2558859_BorrowRecord;
import java.time.LocalDate;

// K2558859_StudentFineStrategy - Concrete strategy for calculating student fines
public class K2558859_StudentFineStrategy implements K2558859_FineStrategy {
    
    private static final double FINE_RATE_PER_DAY = 50.0; // LKR 50/day for students
    
    // Calculates the fine for a student's overdue book
    @Override
    public double calculateFine(K2558859_BorrowRecord borrowRecord, LocalDate currentDate) {
        if (!borrowRecord.isOverdue(currentDate)) {
            return 0.0;
        }
        
        long overdueDays = borrowRecord.getOverdueDays(currentDate);
        return overdueDays * FINE_RATE_PER_DAY;
    }
}
