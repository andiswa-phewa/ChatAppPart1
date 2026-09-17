# PROG5121 Part 1 - Registration and Login

## Student Details
**Student Name:** Andiswa Phewa  
**Student Number:** ST12345678  
**Module:** PROG5121 - Programming 1A  
**Assessment:** Part 1 - Registration and Login Feature  

## Project Overview
This project is a Java console application that implements the registration and login functionality required for PROG5121 Part 1. The application allows a user to register using a username, password and South African cell phone number, and then log in using the registered details.

## Features
- Username validation
- Password complexity validation
- South African cell phone number validation using a regular expression
- User registration
- User login authentication
- First name and last name welcome message
- JUnit unit testing for valid and invalid inputs

## Technologies Used
- Java
- Apache Maven
- NetBeans
- JUnit
- Git and GitHub

## Project Structure
- `Main.java` - Handles user input and runs the console application.
- `Login.java` - Contains the registration, validation and login logic.
- `LoginTest.java` - Contains the JUnit tests for Part 1 functionality.

## How to Run
1. Open the Maven project in NetBeans.
2. Run `Main.java`.
3. Follow the registration and login prompts in the console.

## Testing
Unit tests are contained in `LoginTest.java`. The tests check valid and invalid usernames, passwords and cell phone numbers, as well as successful and failed login functionality and the required system messages.

## Regex Reference
The South African cell phone regular expression used in this project is:

`^\\+27\\d{9}$`

The pattern was developed using the South African numbering requirements published by the Independent Communications Authority of South Africa (ICASA). ICASA identifies South African telephone numbers as 10-digit national numbers, while the South African numbering plan uses country code `+27` for international format. The national leading `0` is removed when the country code is used, resulting in `+27` followed by nine digits.

**Source:** Independent Communications Authority of South Africa (ICASA), *Numbering*. Accessed 17 September 2026.

## Video Presentation
**Part 1 Video:** Replace with your final unlisted YouTube video link.



