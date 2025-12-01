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
   javac Main.java
   ```

3. Run the application:
   ```bash
   java Main
   ```

## User Types and Borrowing Policies

| User Type | Borrow Limit | Loan Period | Fine Rate (LKR/day) |
|-----------|--------------|-------------|---------------------|
| Student   | 5 books      | 14 days     | 50                  |
| Faculty   | 10 books     | 30 days     | 20                  |
| Guest     | 3 books      | 7 days      | 100                 |



## License
This project is licensed under the MIT License.
