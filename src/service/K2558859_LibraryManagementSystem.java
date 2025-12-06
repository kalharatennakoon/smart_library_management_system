package service;

import model.book.K2558859_Book;
import model.user.K2558859_User;
import model.user.K2558859_Librarian;
import model.borrow.K2558859_BorrowRecord;
import model.reservation.K2558859_Reservation;
import model.report.K2558859_Report;
import command.*;
import exception.LibraryException;
import exception.BookNotFoundException;
import exception.UserNotFoundException;
import exception.InvalidOperationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// K2558859_LibraryManagementSystem - Central service class managing all library operations
public class K2558859_LibraryManagementSystem {
    private List<K2558859_Book> books;
    private List<K2558859_User> users;
    private List<K2558859_Librarian> librarians;
    private List<K2558859_BorrowRecord> borrowRecords;
    private List<K2558859_Reservation> reservations;
    private K2558859_CommandInvoker commandInvoker;
    private List<K2558859_Report> reports;

    private static K2558859_LibraryManagementSystem instance;

    // Constructor
    public K2558859_LibraryManagementSystem() {
        instance = this;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.librarians = new ArrayList<>();
        this.borrowRecords = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.reports = new ArrayList<>();
        this.commandInvoker = new K2558859_CommandInvoker();
    }

    // ----- Book Management -----

    // Adds a new book to the library system
    public void addBook(K2558859_Book book) {
        books.add(book);
        System.out.println("Book '" + book.getTitle() + "' added successfully.");
    }

    // Removes a book from the library system
    public void removeBook(String bookId) {
        K2558859_Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book with ID " + bookId + " not found.");
            return;
        }

        // Check if book is currently borrowed or reserved
        if (book.getAvailabilityStatus().getStateName().equals("Borrowed") ||
            book.getAvailabilityStatus().getStateName().equals("Reserved")) {
            System.out.println("Error: Cannot remove book '" + book.getTitle() + "' as it is currently " + 
                             book.getAvailabilityStatus().getStateName().toLowerCase() + ".");
            return;
        }

