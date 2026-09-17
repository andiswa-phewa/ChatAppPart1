/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ac.iie.prog6112.chataapppart1;

/**
 *
 * @author anphewa
 */

/*
 * PROG5121 Programming 1A
 * Part 1 - Registration and Login
 *
 * IMPORTANT:
 * If NetBeans created a package statement for your project,
 * keep the SAME package statement in Login.java, Main.java
 * and LoginTest.java.
 *
 * Example:
 * package com.mycompany.chatapppart1;
 */

public class Login {

    /*
     * =========================
     * PRIVATE FIELDS
     * =========================
     *
     * These variables are private because they belong to one Login object.
     *
     * "private" supports encapsulation.
     * This means code outside this class cannot directly change these
     * variables without going through the methods that we provide.
     *
     * For example:
     * username should belong to the registered user and should not simply
     * be changed anywhere in the program without control.
     */

    // The user's first name is needed for the successful login message.
    private String firstName;

    // The user's surname/last name is also needed for the welcome message.
    private String lastName;

    // These are the details stored when the user registers.
    private String username;
    private String password;
    private String cellPhoneNumber;

    /*
     * These two fields store the details that the user enters later
     * when attempting to log in.
     *
     * We keep the registered username/password separate from the
     * entered login username/password so that we can compare them.
     */
    private String enteredUsername;
    private String enteredPassword;

    /*
     * This variable remembers whether registration was successful.
     *
     * boolean means that the variable can contain only:
     * true  = yes
     * false = no
     */
    private boolean registered;

    /*
     * This variable stores the result of the latest login attempt.
     *
     * returnLoginStatus() can then use this value to decide which
     * message should be returned.
     */
    private boolean loginSuccessful;

    /*
     * =========================
     * CONSTRUCTOR
     * =========================
     *
     * A constructor is used when creating an object.
     *
     * Example:
     * Login login = new Login("Kyle", "Smith",
     *                         "kyl_1", "Ch&&sec@ke99!",
     *                         "+27838968976");
     *
     * The constructor receives the information that was entered
     * during registration and stores it in this object.
     */
    public Login(String firstName,
                 String lastName,
                 String username,
                 String password,
                 String cellPhoneNumber) {

        /*
         * "this.firstName" means the field belonging to THIS Login object.
         *
         * "firstName" on the right-hand side is the value received
         * through the constructor parameter.
         */
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;

        /*
         * A new object has not been successfully registered or logged in yet,
         * so both values begin as false.
         */
        this.registered = false;
        this.loginSuccessful = false;
    }

    /*
     * =========================
     * checkUserName()
     * =========================
     *
     * The brief requires:
     * 1. The username must contain an underscore (_).
     * 2. The username must be no more than 5 characters long.
     *
     * The method returns a boolean:
     * true  = username follows the rules.
     * false = username does not follow the rules.
     */
    public boolean checkUserName() {

        /*
         * username != null protects the program from a NullPointerException.
         *
         * contains("_") checks whether an underscore occurs anywhere
         * in the username.
         *
         * length() <= 5 checks that there are no more than five characters.
         *
         * && means AND.
         * Therefore ALL conditions must be true.
         */
        return username != null
                && username.contains("_")
                && username.length() <= 5;
    }

    /*
     * ===============================
     * checkPasswordComplexity()
     * ===============================
     *
     * The password must:
     * - contain at least 8 characters;
     * - contain a capital letter;
     * - contain a number;
     * - contain a special character.
     */
    public boolean checkPasswordComplexity() {

        /*
         * If password is null, there is no password to validate.
         * We immediately return false.
         */
        if (password == null) {
            return false;
        }

        /*
         * Start by checking the length requirement.
         */
        boolean hasMinimumLength = password.length() >= 8;

        /*
         * These start as false because we have not inspected
         * the password characters yet.
         */
        boolean hasCapitalLetter = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        /*
         * A for loop allows us to inspect each character in the password.
         *
         * i starts at 0 because Java String positions begin at index 0.
         *
         * i < password.length() means the loop continues while there
         * are still characters to inspect.
         *
         * i++ increases i by 1 after each loop.
         */
        for (int i = 0; i < password.length(); i++) {

            /*
             * charAt(i) gets one character from the String.
             */
            char currentCharacter = password.charAt(i);

            /*
             * Character.isUpperCase(...) checks whether the current
             * character is a capital letter such as A, B or C.
             */
            if (Character.isUpperCase(currentCharacter)) {
                hasCapitalLetter = true;
            }

            /*
             * Character.isDigit(...) checks specifically for a number
             * such as 0, 1, 2, ... 9.
             *
             * Do NOT use isLetterOrDigit() for the number requirement,
             * because that would incorrectly allow a normal letter
             * to count as a number.
             */
            if (Character.isDigit(currentCharacter)) {
                hasNumber = true;
            }

            /*
             * Character.isLetterOrDigit(...) returns true when the
             * character is either a letter or a number.
             *
             * ! means NOT.
             *
             * Therefore:
             * !Character.isLetterOrDigit(...)
             * means the character is neither a letter nor a number.
             *
             * Examples include:
             * ! @ # $ % & *
             */
            if (!Character.isLetterOrDigit(currentCharacter)) {
                hasSpecialCharacter = true;
            }
        }

        /*
         * The password is valid only when ALL four conditions are true.
         */
        return hasMinimumLength
                && hasCapitalLetter
                && hasNumber
                && hasSpecialCharacter;
    }

