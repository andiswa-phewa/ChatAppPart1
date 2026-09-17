/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog6112.chataapppart1;

/**
 *
 * @author anphewa
 */
public class Login {

    /*
     * ============================================================
     * FIELDS
     * ============================================================
     *
     * The fields are private because they belong to this Login object.
     * This is called encapsulation.
     *
     * Code outside this class cannot directly change these values.
     */

    // The user's first and last name are required for the welcome message.
    private String firstName;
    private String lastName;

    // These are the details entered during registration.
    private String username;
    private String password;
    private String cellPhoneNumber;

    // These are entered later when the user attempts to log in.
    private String enteredUsername;
    private String enteredPassword;

    // Stores whether registration was successful.
    private boolean registered;

    // Stores whether the most recent login attempt was successful.
    private boolean loginSuccessful;


    /*
     * ============================================================
     * CONSTRUCTOR
     * ============================================================
     *
     * The constructor receives the registration information
     * when a new Login object is created.
     */
    public Login(String firstName,
                 String lastName,
                 String username,
                 String password,
                 String cellPhoneNumber) {

        // "this" refers to the fields belonging to this object.
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;

        // A new user has not yet been registered or logged in.
        this.registered = false;
        this.loginSuccessful = false;
    }


    /*
     * ============================================================
     * checkUserName()
     * ============================================================
     *
     * The username must:
     * 1. Contain an underscore (_)
     * 2. Be no more than 5 characters long
     *
     * A boolean method returns either true or false.
     */
    public boolean checkUserName() {

        /*
         * username != null prevents a NullPointerException.
         *
         * contains("_") checks for the underscore.
         *
         * length() <= 5 checks the maximum length.
         *
         * && means AND, so every condition must be true.
         */
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }


    /*
     * ============================================================
     * checkPasswordComplexity()
     * ============================================================
     *
     * A valid password must:
     * - contain at least 8 characters
     * - contain a capital letter
     * - contain a number
     * - contain a special character
     */
    public boolean checkPasswordComplexity() {

        // A null password cannot be valid.
        if (password == null) {
            return false;
        }

        // Check the minimum length.
        boolean hasMinimumLength = password.length() >= 8;

        /*
         * These begin as false because we have not yet found
         * the required characters.
         */
        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        /*
         * This loop examines every character in the password.
         *
         * i starts at 0 because Java String indexes begin at 0.
         */
        for (int i = 0; i < password.length(); i++) {

            // Get one character from the password.
            char currentCharacter = password.charAt(i);

            // Check whether the character is an uppercase letter.
            if (Character.isUpperCase(currentCharacter)) {
                hasCapitalLetter = true;
            }

            /*
             * isDigit() checks specifically for a number.
             *
             * Do not use isLetterOrDigit() here because that would
             * allow normal letters to count as numbers.
             */
            if (Character.isDigit(currentCharacter)) {
                hasNumber = true;
            }

            /*
             * isLetterOrDigit() is true for letters and numbers.
             *
             * ! means NOT.
             *
             * Therefore this condition is true when the character
             * is neither a letter nor a number.
             *
             * Examples: ! @ # $ % & *
             */
            if (!Character.isLetterOrDigit(currentCharacter)) {
                hasSpecialCharacter = true;
            }
        }

        // ALL four password requirements must be true.
        return hasMinimumLength
                && hasCapitalLetter
                && hasNumber
                && hasSpecialCharacter;
    }


    /*
     * ============================================================
     * checkCellPhoneNumber()
     * ============================================================
     *
     * The PoE specifically requires a researched regular expression.
     *
     * STUDENT MUST REPLACE THESE PLACEHOLDERS WITH THEIR REAL SOURCE:
     *
     * Regex adapted from:
     * Author/Website: ___________________________________________
     * Page/Article: _____________________________________________
     * URL: ______________________________________________________
     * Accessed: DD Month YYYY
     *
     * Do not invent a reference.
     */
    public boolean checkCellPhoneNumber() {

        /*
         * Regex:
         *
         * ^\\+27\\d{9}$
         *
         * ^       = beginning of the String
         * \\+     = literal + symbol
         * 27      = South African international country code
         * \\d     = a digit from 0 to 9
         * {9}     = exactly 9 digits
         * $       = end of the String
         *
         * Example that passes:
         * +27838968976
         */
        return cellPhoneNumber != null
                && cellPhoneNumber.matches("^\\+27\\d{9}$");
    }


    /*
     * ============================================================
     * registerUser()
     * ============================================================
     *
     * This method calls the validation methods.
     *
     * We do NOT rewrite the validation rules here.
     */
    public String registerUser() {

        // Start each registration attempt as unsuccessful.
        registered = false;

        // First check the username.
        if (!checkUserName()) {

            return "Username is not correctly formatted; please ensure that "
                    + "your username contains an underscore and is no more "
                    + "than five characters in length.";
        }

        // If username is valid, check the password.
        if (!checkPasswordComplexity()) {

            return "Password is not correctly formatted; please ensure that "
                    + "the password contains at least eight characters, "
                    + "a capital letter, a number, and a special character.";
        }

        // If username and password are valid, check the cellphone number.
        if (!checkCellPhoneNumber()) {

            return "Cell number is incorrectly formatted or does not contain "
                    + "an international code; please correct the number and try again.";
        }

        /*
         * If Java reaches this point, all three registration
         * requirements have passed.
         */
        registered = true;

        /*
         * These are the success messages supplied by the PoE.
         *
         * \n starts a new line.
         */
        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell number successfully captured.";
    }


    /*
     * ============================================================
     * loginUser()
     * ============================================================
     *
     * The login username and password must match the details
     * stored during registration.
     */
    public boolean loginUser() {

        /*
         * Do not allow login if registration was unsuccessful.
         */
        if (!registered) {
            loginSuccessful = false;
            return false;
        }

        /*
         * These checks prevent NullPointerException.
         */
        if (enteredUsername == null || enteredPassword == null) {
            loginSuccessful = false;
            return false;
        }

        /*
         * .equals() compares the CONTENT of Strings.
         *
         * We use:
         *
         * username.equals(enteredUsername)
         *
         * instead of:
         *
         * username == enteredUsername
         *
         * because == compares object references rather than
         * the actual text stored inside the Strings.
         */
        loginSuccessful
                = username.equals(enteredUsername)
                && password.equals(enteredPassword);

        return loginSuccessful;
    }


    /*
     * ============================================================
     * returnLoginStatus()
     * ============================================================
     *
     * Returns the required message after loginUser() has run.
     */
    public String returnLoginStatus() {

        // Successful login.
        if (loginSuccessful) {

            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }

        // Failed login.
        return "Username or password incorrect, please try again.";
    }


    /*
     * ============================================================
     * SETTERS FOR LOGIN DETAILS
     * ============================================================
     *
     * These methods allow Main.java to store the username and
     * password entered during LOGIN without directly accessing
     * the private fields.
     */

    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }


    /*
     * This helper method allows Main to determine whether the
     * registration succeeded.
     *
     * It avoids checking the text of a message to make decisions.
     */
    public boolean isRegistered() {
        return registered;
    }
}