        books.remove(book);
        System.out.println("Book '" + book.getTitle() + "' removed successfully.");
    }

    // ----- User Management -----

    // Registers a new user in the library system
    public void registerUser(K2558859_User user) {
        users.add(user);
        System.out.println("User '" + user.getName() + "' registered successfully as " + 
                         user.getClass().getSimpleName() + ".");
    }

    // Removes a user from the library system
    public void removeUser(String userId) {
        K2558859_User user = findUserById(userId);
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

    // ----- Borrowing Operations (Using Command Pattern) -----

    // Borrows a book for a user using Command Pattern
    public void borrowBook(String bookId, String userId) {
        try {
            K2558859_Book book = findBookById(bookId);
            K2558859_User user = findUserById(userId);
            if (book == null) throw new BookNotFoundException(bookId);
            if (user == null) throw new UserNotFoundException(userId);

            K2558859_Command borrowCommand = new K2558859_BorrowCommand(user, book);
            commandInvoker.executeCommand(borrowCommand);
        } catch (LibraryException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    // Returns a book using Command Pattern
    public void returnBook(String bookId, String userId) {
        try {
            K2558859_Book book = findBookById(bookId);
            K2558859_User user = findUserById(userId);
            if (book == null) throw new BookNotFoundException(bookId);
            if (user == null) throw new UserNotFoundException(userId);

            K2558859_Command returnCommand = new K2558859_ReturnCommand(user, book);
            commandInvoker.executeCommand(returnCommand);
        } catch (LibraryException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    // Reserves a book for a user using Command Pattern
    public void reserveBook(String bookId, String userId) {
        try {
            K2558859_Book book = findBookById(bookId);
            K2558859_User user = findUserById(userId);
            if (book == null) throw new BookNotFoundException(bookId);
            if (user == null) throw new UserNotFoundException(userId);

            K2558859_Command reserveCommand = new K2558859_ReserveCommand(user, book);
            commandInvoker.executeCommand(reserveCommand);
        } catch (LibraryException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    // Cancels a book reservation using Command Pattern
    public void cancelReservation(String bookId, String userId) {
        try {
            K2558859_Book book = findBookById(bookId);
            K2558859_User user = findUserById(userId);
            if (book == null) throw new BookNotFoundException(bookId);
            if (user == null) throw new UserNotFoundException(userId);

            K2558859_Command cancelCommand = new K2558859_CancelReservationCommand(user, book);
            commandInvoker.executeCommand(cancelCommand);
        } catch (LibraryException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    // ----- Report Generation -----

    // Generates different types of reports for librarians
    public K2558859_Report generateReport(String reportType) {
        K2558859_Report report = new K2558859_Report("REP-" + System.currentTimeMillis(), reportType);

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

    // Generates report for most borrowed books
    private void generateMostBorrowedBooksReport(K2558859_Report report) {
        Map<String, Integer> borrowCounts = new HashMap<>();

        // Count borrows for each book
        for (K2558859_BorrowRecord record : borrowRecords) {
            String bookId = record.getBook().getBookId();
            borrowCounts.put(bookId, borrowCounts.getOrDefault(bookId, 0) + 1);
        }

        // Sort by borrow count
        List<Map.Entry<String, Integer>> sortedBooks = borrowCounts.entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .collect(Collectors.toList());

        report.addLine("MOST BORROWED BOOKS REPORT");
        report.addLine("----------------------------------------------------------------------");
        report.addLine(String.format("%-10s %-30s %-15s", "Book ID", "Title", "Borrow Count"));
        report.addLine("----------------------------------------------------------------------");

        for (Map.Entry<String, Integer> entry : sortedBooks) {
            K2558859_Book book = findBookById(entry.getKey());
            if (book != null) {
                report.addLine(String.format("%-10s %-30s %-15d", 
                    book.getBookId(), book.getTitle(), entry.getValue()));
            }
        }

        if (sortedBooks.isEmpty()) {
            report.addLine("No borrowing records found.");
        }
    }

    // Generates report for active borrowers
    private void generateActiveBorrowersReport(K2558859_Report report) {
        report.addLine("ACTIVE BORROWERS REPORT");
        report.addLine("----------------------------------------------------------------------");
        report.addLine(String.format("%-10s %-20s %-15s %-15s", 
            "User ID", "Name", "User Type", "Active Borrows"));
        report.addLine("----------------------------------------------------------------------");

        for (K2558859_User user : users) {
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

    // Generates report for overdue books
    private void generateOverdueBooksReport(K2558859_Report report) {
        LocalDate today = LocalDate.now();
        
        report.addLine("OVERDUE BOOKS REPORT");
        report.addLine("----------------------------------------------------------------------");
        report.addLine(String.format("%-10s %-25s %-20s %-12s %-10s", 
            "Book ID", "Title", "Borrower", "Due Date", "Fine (LKR)"));
        report.addLine("----------------------------------------------------------------------");

        boolean hasOverdue = false;
        for (K2558859_BorrowRecord record : borrowRecords) {
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


    // Finds a book by its ID
    private K2558859_Book findBookById(String bookId) {
        return books.stream()
            .filter(book -> book.getBookId().equalsIgnoreCase(bookId))
            .findFirst()
            .orElse(null);
    }

    // Finds a user by their ID
    private K2558859_User findUserById(String userId) {
        return users.stream()
            .filter(user -> user.getUserId().equalsIgnoreCase(userId))
            .findFirst()
            .orElse(null);
    }

    // ----- Librarian Management -----

    // Registers a new librarian in the library system
    public void registerLibrarian(K2558859_Librarian librarian) {
        librarians.add(librarian);
        System.out.println("Librarian '" + librarian.getName() + "' registered successfully.");
    }

    // Removes a librarian from the library system
    public void removeLibrarian(String librarianId) {
        K2558859_Librarian librarian = findLibrarianById(librarianId);
        if (librarian == null) {
            System.out.println("Error: Librarian with ID " + librarianId + " not found.");
            return;
        }

        librarians.remove(librarian);
        System.out.println("Librarian '" + librarian.getName() + "' removed successfully.");
    }

    // Finds a librarian by their ID
    private K2558859_Librarian findLibrarianById(String librarianId) {
        return librarians.stream()
            .filter(librarian -> librarian.getLibrarianId().equalsIgnoreCase(librarianId))
            .findFirst()
            .orElse(null);
    }

    // ----- Getters -----

    public List<K2558859_Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<K2558859_User> getUsers() {
        return new ArrayList<>(users);
    }

    public List<K2558859_BorrowRecord> getBorrowRecords() {
        return new ArrayList<>(borrowRecords);
    }

    public List<K2558859_Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    public List<K2558859_Report> getReports() {
        return new ArrayList<>(reports);
    }

    public List<K2558859_Librarian> getLibrarians() {
        return new ArrayList<>(librarians);
    }

    public static K2558859_LibraryManagementSystem getInstance() {
        return instance;
    }

    public void addBorrowRecord(K2558859_BorrowRecord record) {
        if (record != null) {
            borrowRecords.add(record);
        }
    }
} 
