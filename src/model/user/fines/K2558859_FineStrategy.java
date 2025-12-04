package model.user.fines;

import model.borrow.K2558859_BorrowRecord;
import java.time.LocalDate;

// K2558859_FineStrategy interface for Strategy Pattern
public interface K2558859_FineStrategy {
    
    // Calculates the fine for an overdue book
    double calculateFine(K2558859_BorrowRecord borrowRecord, LocalDate currentDate);
}
