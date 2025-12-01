# Smart Library Management System

A comprehensive library management system built with Java, implementing various design patterns to provide a robust and scalable solution for managing books, users, borrowing, and reservations.

## Features

- **Book Management**: Add, update, and manage books with various attributes
- **User Management**: Support for different user types (Students, Faculty, Guests)
- **Borrowing System**: Track book borrowings with due dates and return status
- **Reservation System**: Allow users to reserve books that are currently borrowed
- **Fine Calculation**: Automatic fine calculation based on user type and overdue days
- **Notifications**: Real-time notifications for users about borrowing, reservations, and due dates
- **Book Decorators**: Enhanced book features (Featured, Recommended, Special Edition)
- **Interactive CLI**: Comprehensive menu-driven interface for all operations
- **Data Validation**: Email and contact number validation for user registration
- **Exception Handling**: Robust error handling with custom exception hierarchy

## Additional Strengths

### 1. **Custom Exception Hierarchy**
The system implements a comprehensive exception handling mechanism with specialized exception types:

- **`LibraryException`** (Base Exception): Parent class for all library-specific exceptions
- **`BookNotFoundException`**: Thrown when a requested book is not found
- **`UserNotFoundException`**: Thrown when a requested user is not found
- **`InvalidOperationException`**: Thrown for invalid state transitions or business rule violations
- **`ValidationException`**: Thrown when input validation fails

**Location**: `src/exception/`

**Benefits**:
- Better error diagnosis and debugging
- Specific error handling for different scenarios
- Clear separation of error types
- Improved code maintainability

### 2. **Interactive CLI with Menu-Driven Interface**
A comprehensive command-line interface that provides:

- **Main Menu**: Central navigation hub
- **Book Management**: Add books (simple or with metadata), decorate books, remove books, view all books
- **User Management**: Register users (Student/Faculty/Guest), remove users, view all users
- **Borrowing Operations**: Borrow books, return books, calculate fines
- **Reservation Management**: Reserve books, cancel reservations
- **Notification System**: Subscribe users to notifications (Observer Pattern demonstration)
- **Report Generation**: Generate various reports (most borrowed books, active borrowers, overdue books)

**Location**: `src/Main.java`

**Features**:
- Input validation for all user inputs
- Clear error messages
- Formatted output with tables and headers
- User-friendly prompts and confirmations

### 3. **Data Validation**
Comprehensive input validation using the ValidationUtil class:

#### Email Validation
- **Pattern**: `username@domain.extension`
- **Examples**: 
  - Valid: `john.doe@example.com`, `student123@university.edu`
  - Invalid: `john@`, `@example.com`, `john.doe@`
- **Implementation**: Regex pattern matching

#### Contact Number Validation
- **Format**: Exactly 10 digits
- **Examples**:
  - Valid: `0771234567`, `1234567890`
  - Invalid: `123456789` (9 digits), `077-123-4567` (contains hyphens)
- **Implementation**: Regex pattern matching

#### Additional Validation Methods
- **`isNotEmpty()`**: Validates non-null, non-empty strings
- **`isPositive()`**: Validates positive numeric values
- **Custom error messages**: User-friendly validation error messages

**Location**: `src/util/ValidationUtil.java`

**Benefits**:
- Prevents invalid data from entering the system
- Improves data quality and consistency
- Provides clear feedback to users
- Centralized validation logic for easy maintenance

### 4. **Extensive JavaDoc Documentation**
Every class, method, and significant code block includes comprehensive JavaDoc comments:

- **Class-level documentation**: Purpose, design patterns used, relationships
- **Method-level documentation**: Parameters, return values, exceptions, examples
- **Package documentation**: Overview of package purpose and contents
- **Design pattern references**: Clear indication of which pattern is being used

**Examples**:

```java
/**
 * Abstract User class representing a library user.
 * Uses Strategy Pattern for fine calculation based on membership type.
 * Subclasses: Student, Faculty, Guest.
 */
public abstract class User { ... }

/**
 * Validates an email address format.
 * The email must match the pattern: username@domain.extension
 * 
 * @param email The email address to validate
 * @return true if the email is valid, false otherwise
 * 
 * @example Valid: john.doe@example.com, student123@university.edu
 * @example Invalid: john@, @example.com, john.doe@
 */
public static boolean isValidEmail(String email) { ... }
```

**Coverage**:
- All model classes
- All service classes
- All command classes
- All decorator classes
- All state classes
- All strategy classes
- All observer pattern classes
- Utility classes
- Exception classes

**Benefits**:
- Easy code understanding for new developers
- Clear API documentation
- Better IDE support with tooltips
- Facilitates code maintenance and updates
- Serves as inline documentation

## Design Patterns Implemented

### 1. **Command Pattern**
- Location: `src/command/`
- Encapsulates borrowing, returning, reserving, and canceling operations as command objects
- Classes: `BorrowCommand`, `ReturnCommand`, `ReserveCommand`, `CancelReservationCommand`

