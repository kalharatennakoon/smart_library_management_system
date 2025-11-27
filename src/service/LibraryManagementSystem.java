package service;

import model.book.Book;
import model.user.User;
import model.borrow.BorrowRecord;
import model.reservation.Reservation;
import model.report.Report;
import command.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * LibraryManagementSystem - Central service class managing all library operations.
 * Coordinates books, users, borrowing, reservations, and reports.
 * Uses Command Pattern for user actions and integrates all other patterns.
 */
public class LibraryManagementSystem {
    private List<Book> books;
    private List<User> users;
    private List<BorrowRecord> borrowRecords;
    private List<Reservation> reservations;
    private List<Report> reports;

    /**
     * Constructor for LibraryManagementSystem.
     * Initializes all data structures.
     */
    public LibraryManagementSystem() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.borrowRecords = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    // ==================== Book Management ====================

    /**
     * Adds a new book to the library system.
     * @param book The book to be added
     */
    public void addBook(Book book) {
        if (findBookById(book.getBookId()) != null) {
            System.out.println("Error: Book with ID " + book.getBookId() + " already exists.");
            return;
        }
        books.add(book);
        System.out.println("Book '" + book.getTitle() + "' added successfully.");
    }

    /**
     * Removes a book from the library system.
     * @param bookId The ID of the book to be removed
     */
    public void removeBook(String bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book with ID " + bookId + " not found.");
            return;
        }

        // Check if book is currently borrowed
        if (book.getAvailabilityStatus().getStateName().equals("Borrowed") ||
            book.getAvailabilityStatus().getStateName().equals("Reserved")) {
            System.out.println("Error: Cannot remove book '" + book.getTitle() + "' as it is currently " + 
                             book.getAvailabilityStatus().getStateName().toLowerCase() + ".");
            return;
        }

