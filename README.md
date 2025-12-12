# Smart Library Management System

A comprehensive library management system built with Java, implementing various design patterns to provide a robust and scalable solution for managing books, users, borrowing, and reservations.

## Features

- **Book Management**: Add, update, and manage books with various attributes
- **User Management**: Support for different user types (Students, Faculty, Guests, Librarians)
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
- Classes: `K2558859_BorrowCommand`, `K2558859_ReturnCommand`, `K2558859_ReserveCommand`, `K2558859_CancelReservationCommand`

### 2. **State Pattern**
- Location: `src/model/book/state/`
- Manages book states (Available, Borrowed, Reserved)
- Classes: `K2558859_AvailableState`, `K2558859_BorrowedState`, `K2558859_ReservedState`

### 3. **Builder Pattern**
- Location: `src/model/book/`
- Provides a flexible way to construct Book objects with metadata
- Class: `K2558859_BookBuilder` (as a static inner class inside `K2558859_Book.java`)

### 4. **Decorator Pattern**
- Location: `src/model/book/decorator/`
- Adds additional features to books dynamically
- Classes: `K2558859_FeaturedDecorator`, `K2558859_RecommendedDecorator`, `K2558859_SpecialEditionDecorator`

### 5. **Strategy Pattern**
- Location: `src/model/user/fines/`
- Different fine calculation strategies for different user types
- Classes: `K2558859_StudentFineStrategy`, `K2558859_FacultyFineStrategy`, `K2558859_GuestFineStrategy`

### 6. **Observer Pattern**
- Location: `src/service/notification/`
- Notification system for user updates
- Classes: `K2558859_NotificationService`, `K2558859_UserNotificationObserver`

## Project Structure

```
src/
├── Main.java
├── reset.sh
├── command/
│   ├── K2558859_Command.java
│   ├── K2558859_CommandInvoker.java
│   ├── K2558859_BorrowCommand.java
│   ├── K2558859_ReturnCommand.java
│   ├── K2558859_ReserveCommand.java
│   └── K2558859_CancelReservationCommand.java
├── exception/
│   ├── LibraryException.java
│   ├── BookNotFoundException.java
│   ├── UserNotFoundException.java
│   ├── InvalidOperationException.java
│   └── ValidationException.java
├── model/
│   ├── book/
│   │   ├── K2558859_Book.java
│   │   ├── K2558859_BasicBook.java
│   │   ├── decorator/
│   │   │   ├── K2558859_BookDecorator.java
│   │   │   ├── K2558859_FeaturedDecorator.java
│   │   │   ├── K2558859_RecommendedDecorator.java
│   │   │   └── K2558859_SpecialEditionDecorator.java
│   │   └── state/
│   │       ├── K2558859_BookState.java
│   │       ├── K2558859_AvailableState.java
│   │       ├── K2558859_BorrowedState.java
│   │       └── K2558859_ReservedState.java
│   ├── borrow/
│   │   └── K2558859_BorrowRecord.java
│   ├── report/
│   │   └── K2558859_Report.java
│   ├── reservation/
│   │   └── K2558859_Reservation.java
│   └── user/
│       ├── K2558859_User.java
│       ├── K2558859_Student.java
│       ├── K2558859_Faculty.java
│       ├── K2558859_Guest.java
│       ├── K2558859_Librarian.java
│       └── fines/
│           ├── K2558859_FineStrategy.java
│           ├── K2558859_StudentFineStrategy.java
│           ├── K2558859_FacultyFineStrategy.java
│           └── K2558859_GuestFineStrategy.java
├── service/
│   ├── K2558859_LibraryManagementSystem.java
│   └── notification/
│       ├── K2558859_Subject.java
│       ├── K2558859_Observer.java
│       ├── K2558859_NotificationService.java
│       └── K2558859_UserNotificationObserver.java
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

4. (Optional) Reset compiled files:
   ```bash
   ./reset.sh
   ```
   This script removes all `.class` files from the project.

## User Types and Borrowing Policies

| User Type | Borrow Limit | Loan Period | Fine Rate (LKR/day) |
|-----------|--------------|-------------|---------------------|
| Student   | 5 books      | 14 days     | 50                  |
| Faculty   | 10 books     | 30 days     | 20                  |
| Guest     | 3 books      | 7 days      | 100                 |


## License
This project is licensed under the MIT License.
