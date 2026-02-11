package coursework;

/**
 * The Name class represents the name of a player.
 * 
 * <p>
 * It stores the first name and last name separately and provides
 * utility methods to retrieve the full name and initials.
 * </p>
 */
public class Name {
    private String firstName;
    private String lastName;

    /**
     * Constructs a Name object with a first and last name.
     *
     * @param firstName the player's first name
     * @param lastName  the player's last name
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns the first name.
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name.
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the full name in the format "FirstName LastName".
     *
     * @return full name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Returns the initials of the name in upper case.
     *
     * @return initials (e.g., "JS" for John Smith)
     */
    public String getInitials() {
        return ("" + firstName.charAt(0) + lastName.charAt(0)).toUpperCase();
    }
}