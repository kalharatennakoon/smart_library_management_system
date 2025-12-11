package util;

import exception.ValidationException;

// ValidationUtil - Utility class for validating user input data
public class ValidationUtil {
    
    // Regular expression patterns for validation
    private static final String EMAIL_PATTERN = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
    private static final String CONTACT_NUMBER_PATTERN = "^\\d{10}$";
    
    // Private constructor to prevent instantiation
    private ValidationUtil() {
        throw new UnsupportedOperationException("ValidationUtil is a utility class and cannot be instantiated");
    }
    
    // Validates an email address format
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.matches(EMAIL_PATTERN);
    }
    
    // Validates an email address and throws an exception if invalid
    public static void validateEmail(String email) throws ValidationException {
        if (!isValidEmail(email)) {
            throw new ValidationException("email", "Invalid email format. Expected format: username@domain.extension");
        }
    }
    
    // Validates a contact number format
    public static boolean isValidContactNumber(String contactNumber) {
        if (contactNumber == null || contactNumber.trim().isEmpty()) {
            return false;
        }
        return contactNumber.matches(CONTACT_NUMBER_PATTERN);
    }
    
    // Validates a contact number and throws an exception if invalid
    public static void validateContactNumber(String contactNumber) throws ValidationException {
        if (!isValidContactNumber(contactNumber)) {
            throw new ValidationException("contactNumber", "Contact number must contain exactly 10 digits");
        }
    }
    
    // Validates that a string is not null or empty
    public static boolean isNotEmpty(String value, String fieldName) {
        return value != null && !value.trim().isEmpty();
    }
    
    // Validates that a string is not null or empty and throws an exception if invalid
    public static void validateNotEmpty(String value, String fieldName) throws ValidationException {
        if (!isNotEmpty(value, fieldName)) {
            throw new ValidationException(fieldName, "Field cannot be null or empty");
        }
    }
    
    // Validates that a numeric value is positive
    public static boolean isPositive(double value, String fieldName) {
        return value > 0;
    }
    
    // Validates that a numeric value is positive and throws an exception if invalid
    public static void validatePositive(double value, String fieldName) throws ValidationException {
        if (!isPositive(value, fieldName)) {
            throw new ValidationException(fieldName, "Value must be positive");
        }
    }
    
    // Gets a user-friendly error message for email validation failure
    public static String getEmailErrorMessage() {
        return "Invalid email format. Please enter a valid email address (e.g., user@example.com)";
    }
    
    // Gets a user-friendly error message for contact number validation failure
    public static String getContactNumberErrorMessage() {
        return "Invalid contact number. Contact number must contain exactly 10 digits";
    }
    
    // Removes the class prefix (e.g., "K2558859_") from a class name
    public static String removeClassPrefix(String className) {
        if (className == null || className.isEmpty()) {
            return className;
        }
        // Remove prefix pattern like "K2558859_"
        return className.replaceFirst("^K\\d+_", "");
    }
}
