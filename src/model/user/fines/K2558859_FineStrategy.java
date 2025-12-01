package model.user.fines;

import model.borrow.K2558859_BorrowRecord;
import java.time.LocalDate;

/**
 * K2558859_FineStrategy interface for Strategy Pattern.
 * Defines the contract for calculating overdue fines based on membership type.
 * Different user types (Student, Faculty, Guest) have different fine rates.
 */
public interface K2558859_FineStrategy {
    
    /**
     * Calculates the fine for an overdue book.
     * @param borrowRecord The borrow record containing due date and return information
     * @param currentDate The current date to calculate fine against
     * @return The calculated fine amount in LKR (0 if not overdue)
     */
    double calculateFine(K2558859_BorrowRecord borrowRecord, LocalDate currentDate);
}
