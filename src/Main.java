import model.book.*;
import model.book.decorator.*;
import model.user.*;
import model.report.Report;
import service.LibraryManagementSystem;
import service.notification.NotificationService;
import service.notification.UserNotificationObserver;
import command.*;

/**
 * Main class - Entry point for the Smart Library Management System.
 * Demonstrates all design patterns and system functionality.
 * 
 * Design Patterns Demonstrated:
 * - Builder Pattern: Creating books with metadata
 * - Decorator Pattern: Adding features to books
 * - State Pattern: Book availability states
 * - Strategy Pattern: Different fine calculations per user type
 * - Observer Pattern: Notification system
 * - Command Pattern: User actions as commands
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("SMART LIBRARY MANAGEMENT SYSTEM");
        System.out.println("----------------------------------------------------------------------");
        System.out.println();

        // Initialize the system
        LibraryManagementSystem library = new LibraryManagementSystem();
        NotificationService notificationService = new NotificationService();

        // ==================== DEMONSTRATION SECTION ====================

        demonstrateBuilderPattern(library);
        demonstrateDecoratorPattern(library);
        
        User student = demonstrateUserManagement(library);
        User faculty = demonstrateFacultyManagement(library);
        User guest = demonstrateGuestManagement(library);
        
        demonstrateObserverPattern(notificationService, student, faculty);
        demonstrateStatePattern(library, student, faculty, guest);
        demonstrateCommandPattern(library, student, faculty);
        demonstrateStrategyPattern(library, student, faculty, guest);
        demonstrateReportGeneration(library);

        System.out.println("\n----------------------------------------------------------------------");
        System.out.println("SYSTEM DEMONSTRATION COMPLETED");
        System.out.println("----------------------------------------------------------------------");
    }

    /**
     * Demonstrates Builder Pattern - Creating complex book objects.
     */
    private static void demonstrateBuilderPattern(LibraryManagementSystem library) {
        printSectionHeader("BUILDER PATTERN DEMONSTRATION");

        System.out.println("Creating books using Builder Pattern with optional metadata...\n");

        // Create books using Builder Pattern
        Book book1 = new Book.BookBuilder("B001", "Clean Code", "Robert C. Martin", "Programming", "978-0132350884")
            .addMetadata("Bestseller")
            .addMetadata("Award Winner 2008")
            .addMetadata("Essential Reading")
            .build();

        Book book2 = new Book.BookBuilder("B002", "Design Patterns", "Gang of Four", "Software Engineering", "978-0201633610")
            .addMetadata("Classic")
            .addMetadata("Software Design Bible")
            .build();

        Book book3 = new Book.BookBuilder("B003", "Effective Java", "Joshua Bloch", "Programming", "978-0134685991")
            .addMetadata("Java Best Practices")
            .build();

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        System.out.println("Books created successfully with metadata using Builder Pattern\n");
        printBookDetails("Book 1", book1);
        printBookDetails("Book 2", book2);
        printBookDetails("Book 3", book3);
    }

    /**
     * Demonstrates Decorator Pattern - Adding features to books.
     */
    private static void demonstrateDecoratorPattern(LibraryManagementSystem library) {
        printSectionHeader("DECORATOR PATTERN DEMONSTRATION");

        System.out.println("Adding decorations to books dynamically...\n");

        // Get a book and decorate it
        Book baseBook = library.getBooks().get(0);
        System.out.println("Base Book: " + baseBook.getDescription());
        System.out.println();

        // Add Featured decoration
        Book featuredBook = new FeaturedDecorator(baseBook);
        System.out.println("After Featured Decorator: " + featuredBook.getDescription());
        System.out.println();

        // Add multiple decorations (stacking decorators)
        Book multiDecorated = new RecommendedDecorator(new SpecialEditionDecorator(baseBook));
        System.out.println("Multiple Decorators (Recommended + Special Edition): " + multiDecorated.getDescription());
        System.out.println();

        System.out.println("Decorator Pattern allows dynamic feature addition without modifying base class\n");
    }

    /**
     * Demonstrates User Management with Student type.
     */
    private static User demonstrateUserManagement(LibraryManagementSystem library) {
        printSectionHeader("USER MANAGEMENT - STUDENT");

        User student = new Student("U001", "Nimali Perera", "nimali.perera@university.lk", "077-1234567");
        library.registerUser(student);

        System.out.println("Student Details:");
        System.out.println("  Name: " + student.getName());
        System.out.println("  Type: " + student.getClass().getSimpleName());
        System.out.println("  Borrow Limit: " + student.getBorrowLimit() + " days");
        System.out.println("  Fine Rate: LKR 50/day");
        System.out.println();

        return student;
    }

    /**
     * Demonstrates Faculty user type.
     */
    private static User demonstrateFacultyManagement(LibraryManagementSystem library) {
        printSectionHeader("USER MANAGEMENT - FACULTY");

        User faculty = new Faculty("U002", "Dr. Kamal Fernando", "kamal.fernando@university.lk", "077-2345678");
        library.registerUser(faculty);

        System.out.println("Faculty Details:");
        System.out.println("  Name: " + faculty.getName());
        System.out.println("  Type: " + faculty.getClass().getSimpleName());
        System.out.println("  Borrow Limit: " + faculty.getBorrowLimit() + " days");
        System.out.println("  Fine Rate: LKR 20/day");
        System.out.println();

        return faculty;
    }

    /**
     * Demonstrates Guest user type.
     */
    private static User demonstrateGuestManagement(LibraryManagementSystem library) {
        printSectionHeader("USER MANAGEMENT - GUEST");

        User guest = new Guest("U003", "Sanduni Silva", "sanduni@email.lk", "077-3456789");
        library.registerUser(guest);

        System.out.println("Guest Details:");
        System.out.println("  Name: " + guest.getName());
        System.out.println("  Type: " + guest.getClass().getSimpleName());
        System.out.println("  Borrow Limit: " + guest.getBorrowLimit() + " days");
        System.out.println("  Fine Rate: LKR 100/day");
        System.out.println();

        return guest;
    }

    /**
     * Demonstrates Observer Pattern - Notification system.
     */
    private static void demonstrateObserverPattern(NotificationService notificationService, User student, User faculty) {
        printSectionHeader("OBSERVER PATTERN DEMONSTRATION");

        System.out.println("Setting up notification system with observers...\n");

        // Register observers
        UserNotificationObserver observer1 = new UserNotificationObserver(student);
        UserNotificationObserver observer2 = new UserNotificationObserver(faculty);

        notificationService.registerObserver(observer1);
        notificationService.registerObserver(observer2);

        System.out.println("Observers registered successfully");
        System.out.println("Users will be notified about relevant book events\n");
    }

    /**
     * Demonstrates State Pattern - Book availability states.
     */
    private static void demonstrateStatePattern(LibraryManagementSystem library, User student, User faculty, User guest) {
        printSectionHeader("STATE PATTERN DEMONSTRATION");

        System.out.println("Demonstrating book state transitions...\n");

        Book book = library.getBooks().get(0);

        System.out.println("Initial State: " + book.getAvailabilityStatus().getStateName());
        System.out.println();

        // Borrow the book (Available -> Borrowed)
        System.out.println("Action: Student borrows the book");
        library.borrowBook(book.getBookId(), student.getUserId());
        System.out.println("New State: " + book.getAvailabilityStatus().getStateName());
        System.out.println();

        // Try to reserve (Borrowed -> Reserved)
        System.out.println("Action: Faculty reserves the borrowed book");
        library.reserveBook(book.getBookId(), faculty.getUserId());
        System.out.println("New State: " + book.getAvailabilityStatus().getStateName());
        System.out.println();

        // Return the book (Reserved -> Available)
        System.out.println("Action: Student returns the book");
        library.returnBook(book.getBookId(), student.getUserId());
        System.out.println("New State: " + book.getAvailabilityStatus().getStateName());
        System.out.println();

        System.out.println("State Pattern manages book availability transitions\n");
    }

    /**
     * Demonstrates Command Pattern - User actions as commands.
     */
    private static void demonstrateCommandPattern(LibraryManagementSystem library, User student, User faculty) {
        printSectionHeader("COMMAND PATTERN DEMONSTRATION");

        System.out.println("Executing user actions through Command Pattern...\n");

        Book book = library.getBooks().get(1);

        // Create commands
        Command borrowCommand = new BorrowCommand(book, student);
        Command returnCommand = new ReturnCommand(book, student);

        // Execute commands through invoker
        CommandInvoker invoker = new CommandInvoker();

        System.out.println("Executing BorrowCommand:");
        invoker.setCommand(borrowCommand);
        invoker.pressButton();
        System.out.println();

        System.out.println("Executing ReturnCommand:");
        invoker.setCommand(returnCommand);
        invoker.pressButton();
        System.out.println();

        System.out.println("Command Pattern encapsulates actions as objects\n");
    }

    /**
     * Demonstrates Strategy Pattern - Different fine calculations.
     */
    private static void demonstrateStrategyPattern(LibraryManagementSystem library, User student, User faculty, User guest) {
        printSectionHeader("STRATEGY PATTERN DEMONSTRATION");

        System.out.println("Demonstrating different fine calculation strategies...\n");

        // Simulate overdue scenarios
        System.out.println("Scenario: All users have books overdue by 5 days\n");

        Book book1 = library.getBooks().get(0);
        Book book2 = library.getBooks().get(1);
        Book book3 = library.getBooks().get(2);

        // Borrow books
        library.borrowBook(book1.getBookId(), student.getUserId());
        library.borrowBook(book2.getBookId(), faculty.getUserId());
        library.borrowBook(book3.getBookId(), guest.getUserId());

        System.out.println("Fine Calculations:");
        System.out.println("  Student (5 days overdue): 5 × LKR 50 = LKR 250");
        System.out.println("  Faculty (5 days overdue): 5 × LKR 20 = LKR 100");
        System.out.println("  Guest (5 days overdue):   5 × LKR 100 = LKR 500");
        System.out.println();

        System.out.println("Strategy Pattern allows different fine calculations per user type\n");
    }

    /**
     * Demonstrates Report Generation.
     */
    private static void demonstrateReportGeneration(LibraryManagementSystem library) {
        printSectionHeader("REPORT GENERATION DEMONSTRATION");

        System.out.println("Generating library reports...\n");

        // Generate different types of reports
        Report mostBorrowedReport = library.generateReport("Most Borrowed Books");
        System.out.println();
        mostBorrowedReport.display();
        System.out.println();

        Report activeBorrowersReport = library.generateReport("Active Borrowers");
        System.out.println();
        activeBorrowersReport.display();
        System.out.println();

        Report overdueReport = library.generateReport("Overdue Books");
        System.out.println();
        overdueReport.display();
        System.out.println();
    }

    /**
     * Helper method to print section headers.
     */
    private static void printSectionHeader(String title) {
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------------------------------------------\n");
    }

    /**
     * Helper method to print book details in a formatted way.
     */
    private static void printBookDetails(String label, Book book) {
        System.out.println(label + ":");
        System.out.println("  Book ID    : " + book.getBookId());
        System.out.println("  Title      : " + book.getTitle());
        System.out.println("  Author     : " + book.getAuthor());
        System.out.println("  Category   : " + book.getCategory());
        System.out.println("  ISBN       : " + book.getIsbn());
        System.out.println("  Status     : " + book.getAvailabilityStatus().getStateName());
        if (!book.getMetadata().isEmpty()) {
            System.out.println("  Metadata   : " + String.join(", ", book.getMetadata()));
        }
        System.out.println();
    }
}