### 2. **State Pattern**
- Location: `src/model/book/state/`
- Manages book states (Available, Borrowed, Reserved)
- Classes: `AvailableState`, `BorrowedState`, `ReservedState`

### 3. **Builder Pattern**
- Location: `src/model/book/`
- Provides a flexible way to construct Book objects
- Class: `BookBuilder` (as a static inner class inside `Book.java`)

### 4. **Decorator Pattern**
- Location: `src/model/book/decorator/`
- Adds additional features to books dynamically
- Classes: `FeaturedDecorator`, `RecommendedDecorator`, `SpecialEditionDecorator`

### 5. **Strategy Pattern**
- Location: `src/model/user/fines/`
- Different fine calculation strategies for different user types
- Classes: `StudentFineStrategy`, `FacultyFineStrategy`, `GuestFineStrategy`

### 6. **Observer Pattern**
- Location: `src/service/notification/`
- Notification system for user updates
- Classes: `NotificationService`, `UserNotificationObserver`

## Project Structure

```
src/
├── Main.java
├── command/
│   ├── Command.java
│   ├── CommandInvoker.java
│   ├── BorrowCommand.java
│   ├── ReturnCommand.java
│   ├── ReserveCommand.java
│   └── CancelReservationCommand.java
├── exception/
│   ├── LibraryException.java
│   ├── BookNotFoundException.java
│   ├── UserNotFoundException.java
│   ├── InvalidOperationException.java
│   └── ValidationException.java
├── model/
│   ├── book/
│   │   ├── Book.java
│   │   ├── BasicBook.java
│   │   ├── decorator/
│   │   │   ├── BookDecorator.java
│   │   │   ├── FeaturedDecorator.java
│   │   │   ├── RecommendedDecorator.java
│   │   │   └── SpecialEditionDecorator.java
│   │   └── state/
│   │       ├── BookState.java
│   │       ├── AvailableState.java
│   │       ├── BorrowedState.java
│   │       └── ReservedState.java
│   ├── borrow/
│   │   └── BorrowRecord.java
│   ├── report/
│   │   └── Report.java
│   ├── reservation/
│   │   └── Reservation.java
│   └── user/
│       ├── User.java
│       ├── Student.java
│       ├── Faculty.java
│       ├── Guest.java
│       └── fines/
│           ├── FineStrategy.java
│           ├── StudentFineStrategy.java
│           ├── FacultyFineStrategy.java
│           └── GuestFineStrategy.java
├── service/
│   ├── LibraryManagementSystem.java
│   └── notification/
│       ├── Subject.java
│       ├── Observer.java
│       ├── NotificationService.java
│       └── UserNotificationObserver.java
└── util/
    └── ValidationUtil.java
```

## Getting Started

### Prerequisites
- Java JDK 8 or higher

### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/kalharatennakoon/smart_library_management_system.git
   cd smart_library_management_system
   ```

2. Compile the project:
   ```bash
   cd src
   javac -d ../bin Main.java
   ```

3. Run the application:
   ```bash
   cd ../bin
   java Main
   ```

## User Types and Borrowing Policies

| User Type | Borrow Limit | Loan Period | Fine Rate (LKR/day) |
|-----------|--------------|-------------|---------------------|
| Student   | 5 books      | 14 days     | 50                  |
| Faculty   | 10 books     | 30 days     | 20                  |
| Guest     | 3 books      | 7 days      | 100                 |

## Usage Examples

### Adding a Book with Builder Pattern
```java
Book book = new Book.BookBuilder("B001", "Clean Code", "Robert C. Martin")
    .category("Programming")
    .isbn("978-0132350884")
    .metadata("Edition: 1st")
    .metadata("Year: 2008")
    .build();
```

### Decorating a Book
```java
Book decoratedBook = new FeaturedDecorator(
    new RecommendedDecorator(book)
);
```

### Registering a User with Validation
```java
// Email validation: must match pattern username@domain.extension
// Contact: must be exactly 10 digits
Student student = new Student("U001", "John Doe", 
    "john.doe@university.edu", "0771234567");
```

### Fine Calculation with Strategy Pattern
```java
// Automatic fine calculation based on user type
double fine = borrowRecord.calculateFine(LocalDate.now());
// Student: LKR 50/day
// Faculty: LKR 20/day
// Guest: LKR 100/day
```

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License.

## Author
Kalhara Tennakoon (@kalharatennakoon)

## Assumptions Made

The following rules were not explicitly defined in the case study and have been implemented based on common library policies:

- **Student Borrow Limit**: Assumed to be **5** books
- **Faculty Borrow Limit**: Assumed to be **10** books
- **Guest Borrow Limit**: Assumed to be **3** books
- **Guest Loan Period**: Assumed to be **7** days

## Acknowledgments
- Design Patterns: Elements of Reusable Object-Oriented Software (Gang of Four)
- Clean Code by Robert C. Martin
- Effective Java by Joshua Bloch

