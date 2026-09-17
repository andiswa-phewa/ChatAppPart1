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
 */

import java.util.Scanner;

public class Main {

    /*
     * main() is the starting point of a normal Java console application.
     *
     * When we run Main.java, Java begins executing the statements
     * inside this method.
     */
    public static void main(String[] args) {

        /*
         * Scanner allows us to collect information typed by the user
         * in the console.
         *
         * System.in means that the information comes from the keyboard.
         */
        Scanner input = new Scanner(System.in);

        /*
         * =========================
         * REGISTRATION SECTION
         * =========================
         *
         * Main is responsible for asking questions.
         *
         * Main should NOT contain the actual username/password/phone
         * validation rules. Those rules belong inside Login.java.
         */

        System.out.println("=== PROG5121 PART 1 REGISTRATION ===");

        /*
         * Ask for the first name.
         *
         * nextLine() reads the complete line typed by the user
         * and stores it as a String.
         */
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        // Ask for the user's surname/last name.
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        // Ask for the registration username.
        System.out.print("Enter a username: ");
        String username = input.nextLine();

        // Ask for the registration password.
        System.out.print("Enter a password: ");
        String password = input.nextLine();

        // Ask for the South African cell number in international format.
        System.out.print("Enter your South African cell phone number (+27...): ");
        String cellPhoneNumber = input.nextLine();

        /*
         * Create ONE Login object containing the registration information.
         *
         * The Login class will be responsible for validating the information.
         */
        Login login = new Login(
                firstName,
                lastName,
                username,
                password,
                cellPhoneNumber
        );

        /*
         * Call registerUser().
         *
         * The returned String contains the appropriate registration feedback.
         */
        String registrationMessage = login.registerUser();

        System.out.println();
        System.out.println(registrationMessage);

        /*
         * IMPORTANT:
         * Do not continue to login if registration failed.
         *
         * isRegistered() returns true only after all registration
         * validation checks have passed.
         */
        if (!login.isRegistered()) {

            System.out.println();
            System.out.println("Registration was not completed.");

            /*
             * We no longer need Scanner because the program is ending.
             */
            input.close();

            /*
             * return ends the main() method immediately.
             */
            return;
        }

        /*
         * =================
         * LOGIN SECTION
         * =================
         *
         * The program reaches this point only after successful registration.
         */

        System.out.println();
        System.out.println("=== LOGIN ===");

        /*
         * Ask the user to enter the username again.
         *
         * This is intentionally separate from the stored registration username.
         * We need two values so that Login can compare them.
         */
        System.out.print("Enter your username to log in: ");
        String loginUsername = input.nextLine();

        // Ask the user to enter the password again.
        System.out.print("Enter your password to log in: ");
        String loginPassword = input.nextLine();

        /*
         * Store the login attempt inside the Login object.
         */
        login.setEnteredUsername(loginUsername);
        login.setEnteredPassword(loginPassword);

        /*
         * loginUser() performs the actual comparison.
         *
         * We call it before returnLoginStatus() so that the Login object
         * knows whether the latest attempt succeeded or failed.
         */
        boolean loginResult = login.loginUser();

        /*
         * loginResult is useful when explaining the flow:
         *
         * true  = login details matched.
         * false = login details did not match.
         *
         * The actual user-friendly message is returned by
         * returnLoginStatus().
         */
        System.out.println();

        String loginMessage = login.returnLoginStatus();

        System.out.println(loginMessage);

        /*
         * Scanner should be closed when the application no longer needs it.
         */
        input.close();
    }
}