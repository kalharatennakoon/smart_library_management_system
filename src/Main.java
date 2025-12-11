import model.book.*;
import model.book.decorator.*;
import model.user.*;
import model.report.K2558859_Report;
import service.K2558859_LibraryManagementSystem;
import service.notification.K2558859_NotificationService;
import service.notification.K2558859_UserNotificationObserver;
import util.ValidationUtil;
import java.util.Scanner;
import java.util.Random;

// Main class - Interactive CLI for the Smart Library Management System
public class Main {
    private K2558859_LibraryManagementSystem library;
    private K2558859_NotificationService notificationService;
    private Scanner scanner;
    private int nextBookId = 1;
    private int nextUserId = 1;
    private int nextLibrarianId = 1;

    public Main() {
        this.library = new K2558859_LibraryManagementSystem();
        this.notificationService = new K2558859_NotificationService();
        this.scanner = new Scanner(System.in);
        this.nextBookId = 1;
        this.nextUserId = 1;
        this.nextLibrarianId = 1;
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    // Main CLI loop - displays menu and processes user choices
    public void run() {
        printWelcomeBanner();
        
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    bookManagementMenu();
                    break;
                case 2:
                    userManagementMenu();
                    break;
                case 3:
                    borrowingOperationsMenu();
                    break;
                case 4:
                    reservationMenu();
                    break;
                case 5:
                    notificationMenu();
                    break;
                case 6:
                    reportMenu();
                    break;
                case 7:
                    viewAllData();
                    break;
                case 0:
                    running = false;
                    System.out.println("\n" + "=".repeat(80));
                    System.out.println("Thank you for using Smart Library Management System!");
                    System.out.println("=".repeat(80));
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number corresponding to the menu options.");
            }
        }
        
