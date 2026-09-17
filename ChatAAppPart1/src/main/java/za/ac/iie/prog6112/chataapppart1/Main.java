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

    public static void main(String[] args) {

        /*
         * Scanner allows us to read information typed by the user
         * through the console.
         *
         * System.in represents keyboard input.
         */
        Scanner input = new Scanner(System.in);


        /*
         * ========================================================
         * REGISTRATION
         * ========================================================
         *
         * Main.java handles interaction with the user.
         *
         * It does NOT contain the actual validation rules.
         * Those rules belong inside Login.java.
         */

        System.out.println("=== WELCOME TO CHATAPP ===");


        // Ask for the user's first name.
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();


        // Ask for the user's surname.
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();


        // Ask for the username that will be registered.
        System.out.print("Enter a username: ");
        String username = input.nextLine();


        // Ask for the password that will be registered.
        System.out.print("Enter a password: ");
        String password = input.nextLine();


        // Ask for the South African cellphone number.
        System.out.print(
                "Enter your South African cell phone number (+27...): "
        );

        String cellPhoneNumber = input.nextLine();


        /*
         * Create the Login object and send all registration
         * information to its constructor.
         */
        Login login = new Login(
                firstName,
                lastName,
                username,
                password,
                cellPhoneNumber
        );


        /*
         * registerUser() performs the registration validation
         * inside the Login class.
         */
        String registrationMessage = login.registerUser();


        // Display the result returned by registerUser().
        System.out.println();
        System.out.println(registrationMessage);


        /*
         * If registration failed, Part 1 does not require us
         * to keep asking the user again.
         *
         * Therefore we simply stop here.
         */
        if (!login.isRegistered()) {

            System.out.println();
            System.out.println("Registration was not completed.");

            input.close();

            // return ends the main() method.
            return;
        }


        /*
         * ========================================================
         * LOGIN
         * ========================================================
         *
         * This section is reached ONLY when registration succeeded.
         */

        System.out.println();
        System.out.println("=== LOGIN ===");


        /*
         * Ask for the username again.
         *
         * This allows the program to compare the login username
         * with the username that was stored during registration.
         */
        System.out.print("Enter your username to log in: ");
        String loginUsername = input.nextLine();


        // Ask for the password again.
        System.out.print("Enter your password to log in: ");
        String loginPassword = input.nextLine();


        /*
         * Store the login attempt inside the Login object.
         */
        login.setEnteredUsername(loginUsername);
        login.setEnteredPassword(loginPassword);


        /*
         * loginUser() performs the comparison.
         *
         * true means the username/password matched.
         * false means they did not match.
         */
        login.loginUser();


        /*
         * returnLoginStatus() returns either:
         *
         * Welcome <first name>, <last name> ...
         *
         * OR
         *
         * Username or password incorrect...
         */
        String loginMessage = login.returnLoginStatus();


        // Display the final authentication message.
        System.out.println();
        System.out.println(loginMessage);


        // Close Scanner once all input is finished.
        input.close();
    }
}