    /*
     * ============================
     * checkCellPhoneNumber()
     * ============================
     *
     * The assessment requires a REGULAR EXPRESSION (regex)
     * and requires the researched regex to be attributed.
     *
     * IMPORTANT FOR STUDENTS:
     * Replace the placeholders below with the ACTUAL source
     * that YOU researched.
     *
     * Regex pattern adapted from:
     * Author/Website: __________________________________________
     * Page/Article: ____________________________________________
     * URL: _____________________________________________________
     * Accessed: DD Month YYYY
     *
     * Do not invent a reference.
     */

    public boolean checkCellPhoneNumber() {

        /*
         * The Java regex is:
         *
         * ^\\+27\\d{9}$
         *
         * Explanation:
         *
         * ^       = the value must start here.
         *
         * \\+     = matches the literal + symbol.
         *           In Java, backslash itself must be escaped,
         *           therefore we write \\.
         *
         * 27      = the South African international country code.
         *
         * \\d     = a digit from 0 to 9.
         *
         * {9}     = exactly nine digits must follow +27.
         *
         * $       = nothing else may appear after those digits.
         *
         * Example accepted value:
         * +27838968976
         */
        return cellPhoneNumber != null
                && cellPhoneNumber.matches("^\\+27\\d{9}$");
    }

    /*
     * =====================
     * registerUser()
     * =====================
     *
     * This method calls the validation methods instead of rewriting
     * the validation rules.
     *
     * This is important because we want ONE place responsible for
     * checking each rule.
     */
    public String registerUser() {

        /*
         * Each new registration attempt begins as unsuccessful.
         */
        registered = false;

        /*
         * The ! means NOT.
         *
         * Therefore:
         * !checkUserName()
         *
         * means:
         * "if the username is NOT valid..."
         */
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that "
                    + "your username contains an underscore and is no more "
                    + "than five characters in length.";
        }

        /*
         * We only reach this point if the username was valid.
         *
         * Now validate the password.
         */
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that "
                    + "the password contains at least eight characters, "
                    + "a capital letter, a number, and a special character.";
        }

        /*
         * We only reach this point if both username and password were valid.
         *
         * Now validate the cell phone number.
         *
         * The wording below follows the Part 1 unit-test table.
         */
        if (!checkCellPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain "
                    + "an international code; please correct the number and try again.";
        }

        /*
         * If the program reaches this point:
         * - username is valid;
         * - password is valid;
         * - cell number is valid.
         *
         * Registration is therefore considered successful.
         */
        registered = true;

        /*
         * The brief gives individual success messages for the captured
         * registration information.
         *
         * We combine the exact success messages into one String because
         * registerUser() can return only one String.
         *
         * \n creates a new line.
         */
        return "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell number successfully captured.";
    }

    /*
     * =================
     * loginUser()
     * =================
     *
     * This method checks whether the username and password entered
     * during login match the username and password stored at registration.
     */
    public boolean loginUser() {

        /*
         * If registration never succeeded, login must not succeed.
         *
         * This also prevents us from calling .equals() on values
         * that may not be ready for authentication.
         */
        if (!registered) {
            loginSuccessful = false;
            return false;
        }

        /*
         * Null checks make the method safer.
         *
         * If either login value is null, the user cannot be authenticated.
         */
        if (enteredUsername == null || enteredPassword == null) {
            loginSuccessful = false;
            return false;
        }

        /*
         * .equals() compares the CONTENTS of Java Strings.
         *
         * For example:
         *
         * "kyl_1".equals("kyl_1")
         *
         * is true because the text is the same.
         *
         * We do NOT use == to compare String contents.
         * == checks whether two references point to the same object,
         * which is not what we want when checking login text.
         */
        loginSuccessful
                = username.equals(enteredUsername)
                && password.equals(enteredPassword);

        return loginSuccessful;
    }

    /*
     * ==========================
     * returnLoginStatus()
     * ==========================
     *
     * This method returns the correct message based on the result
     * of the latest loginUser() call.
     */
    public String returnLoginStatus() {

        /*
         * If loginSuccessful is true, display first name and last name.
         *
         * The brief requires the names, NOT the username.
         */
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName
                    + " it is great to see you again.";
        }

        /*
         * If loginSuccessful is false, return the exact failed-login message.
         */
        return "Username or password incorrect, please try again.";
    }

    /*
     * ==================================
     * LOGIN DETAIL SETTERS
     * ==================================
     *
     * A setter is a method that allows us to assign a value
     * to a private field in a controlled way.
     *
     * Main will use these methods when the user enters
     * their login username and password.
     */

    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }

    /*
     * This helper method lets Main check whether registration succeeded
     * without comparing message text.
     *
     * This keeps Main independent from the wording of the messages.
     */
    public boolean isRegistered() {
        return registered;
    }
}
