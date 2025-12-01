package model.user;

/**
 * K2558859_Librarian class - Represents a librarian in the library system.
 * Librarians are system operators who manage books, users, and reports.
 * This class is separate from the User hierarchy as librarians do not borrow books.
 */
public class K2558859_Librarian {
    private String librarianId;
    private String name;
    private String email;
    private String contactNumber;

    /**
     * Constructor for K2558859_Librarian.
     * @param librarianId Unique identifier for the librarian
     * @param name Name of the librarian
     * @param email Email address of the librarian
     * @param contactNumber Contact number of the librarian
     */
    public K2558859_Librarian(String librarianId, String name, String email, String contactNumber) {
        this.librarianId = librarianId;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    // Getters
    public String getLibrarianId() {
        return librarianId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    // Setters (optional, for updates)
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "librarianId='" + librarianId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