        books.remove(book);
        System.out.println("Book '" + book.getTitle() + "' removed successfully.");
    }

    /**
     * Updates book information.
     * @param book The book with updated information
     */
    public void updateBook(Book book) {
        Book existingBook = findBookById(book.getBookId());
        if (existingBook == null) {
            System.out.println("Error: Book with ID " + book.getBookId() + " not found.");
            return;
        }

        // Remove old and add updated book
        books.remove(existingBook);
        books.add(book);
        System.out.println("Book '" + book.getTitle() + "' updated successfully.");
    }

    // ==================== User Management ====================

    /**
     * Registers a new user in the library system.
     * @param user The user to be registered
     */
    public void registerUser(User user) {
        if (findUserById(user.getUserId()) != null) {
            System.out.println("Error: User with ID " + user.getUserId() + " already exists.");
            return;
        }
        users.add(user);
        System.out.println("User '" + user.getName() + "' registered successfully as " + 
                         user.getClass().getSimpleName() + ".");
    }

    /**
     * Removes a user from the library system.
     * @param userId The ID of the user to be removed
     */
    public void removeUser(String userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found.");
            return;
        }

        // Check if user has active borrows
        if (user.getActiveBorrowCount() > 0) {
            System.out.println("Error: Cannot remove user '" + user.getName() + 
                             "' as they have active borrowed books.");
            return;
        }

        users.remove(user);
        System.out.println("User '" + user.getName() + "' removed successfully.");
    }

    // ==================== Borrowing Operations (Using Command Pattern) ====================

    /**
     * Borrows a book for a user using Command Pattern.
     * @param bookId The ID of the book to borrow
     * @param userId The ID of the user borrowing the book
     */
    public void borrowBook(String bookId, String userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (book == null) {
            System.out.println("Error: Book with ID " + bookId + " not found.");
            return;
        }
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found.");
            return;
        }

        // Use Command Pattern
        Command borrowCommand = new BorrowCommand(book, user);
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(borrowCommand);
        invoker.pressButton();

        // Track borrow record if successful
        if (book.getAvailabilityStatus().getStateName().equals("Borrowed")) {
            List<BorrowRecord> userRecords = user.getBorrowedBooks();
            if (!userRecords.isEmpty()) {
                BorrowRecord latestRecord = userRecords.get(userRecords.size() - 1);
                borrowRecords.add(latestRecord);
            }
        }
    }

    /**
     * Returns a book using Command Pattern.
     * @param bookId The ID of the book to return
     * @param userId The ID of the user returning the book
     */
    public void returnBook(String bookId, String userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (book == null) {
            System.out.println("Error: Book with ID " + bookId + " not found.");
            return;
        }
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found.");
            return;
        }

        // Use Command Pattern
        Command returnCommand = new ReturnCommand(book, user);
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(returnCommand);
        invoker.pressButton();
    }

    /**
     * Reserves a book for a user using Command Pattern.
     * @param bookId The ID of the book to reserve
     * @param userId The ID of the user reserving the book
     */
    public void reserveBook(String bookId, String userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);

        if (book == null) {
            System.out.println("Error: Book with ID " + bookId + " not found.");
            return;
        }
        if (user == null) {
            System.out.println("Error: User with ID " + userId + " not found.");
            return;
        }

        // Use Command Pattern
        Command reserveCommand = new ReserveCommand(book, user);
        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(reserveCommand);
        invoker.pressButton();

        // Track reservation if successful
        List<Reservation> userReservations = user.getReservations();
        if (!userReservations.isEmpty()) {
            Reservation latestReservation = userReservations.get(userReservations.size() - 1);
            if (!reservations.contains(latestReservation)) {
                reservations.add(latestReservation);
            }
        }
    }

    // ==================== Report Generation ====================

    /**
     * Generates different types of reports for librarians.
     * @param reportType The type of report to generate 
     *                   ("Most Borrowed Books", "Active Borrowers", "Overdue Books")
     * @return The generated Report object
     */
    public Report generateReport(String reportType) {
        Report report = new Report("REP-" + System.currentTimeMillis(), reportType);

        switch (reportType) {
            case "Most Borrowed Books":
                generateMostBorrowedBooksReport(report);
                break;
            case "Active Borrowers":
                generateActiveBorrowersReport(report);
                break;
            case "Overdue Books":
                generateOverdueBooksReport(report);
                break;
            default:
                report.setContent("Unknown report type: " + reportType);
        }

        report.generate();
        reports.add(report);
        return report;
    }

    /**
     * Generates report for most borrowed books.
     */
    private void generateMostBorrowedBooksReport(Report report) {
        Map<String, Integer> borrowCounts = new HashMap<>();

        // Count borrows for each book
        for (BorrowRecord record : borrowRecords) {
            String bookId = record.getBook().getBookId();
            borrowCounts.put(bookId, borrowCounts.getOrDefault(bookId, 0) + 1);
        }

        // Sort by borrow count
        List<Map.Entry<String, Integer>> sortedBooks = borrowCounts.entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .collect(Collectors.toList());

        report.addLine("MOST BORROWED BOOKS REPORT");
        report.addLine("=".repeat(60));
        report.addLine(String.format("%-10s %-30s %-15s", "Book ID", "Title", "Borrow Count"));
        report.addLine("-".repeat(60));

        for (Map.Entry<String, Integer> entry : sortedBooks) {
            Book book = findBookById(entry.getKey());
            if (book != null) {
                report.addLine(String.format("%-10s %-30s %-15d", 
                    book.getBookId(), book.getTitle(), entry.getValue()));
            }
        }

        if (sortedBooks.isEmpty()) {
            report.addLine("No borrowing records found.");
        }
    }

    /**
     * Generates report for active borrowers.
     */
    private void generateActiveBorrowersReport(Report report) {
        report.addLine("ACTIVE BORROWERS REPORT");
        report.addLine("=".repeat(60));
        report.addLine(String.format("%-10s %-20s %-15s %-15s", 
            "User ID", "Name", "User Type", "Active Borrows"));
        report.addLine("-".repeat(60));

        for (User user : users) {
            int activeBorrows = user.getActiveBorrowCount();
            if (activeBorrows > 0) {
                report.addLine(String.format("%-10s %-20s %-15s %-15d", 
                    user.getUserId(), user.getName(), 
                    user.getClass().getSimpleName(), activeBorrows));
            }
        }

        if (users.stream().noneMatch(u -> u.getActiveBorrowCount() > 0)) {
            report.addLine("No active borrowers found.");
        }
    }

    /**
     * Generates report for overdue books.
     */
    private void generateOverdueBooksReport(Report report) {
        LocalDate today = LocalDate.now();
        
        report.addLine("OVERDUE BOOKS REPORT");
        report.addLine("=".repeat(80));
        report.addLine(String.format("%-10s %-25s %-20s %-12s %-10s", 
            "Book ID", "Title", "Borrower", "Due Date", "Fine (LKR)"));
        report.addLine("-".repeat(80));

        boolean hasOverdue = false;
        for (BorrowRecord record : borrowRecords) {
            if (record.getReturnDate() == null && record.isOverdue(today)) {
                double fine = record.calculateFine(record.getUser().getFineStrategy(), today);
                report.addLine(String.format("%-10s %-25s %-20s %-12s %-10.2f", 
                    record.getBook().getBookId(), 
                    record.getBook().getTitle(),
                    record.getUser().getName(),
                    record.getDueDate().toString(),
                    fine));
                hasOverdue = true;
            }
        }

        if (!hasOverdue) {
            report.addLine("No overdue books found.");
        }
    }

    // ==================== Helper Methods ====================

    /**
     * Finds a book by its ID.
     * @param bookId The book ID to search for
     * @return The Book object if found, null otherwise
     */
    private Book findBookById(String bookId) {
        return books.stream()
            .filter(book -> book.getBookId().equals(bookId))
            .findFirst()
            .orElse(null);
    }

    /**
     * Finds a user by their ID.
     * @param userId The user ID to search for
     * @return The User object if found, null otherwise
     */
    private User findUserById(String userId) {
        return users.stream()
            .filter(user -> user.getUserId().equals(userId))
            .findFirst()
            .orElse(null);
    }

    // ==================== Getters ====================

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public List<BorrowRecord> getBorrowRecords() {
        return new ArrayList<>(borrowRecords);
    }

    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    public List<Report> getReports() {
        return new ArrayList<>(reports);
    }
}
