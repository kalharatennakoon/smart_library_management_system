package model.user.fines;

import model.borrow.BorrowRecord;
import java.time.LocalDate;

/**
 * FacultyFineStrategy - Concrete strategy for calculating fines for faculty members.
 * Fine rate: LKR 20 per day for overdue books.
 */
public class FacultyFineStrategy implements FineStrategy {
    
    private static final double FINE_PER_DAY = 20.0;

    /**
     * Calculates fine for faculty members at LKR 20 per day.
     * @param borrowRecord The borrow record to calculate fine for
     * @param currentDate The current date for calculation
     * @return Fine amount in LKR (0 if not overdue)
     */
    @Override
    public double calculateFine(BorrowRecord borrowRecord, LocalDate currentDate) {
        if (!borrowRecord.isOverdue(currentDate)) {
            return 0.0;
        }
        
        long overdueDays = borrowRecord.getOverdueDays(currentDate);
        double fine = overdueDays * FINE_PER_DAY;
        
        return fine;
    }
}
