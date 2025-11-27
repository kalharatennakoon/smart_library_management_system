package model.user.fines;

import model.borrow.BorrowRecord;
import java.time.LocalDate;

/**
 * FineStrategy interface for Strategy Pattern.
 * Defines the contract for calculating overdue fines based on membership type.
 * Different user types (Student, Faculty, Guest) have different fine rates.
 */
public interface FineStrategy {
    
    /**
     * Calculates the fine for an overdue book.
     * @param borrowRecord The borrow record containing due date and return information
     * @param currentDate The current date to calculate fine against
     * @return The calculated fine amount in LKR (0 if not overdue)
     */
    double calculateFine(BorrowRecord borrowRecord, LocalDate currentDate);
}