        scanner.close();
    }

    // ------ BOOK MANAGEMENT --------

    private void bookManagementMenu() {
        while (true) {
            printSectionHeader("BOOK MANAGEMENT");
            System.out.println("1. Add Book");
            System.out.println("2. Add Book with Metadata"); // Builder Pattern
            System.out.println("3. Decorate Book"); // Decorator Pattern
            System.out.println("4. Update Book");
            System.out.println("5. Remove Book");
            System.out.println("6. View All Books");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("\nEnter your choice: ");

            switch (choice) {
                case 1:
                    addSimpleBook();
                    break;
                case 2:
                    addBookWithBuilder();
                    break;
                case 3:
                    decorateBook();
                    break;
                case 4:
                    updateBook();
                    break;
                case 5:
                    removeBook();
                    break;
                case 6:
                    viewAllBooks();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 0 and 6.");
            }
        }
    }

    private void addSimpleBook() {
        printSubHeader("Add Simple Book");
        
        String title = getStringInput("Title: ");
        String author = getStringInput("Author: ");
        String category = getStringInput("Category: ");
        String isbn = getStringInput("ISBN: ");
        
        String bookId = generateId("B"); // Auto-generate book ID
        K2558859_Book book = new K2558859_BasicBook(bookId, title, author, category, isbn);
        library.addBook(book);
    }

    // Builder Pattern implementation
    private void addBookWithBuilder() {
        printSubHeader("Add Book with Metadata");
        
        String title = getStringInput("Title: ");
        String author = getStringInput("Author: ");
        String category = getStringInput("Category: ");
        String isbn = getStringInput("ISBN: ");
        
        String bookId = generateId("B"); // Auto-generate book ID
        K2558859_Book.K2558859_BookBuilder builder = 
            new K2558859_Book.K2558859_BookBuilder(bookId, title, author, category, isbn);
        
        // Ask for optional metadata
        System.out.println("\nAdd optional metadata (press Enter to skip):");
        
        String edition = getOptionalStringInput("Edition (e.g., 'First Edition', '2024'): ", "");
        if (!edition.isEmpty()) {
            builder.setEdition(edition);
        }
        
        String review = getOptionalStringInput("Review: ", "");
        if (!review.isEmpty()) {
            builder.addReview(review);
        }
        
        String tag = getOptionalStringInput("Tag (e.g., 'bestseller', 'award-winning'): ", "");
        if (!tag.isEmpty()) {
            builder.addTag(tag);
        }
        
        // Allow multiple tags
        System.out.print("Add more tags? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            while (true) {
                String additionalTag = getOptionalStringInput("Additional Tag (or press Enter to finish): ", "");
                if (additionalTag.isEmpty()) break;
                builder.addTag(additionalTag);
            }
        }
        
        K2558859_Book book = builder.build();
        library.addBook(book);
        
        System.out.println("\nBook Details:");
        System.out.println(book.getDescription());
    }

    // Decorator Pattern implementation
    private void decorateBook() {
        if (library.getBooks().isEmpty()) {
            System.out.println("\nNo books available. Add a book first.");
            return;
        }
        
        printSubHeader("Decorate Book");
        viewAllBooks();
        
        String bookId = getStringInput("\nEnter Book ID to decorate: ");
        K2558859_Book baseBook = findBookById(bookId);
        
        if (baseBook == null) {
            System.out.println("\nBook not found!");
            return;
        }
        
        System.out.println("\nChoose decoration:");
        System.out.println("1. Featured");
        System.out.println("2. Recommended");
        System.out.println("3. Special Edition");
        System.out.println("4. All Decorations (Featured + Recommended + Special Edition)");
        
        int choice = getIntInput("Choice: ");
        K2558859_Book decoratedBook = baseBook;
        
        switch (choice) {
            case 1:
                decoratedBook = new K2558859_FeaturedDecorator(baseBook);
                break;
            case 2:
                decoratedBook = new K2558859_RecommendedDecorator(baseBook);
                break;
            case 3:
                decoratedBook = new K2558859_SpecialEditionDecorator(baseBook);
                break;
            case 4:
                decoratedBook = new K2558859_FeaturedDecorator(
                    new K2558859_RecommendedDecorator(
                        new K2558859_SpecialEditionDecorator(baseBook)));
                break;
        }
        
        System.out.println("\nBook decorated successfully!");
        System.out.println("\nOriginal Book:");
        System.out.println(baseBook.getDescription());
        System.out.println("\nDecorated Book:");
        System.out.println(decoratedBook.getDescription());
    }

    private void updateBook() {
        printSubHeader("Update Book");
        
        if (library.getBooks().isEmpty()) {
            System.out.println("\nNo books available in the system to update.");
            return;
        }
        
        viewAllBooks();
        
        String bookId = getStringInput("\nEnter Book ID to update: ").trim();
        K2558859_Book existingBook = findBookById(bookId);
        
        if (existingBook == null) {
            System.out.println("\nError: Book with ID " + bookId + " not found.");
            return;
        }
        
        System.out.println("\nCurrent Book Details:");
        System.out.println("Title: " + existingBook.getTitle());
        System.out.println("Author: " + existingBook.getAuthor());
        System.out.println("Category: " + existingBook.getCategory());
        System.out.println("ISBN: " + existingBook.getIsbn());
        System.out.println("Status: " + existingBook.getAvailabilityStatus().getStateName());
        
        // Display current metadata if any
        if (!existingBook.getMetadata().isEmpty()) {
            System.out.println("\nCurrent Metadata:");
            for (String meta : existingBook.getMetadata()) {
                System.out.println("  • " + meta);
            }
        }
        
        System.out.println("\n" + "-".repeat(80));
        System.out.println("Enter new details (press Enter to keep current value):");
        System.out.println("-".repeat(80));
        
        // Get new values, using current values as defaults
        String newTitle = getOptionalStringInput("New Title [" + existingBook.getTitle() + "]: ", existingBook.getTitle());
        String newAuthor = getOptionalStringInput("New Author [" + existingBook.getAuthor() + "]: ", existingBook.getAuthor());
        String newCategory = getOptionalStringInput("New Category [" + existingBook.getCategory() + "]: ", existingBook.getCategory());
        String newIsbn = getOptionalStringInput("New ISBN [" + existingBook.getIsbn() + "]: ", existingBook.getIsbn());
        
        // Confirm update
        System.out.print("\nConfirm update? (yes/no): ");
        String confirmation = scanner.nextLine().trim();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            library.updateBook(bookId, newTitle, newAuthor, newCategory, newIsbn);
        } else {
            System.out.println("\nUpdate cancelled.");
        }
    }

    private void removeBook() {
        printSubHeader("Remove Book");
        
        if (library.getBooks().isEmpty()) {
            System.out.println("\nNo books available in the system to remove.");
            return;
        }
        
        viewAllBooks();
        String bookId = getStringInput("\nEnter Book ID to remove: ").trim();
        
        // Find the book first to confirm its existence and details
        K2558859_Book bookToRemove = findBookById(bookId);

        if (bookToRemove == null) {
            System.out.println("\nError: Book with ID " + bookId + " not found.");
            return;
        }

        // Get confirmation before deleting
        System.out.printf("\nAre you sure you want to delete the book '%s' (ID: %s)? (yes/no): ",
                bookToRemove.getTitle(), bookToRemove.getBookId());
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("yes")) {
            library.removeBook(bookToRemove.getBookId()); // Use the correct-cased ID
        } else {
            System.out.println("\nDeletion cancelled.");
        }
    }

    private void viewAllBooks() {
        printSubHeader("All Books");
        
        if (library.getBooks().isEmpty()) {
            System.out.println("No books in the system.");
            return;
        }
        
        System.out.printf("%-10s %-30s %-20s %-15s %-20s %-15s\n", "Book ID", "Title", "Author", "Status", "Category", "ISBN");
        System.out.println("-".repeat(120));
        
        for (K2558859_Book book : library.getBooks()) {
            System.out.printf("%-10s %-30s %-20s %-15s %-20s %-15s\n",
                book.getBookId(),
                truncate(book.getTitle(), 30),
                truncate(book.getAuthor(), 20),
                book.getAvailabilityStatus().getStateName(),
                truncate(book.getCategory(), 20),
                truncate(book.getIsbn(), 15));
            
            // Display optional metadata (reviews, tags, editions) if present
            if (!book.getMetadata().isEmpty()) {
                System.out.println("  Metadata:");
                for (String meta : book.getMetadata()) {
                    System.out.println("    • " + meta);
                }
            }
        }
    }

    // ------- USER MANAGEMENT --------

    private void userManagementMenu() {
        while (true) {
            printSectionHeader("USER MANAGEMENT");
            System.out.println("1. Register Student");
            System.out.println("2. Register Faculty");
            System.out.println("3. Register Guest");
            System.out.println("4. Remove User");
            System.out.println("5. View All Users");
            System.out.println("0. Back to Main Menu");
            
            int choice = getIntInput("\nEnter your choice: ");
            
            switch (choice) {
                case 1:
                    registerUser("Student");
                    break;
                case 2:
                    registerUser("Faculty");
                    break;
                case 3:
                    registerUser("Guest");
                    break;
                case 4:
                    removeUser();
                    break;
                case 5:
                    viewAllUsers();
                    break;
                case 0:
                    return; // Exit to Main Menu
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 0 and 5.");
            }
        }
    }

    // Registers a new user of the specified type (Student, Faculty, or Guest)
    private void registerUser(String userType) {
        printSubHeader("Register " + userType);
        
        String name = getStringInput("Name: ");
        
        // Email validation with loop until valid input
        String email;
        while (true) {
            email = getStringInput("Email: ");
            if (ValidationUtil.isValidEmail(email)) {
                break;
            }
            System.out.println(ValidationUtil.getEmailErrorMessage() + "\n");
        }

        // Contact number validation with loop until valid input
        String contactNumber;
        while (true) {
            contactNumber = getStringInput("Contact Number: ");
            if (ValidationUtil.isValidContactNumber(contactNumber)) {
                break;
            }
            System.out.println(ValidationUtil.getContactNumberErrorMessage() + "\n");
        }
        
        String userId = generateId("U"); // Auto-generate user ID
        K2558859_User user = null;
        switch (userType) {
            case "Student":
                user = new K2558859_Student(userId, name, email, contactNumber);
                System.out.println("\nStudent Details:");
                System.out.println("  Loan Period: 14 days");
                System.out.println("  Fine Rate: LKR 50/day");
                break;
            case "Faculty":
                user = new K2558859_Faculty(userId, name, email, contactNumber);
                System.out.println("\nFaculty Details:");
                System.out.println("  Loan Period: 30 days");
                System.out.println("  Fine Rate: LKR 20/day");
                break;
            case "Guest":
                user = new K2558859_Guest(userId, name, email, contactNumber);
                System.out.println("\nGuest Details:");
                System.out.println("  Loan Period: 7 days");
                System.out.println("  Fine Rate: LKR 100/day");
                break;
        }
        
        if (user != null) {
            library.registerUser(user);
            
            // Ask if user wants to register for notifications
            System.out.print("\nRegister for notifications? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                K2558859_UserNotificationObserver observer = new K2558859_UserNotificationObserver(user);
                notificationService.registerObserver(observer);
                System.out.println("User registered as observer!");
            }
        }
    }

    private void removeUser() {
        printSubHeader("Remove User");
        
        if (library.getUsers().isEmpty()) {
            System.out.println("\nNo users available in the system to remove.");
            return;
        }
        
        viewAllUsers();
        String userId = getStringInput("\nEnter User ID to remove: ").trim();

        // Find the user first to confirm existence and details
        K2558859_User userToRemove = findUserById(userId);

        if (userToRemove == null) {
            System.out.println("\nError: User with ID " + userId + " not found.");
            return;
        }

        // Get confirmation before deleting
        System.out.printf("\nAre you sure you want to delete the user '%s' (ID: %s)? (yes/no): ",
                userToRemove.getName(), userToRemove.getUserId());
        String confirmation = scanner.nextLine().trim();

        if (confirmation.equalsIgnoreCase("yes")) {
            library.removeUser(userToRemove.getUserId()); // Use the correct-cased ID
        } else {
            System.out.println("\nDeletion cancelled.");
        }
    }

    private void viewAllUsers() {
        printSubHeader("All Users");
        
        if (library.getUsers().isEmpty()) {
            System.out.println("No users in the system.");
            return;
        }
        
        System.out.printf("%-10s %-25s %-15s %-15s\n", "User ID", "Name", "Type", "Active Borrows");
        System.out.println("-".repeat(70));
        
        for (K2558859_User user : library.getUsers()) {
            System.out.printf("%-10s %-25s %-15s %-15d\n",
                user.getUserId(),
                truncate(user.getName(), 25),
                user.getClass().getSimpleName(),
                user.getActiveBorrowCount());
        }
    }

    // ------ BORROWING OPERATIONS -------- (Command Pattern)

    private void borrowingOperationsMenu() {
        while (true) {
            printSectionHeader("BORROWING OPERATIONS");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. View Borrow Records");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("\nEnter your choice: ");

            switch (choice) {
            case 1:
                borrowBook();
                break;
            case 2:
                returnBook();
                break;
            case 3:
                viewBorrowRecords();
                break;
                case 0:
                    return; // Exit to Main Menu
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 0 and 3.");
            }
        }
    }

    private void borrowBook() {
        printSubHeader("Borrow Book");
        
        if (library.getBooks().isEmpty()) {
            System.out.println("\nNo books available in the system to borrow.");
            return;
        }
        
        System.out.println("\nAvailable Books:");
        viewAllBooks();
        
        if (library.getUsers().isEmpty()) {
            System.out.println("\nNo registered users available to borrow books.");
            return;
        }
        
        System.out.println("\nRegistered Users:");
        viewAllUsers();
        
        String bookId = getStringInput("\nEnter Book ID: ").trim();
        String userId = getStringInput("Enter User ID: ").trim();

        // Find the actual book and user to get correctly-cased IDs
        K2558859_Book bookToBorrow = findBookById(bookId);
        K2558859_User userToBorrow = findUserById(userId);

        if (bookToBorrow == null) {
            System.out.println("\nError: Book with ID " + bookId + " not found.");
            return;
        }
        if (userToBorrow == null) {
            System.out.println("\nError: User with ID " + userId + " not found.");
            return;
        }

        library.borrowBook(bookToBorrow.getBookId(), userToBorrow.getUserId());
    }

    private void returnBook() {
        printSubHeader("Return Book");
        
        if (library.getBorrowRecords().isEmpty()) {
            System.out.println("\nNo borrow records found.");
            return;
        }
        
        viewBorrowRecords();
        
        String bookId = getStringInput("\nEnter Book ID: ").trim();
        String userId = getStringInput("Enter User ID: ").trim();

        // Find the actual book and user to get correctly-cased IDs
        K2558859_Book bookToReturn = findBookById(bookId);
        K2558859_User userReturning = findUserById(userId);

        if (bookToReturn == null) {
            System.out.println("\nError: Book with ID " + bookId + " not found.");
            return;
        }
        if (userReturning == null) {
            System.out.println("\nError: User with ID " + userId + " not found.");
            return;
        }

        library.returnBook(bookToReturn.getBookId(), userReturning.getUserId());
    }

    private void viewBorrowRecords() {
        printSubHeader("Borrow Records");
        
        if (library.getBorrowRecords().isEmpty()) {
            System.out.println("No borrow records found.");
            return;
        }
        
        System.out.printf("%-15s %-25s %-20s %-12s %-12s\n", 
            "Record ID", "Book", "User", "Due Date", "Status");
        System.out.println("-".repeat(90));
        
        for (var record : library.getBorrowRecords()) {
            String status = record.getReturnDate() != null ? "Returned" : 
                           (record.isOverdue(java.time.LocalDate.now()) ? "OVERDUE" : "Active");
            
            System.out.printf("%-15s %-25s %-20s %-12s %-12s\n",
                truncate(record.getRecordId(), 15),
                truncate(record.getBook().getTitle(), 25),
                truncate(record.getUser().getName(), 20),
                record.getDueDate().toString(),
                status);
        }
    }

    // ------ RESERVATION MENU -------- (State Pattern)

    private void reservationMenu() {
        while (true) {
            printSectionHeader("RESERVATION OPERATIONS");
            System.out.println("1. Reserve Book");
            System.out.println("2. View Reservations");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("\nEnter your choice: ");

            switch (choice) {
            case 1:
                reserveBook();
                break;
            case 2:
                viewReservations();
                break;
                case 0:
                    return; // Exit to Main Menu
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 0 and 2.");
            }
        }
    }

    private void reserveBook() {
        printSubHeader("Reserve Book");
        
        if (library.getBooks().isEmpty()) {
            System.out.println("\nNo books available in the system to reserve.");
            return;
        }
        
        viewAllBooks();
        System.out.println();
        
        if (library.getUsers().isEmpty()) {
            System.out.println("\nNo registered users available to reserve books.");
            return;
        }
        
        viewAllUsers();
        
        String bookId = getStringInput("\nEnter Book ID: ").trim();
        String userId = getStringInput("Enter User ID: ").trim();

        // Find the actual book and user to get correctly-cased IDs
        K2558859_Book bookToReserve = findBookById(bookId);
        K2558859_User userReserving = findUserById(userId);

        if (bookToReserve == null) {
            System.out.println("\nError: Book with ID " + bookId + " not found.");
            return;
        }
        if (userReserving == null) {
            System.out.println("\nError: User with ID " + userId + " not found.");
            return;
        }

        library.reserveBook(bookToReserve.getBookId(), userReserving.getUserId());
    }

    private void viewReservations() {
        printSubHeader("Reservations");
        
        if (library.getReservations().isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }
        
        System.out.printf("%-15s %-30s %-25s %-15s\n", 
            "Reservation ID", "Book", "User", "Date");
        System.out.println("-".repeat(90));
        
        for (var reservation : library.getReservations()) {
            System.out.printf("%-15s %-30s %-25s %-15s\n",
                truncate(reservation.getReservationId(), 15),
                truncate(reservation.getBook().getTitle(), 30),
                truncate(reservation.getUser().getName(), 25),
                reservation.getReservationDate().toString());
        }
    }

    // ------ NOTIFICATION MENU -------- (Observer Pattern)

    private void notificationMenu() {
        while (true) {
            printSectionHeader("NOTIFICATIONS");
            System.out.println("1. Send Test Notification");
            System.out.println("2. Send Due Date Reminder");
            System.out.println("3. View Observer Count");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("\nEnter your choice: ");

            switch (choice) {
                case 1:
                    sendTestNotification();
                    break;
                case 2:
                    sendDueDateReminder();
                    break;
                case 3:
                    System.out.println("\nObserver notification feature - Count not available");
                    // System.out.println("\nRegistered Observers: " + notificationService.getObserverCount());
                    break;
                case 0:
                    return; // Exit the menu
                default:
                    System.out.println("\nInvalid choice. Please enter a valid option.");
            }
        }
    }

    private void sendTestNotification() {
        if (library.getBooks().isEmpty()) {
            System.out.println("\nNo books available. Add books first.");
            return; // Do not re-enter notificationMenu
        }
        
        viewAllBooks();
        String bookId = getStringInput("\nEnter Book ID: ").trim();
        K2558859_Book book = findBookById(bookId);
        
        if (book != null) {
            String message = getStringInput("Enter notification message: ");
            notificationService.notifyObservers(book, message);
            // notificationService.sendBookNotification(book, message);
        }
    }

    private void sendDueDateReminder() {
        if (library.getBooks().isEmpty()) {
            System.out.println("\nNo books available. Add books first.");
            return; // Do not re-enter notificationMenu
        }
        
        viewAllBooks();
        String bookId = getStringInput("\nEnter Book ID: ").trim();
        K2558859_Book book = findBookById(bookId);
        
        if (book != null) {
            int days = getIntInput("Days until due: ");
            String message = "Book '" + book.getTitle() + "' is due in " + days + " days";
            notificationService.notifyObservers(book, message);
            // notificationService.sendDueDateReminder(book, days);
        }
    }

    // ------ REPORT MENU -------- (Strategy Pattern for fine calculation)

    private void reportMenu() {
        while (true) {
            printSectionHeader("REPORTS");
            System.out.println("1. Most Borrowed Books");
            System.out.println("2. Active Borrowers");
            System.out.println("3. Overdue Books");
            System.out.println("0. Back to Main Menu");

            int choice = getIntInput("\nEnter your choice: ");

            String reportType = null;
            switch (choice) {
                case 1:
                    reportType = "Most Borrowed Books";
                    break;
                case 2:
                    reportType = "Active Borrowers";
                    break;
                case 3:
                    reportType = "Overdue Books";
                    break;
                case 0:
                    return; // Exit the menu
                default:
                    System.out.println("\nInvalid choice. Please enter a valid option.");
                    continue;
            }

            if (reportType != null) {
                System.out.println("\nGenerating report...");
                K2558859_Report report = library.generateReport(reportType);
                System.out.println();
                report.display();
            }
        }
    }

    // ------ VIEW ALL DATA --------

    private void viewAllData() {
        printSectionHeader("SYSTEM DATA OVERVIEW");
        
        System.out.println("Total Books: " + library.getBooks().size());
        System.out.println("Total Users: " + library.getUsers().size());
        System.out.println("Total Borrow Records: " + library.getBorrowRecords().size());
        System.out.println("Total Reservations: " + library.getReservations().size());
        // System.out.println("Registered Observers: " + notificationService.getObserverCount());
        System.out.println("Total Reports: " + library.getReports().size());
    }

    // ------- HELPER METHODS -------

    private void printWelcomeBanner() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(centerText("SMART LIBRARY MANAGEMENT SYSTEM", 80));
        System.out.println("=".repeat(80));
    }

    private void printMainMenu() {
        System.out.println("\nMAIN MENU\n");
        System.out.println("1. Book Management");
        System.out.println("2. User Management");
        System.out.println("3. Borrowing Operations");
        System.out.println("4. Reservation Operations");
        System.out.println("5. Notifications");
        System.out.println("6. Reports");
        System.out.println("7. View System Overview");
        System.out.println("0. Exit");
        System.out.println("=".repeat(80));
    }

    private void printSectionHeader(String title) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(title);
        System.out.println("=".repeat(80));
    }

    private void printSubHeader(String title) {
        System.out.println("\n" + "-".repeat(80));
        System.out.println(title);
        System.out.println("-".repeat(80));
    }

    private String getStringInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private K2558859_Book findBookById(String bookId) {
        return library.getBooks().stream()
            .filter(book -> book.getBookId().equalsIgnoreCase(bookId))
            .findFirst()
            .orElse(null);
    }
    
    private K2558859_User findUserById(String userId) {
        return library.getUsers().stream()
            .filter(user -> user.getUserId().equalsIgnoreCase(userId))
            .findFirst()
            .orElse(null);
    }

    private String truncate(String str, int length) {
        if (str == null) return "";
        return str.length() > length ? str.substring(0, length - 3) + "..." : str;
    }

    private String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        String pad = " ".repeat(Math.max(0, padding));
        return pad + text + pad;
    }

    private String generateId(String prefix) {
        if ("B".equals(prefix)) {
            return prefix + String.format("%04d", nextBookId++);
        } else if ("U".equals(prefix)) {
            return prefix + String.format("%04d", nextUserId++);
        } else if ("L".equals(prefix)) {
            return prefix + String.format("%04d", nextLibrarianId++);
        }
        // Fallback for any other prefixes, though not expected
        return prefix + String.format("%04d", new Random().nextInt(10000));
    }

    private String getOptionalStringInput(String prompt, String defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }
}
