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
│   └── LibraryException.java
├── model/
│   ├── book/
│   │   ├── Book.java
│   │   ├── BasicBook.java
│   │   ├── decorator/
│   │   └── state/
│   ├── borrow/
│   ├── report/
│   ├── reservation/
│   └── user/
│       ├── User.java
│       ├── Student.java
│       ├── Faculty.java
│       ├── Guest.java
│       └── fines/
│           ├── FineStrategy.java
│           ├── StudentFineStrategy.java
│           └── FacultyFineStrategy.java
│           └── GuestFineStrategy.java
└── service/
    ├── LibraryManagementSystem.java
    └── notification/
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
   javac Main.java
   ```

3. Run the application:
   ```bash
   java Main
   ```

## User Types and Fine Strategies

- **Students**: Standard fine rates
- **Faculty**: Reduced fine rates with extended borrowing periods
- **Guests**: Higher fine rates with limited borrowing privileges

## Author

## Assumptions Made

The following rules were not explicitly defined in the case study and have been implemented based on common library policies. These are clearly marked in the source code for reference.

- **Student Borrow Limit**: Assumed to be **5** books.
- **Faculty Borrow Limit**: Assumed to be **10** books.
- **Guest Borrow Limit**: Assumed to be **2** books.
- **Guest Loan Period**: Assumed to be **7** days.

Kalhara Tennakoon
- GitHub: [kalharatennakoon](https://github.com/kalharatennakoon)
