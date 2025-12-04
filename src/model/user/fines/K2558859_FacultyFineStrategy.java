package model.user.fines;

import model.borrow.K2558859_BorrowRecord;
import java.time.LocalDate;

// K2558859_FacultyFineStrategy - Concrete strategy for calculating faculty fines
public class K2558859_FacultyFineStrategy implements K2558859_FineStrategy {
    
    private static final double FINE_RATE_PER_DAY = 20.0; // LKR 20/day for faculty
    
    // Calculates the fine for a faculty member's overdue book
    @Override
    public double calculateFine(K2558859_BorrowRecord borrowRecord, LocalDate currentDate) {
        if (!borrowRecord.isOverdue(currentDate)) {
            return 0.0;
        }
        
        long overdueDays = borrowRecord.getOverdueDays(currentDate);
        return overdueDays * FINE_RATE_PER_DAY;
    }
}
