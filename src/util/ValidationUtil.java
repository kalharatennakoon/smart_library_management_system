package util;

import exception.ValidationException;

/**
 * ValidationUtil - Utility class for validating user input data.
 * Provides methods to validate email addresses, contact numbers, and other fields.
 * 
 * Design Pattern: Utility Class Pattern
 * Centralizes validation logic to ensure consistency across the application.
 */
public class ValidationUtil {
    
    // Regular expression patterns for validation
    private static final String EMAIL_PATTERN = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
    private static final String CONTACT_NUMBER_PATTERN = "^\\d{10}$";
    
    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private ValidationUtil() {
        throw new UnsupportedOperationException("ValidationUtil is a utility class and cannot be instantiated");
    }
    
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
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return email.matches(EMAIL_PATTERN);
    }
    
    /**
     * Validates an email address and throws an exception if invalid.
     * 
     * @param email The email address to validate
     * @throws ValidationException if the email format is invalid
     */
    public static void validateEmail(String email) throws ValidationException {
        if (!isValidEmail(email)) {
            throw new ValidationException("email", "Invalid email format. Expected format: username@domain.extension");
        }
    }
    
    /**
     * Validates a contact number format.
     * The contact number must contain exactly 10 digits.
     * 
     * @param contactNumber The contact number to validate
     * @return true if the contact number is valid, false otherwise
     * 
     * @example Valid: 0771234567, 1234567890
     * @example Invalid: 123456789 (9 digits), 12345678901 (11 digits), 077-123-4567 (contains hyphens)
     */
    public static boolean isValidContactNumber(String contactNumber) {
        if (contactNumber == null || contactNumber.trim().isEmpty()) {
            return false;
        }
        return contactNumber.matches(CONTACT_NUMBER_PATTERN);
    }
    
    /**
     * Validates a contact number and throws an exception if invalid.
     * 
     * @param contactNumber The contact number to validate
     * @throws ValidationException if the contact number format is invalid
     */
    public static void validateContactNumber(String contactNumber) throws ValidationException {
        if (!isValidContactNumber(contactNumber)) {
            throw new ValidationException("contactNumber", "Contact number must contain exactly 10 digits");
        }
    }
    
    /**
     * Validates that a string is not null or empty.
     * 
     * @param value The string value to validate
     * @param fieldName The name of the field being validated (for error messages)
     * @return true if the value is not null or empty, false otherwise
     */
    public static boolean isNotEmpty(String value, String fieldName) {
        return value != null && !value.trim().isEmpty();
    }
    
    /**
     * Validates that a string is not null or empty and throws an exception if invalid.
     * 
     * @param value The string value to validate
     * @param fieldName The name of the field being validated
     * @throws ValidationException if the value is null or empty
     */
    public static void validateNotEmpty(String value, String fieldName) throws ValidationException {
        if (!isNotEmpty(value, fieldName)) {
            throw new ValidationException(fieldName, "Field cannot be null or empty");
        }
    }
    
    /**
     * Validates that a numeric value is positive.
     * 
     * @param value The numeric value to validate
     * @param fieldName The name of the field being validated (for error messages)
     * @return true if the value is positive, false otherwise
     */
    public static boolean isPositive(double value, String fieldName) {
        return value > 0;
    }
    
    /**
     * Validates that a numeric value is positive and throws an exception if invalid.
     * 
     * @param value The numeric value to validate
     * @param fieldName The name of the field being validated
     * @throws ValidationException if the value is not positive
     */
    public static void validatePositive(double value, String fieldName) throws ValidationException {
        if (!isPositive(value, fieldName)) {
            throw new ValidationException(fieldName, "Value must be positive");
        }
    }
    
    /**
     * Gets a user-friendly error message for email validation failure.
     * 
     * @return Error message string
     */
    public static String getEmailErrorMessage() {
        return "Invalid email format. Please enter a valid email address (e.g., user@example.com)";
    }
    
    /**
     * Gets a user-friendly error message for contact number validation failure.
     * 
     * @return Error message string
     */
    public static String getContactNumberErrorMessage() {
        return "Invalid contact number. Contact number must contain exactly 10 digits";
    }
}
