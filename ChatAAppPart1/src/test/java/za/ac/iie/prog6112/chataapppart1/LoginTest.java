/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package za.ac.iie.prog6112.chataapppart1;

/*
 * PROG5121 Programming 1A
 * Part 1 - Unit Tests
 *
 * This file belongs under:
 *
 * src/test/java
 *
 * If your project uses a package, LoginTest must use the
 * same package as Login.
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    /*
     * This variable will store a normal valid Login object
     * that several tests can reuse.
     */
    private Login validLogin;

    /*
     * @Before tells JUnit:
     *
     * "Run this method BEFORE every individual @Test method."
     *
     * This gives every test a fresh Login object.
     *
     * That is important because one test should not accidentally
     * change the result of another test.
     */
    @Before
    public void setUp() {

        /*
         * This object uses the exact valid test data supplied
         * by the Part 1 assessment.
         */
        validLogin = new Login(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "+27838968976"
        );
    }

    /*
     * =====================================================
     * USERNAME TEST 1 - VALID USERNAME
     * =====================================================
     *
     * @Test tells JUnit that this method is a unit test.
     */
    @Test
    public void testUsernameCorrectlyFormatted() {

        /*
         * ARRANGE:
         * Prepare the object/data required for the test.
         *
         * validLogin was already prepared by setUp().
         */

        /*
         * ACT:
         * Call the method that we want to test.
         */
        boolean actualResult = validLogin.checkUserName();

        /*
         * ASSERT:
         * Check whether the actual result matches what we expected.
         *
         * assertTrue() passes only if the supplied boolean is true.
         */
        assertTrue(actualResult);
    }

    /*
     * =====================================================
     * USERNAME TEST 2 - INVALID USERNAME
     * =====================================================
     */
    @Test
    public void testUsernameIncorrectlyFormatted() {

        // ARRANGE
        Login invalidLogin = new Login(
                "Kyle",
                "Smith",
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        // ACT
        boolean actualResult = invalidLogin.checkUserName();

        /*
         * assertFalse() passes only if the supplied boolean is false.
         */
        assertFalse(actualResult);
    }

    /*
     * =====================================================
     * PASSWORD TEST 1 - VALID PASSWORD
     * =====================================================
     */
    @Test
    public void testPasswordMeetsComplexityRequirements() {

        // ARRANGE: validLogin already contains Ch&&sec@ke99!

        // ACT
        boolean actualResult = validLogin.checkPasswordComplexity();

        // ASSERT
        assertTrue(actualResult);
    }

    /*
     * =====================================================
     * PASSWORD TEST 2 - INVALID PASSWORD
     * =====================================================
     */
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {

        // ARRANGE
        Login invalidLogin = new Login(
                "Kyle",
                "Smith",
                "kyl_1",
                "password",
                "+27838968976"
        );

        // ACT
        boolean actualResult = invalidLogin.checkPasswordComplexity();

        // ASSERT
        assertFalse(actualResult);
    }

    /*
     * =====================================================
     * CELL PHONE TEST 1 - VALID NUMBER
     * =====================================================
     */
    @Test
    public void testCellPhoneCorrectlyFormatted() {

        // ARRANGE: validLogin contains +27838968976

        // ACT
        boolean actualResult = validLogin.checkCellPhoneNumber();

        // ASSERT
        assertTrue(actualResult);
    }

    /*
     * =====================================================
     * CELL PHONE TEST 2 - INVALID NUMBER
     * =====================================================
     */
    @Test
    public void testCellPhoneIncorrectlyFormatted() {

        // ARRANGE
        Login invalidLogin = new Login(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553"
        );

        // ACT
        boolean actualResult = invalidLogin.checkCellPhoneNumber();

        // ASSERT
        assertFalse(actualResult);
    }

    /*
     * =====================================================
     * REGISTRATION MESSAGE - INVALID USERNAME
     * =====================================================
     */
    @Test
    public void testInvalidUsernameRegistrationMessage() {

        // ARRANGE
        Login invalidLogin = new Login(
                "Kyle",
                "Smith",
                "kyle!!!!!!!",
                "Ch&&sec@ke99!",
                "+27838968976"
        );

        /*
         * Expected contains the EXACT wording required by the brief.
         */
        String expected
                = "Username is not correctly formatted; please ensure that "
                + "your username contains an underscore and is no more "
                + "than five characters in length.";

        // ACT
        String actual = invalidLogin.registerUser();

        /*
         * assertEquals() compares the expected value with the actual value.
         *
         * For Strings, every character matters:
         * spelling, spaces and punctuation.
         */
        assertEquals(expected, actual);
    }

    /*
     * =====================================================
     * REGISTRATION MESSAGE - INVALID PASSWORD
     * =====================================================
     */
    @Test
    public void testInvalidPasswordRegistrationMessage() {

        // ARRANGE
        Login invalidLogin = new Login(
                "Kyle",
                "Smith",
                "kyl_1",
                "password",
                "+27838968976"
        );

        String expected
                = "Password is not correctly formatted; please ensure that "
                + "the password contains at least eight characters, "
                + "a capital letter, a number, and a special character.";

        // ACT
        String actual = invalidLogin.registerUser();

        // ASSERT
        assertEquals(expected, actual);
    }

    /*
     * =====================================================
     * REGISTRATION MESSAGE - INVALID CELL NUMBER
     * =====================================================
     */
    @Test
    public void testInvalidCellPhoneRegistrationMessage() {

        // ARRANGE
        Login invalidLogin = new Login(
                "Kyle",
                "Smith",
                "kyl_1",
                "Ch&&sec@ke99!",
                "08966553"
        );

        /*
         * This uses the wording from the official unit-test table.
         */
        String expected
                = "Cell number is incorrectly formatted or does not contain "
                + "an international code; please correct the number and try again.";

        // ACT
        String actual = invalidLogin.registerUser();

        // ASSERT
        assertEquals(expected, actual);
    }

    /*
     * =====================================================
     * REGISTRATION MESSAGE - SUCCESS
     * =====================================================
     */
    @Test
    public void testSuccessfulRegistrationMessages() {

        // ARRANGE: validLogin already has valid registration values.

        String expected
                = "Username successfully captured.\n"
                + "Password successfully captured.\n"
                + "Cell number successfully captured.";

        // ACT
        String actual = validLogin.registerUser();

        // ASSERT
        assertEquals(expected, actual);
    }

    /*
     * =====================================================
     * LOGIN TEST - SUCCESSFUL LOGIN RETURNS TRUE
     * =====================================================
     */
    @Test
    public void testLoginSuccessfulReturnsTrue() {

        /*
         * ARRANGE:
         * Registration must succeed before login is allowed.
         */
        validLogin.registerUser();

        /*
         * Enter the SAME username and password used during registration.
         */
        validLogin.setEnteredUsername("kyl_1");
        validLogin.setEnteredPassword("Ch&&sec@ke99!");

        // ACT
        boolean actualResult = validLogin.loginUser();

        // ASSERT
        assertTrue(actualResult);
    }

    /*
     * =====================================================
     * LOGIN TEST - FAILED LOGIN RETURNS FALSE
     * =====================================================
     */
    @Test
    public void testLoginFailedReturnsFalse() {

        // ARRANGE
        validLogin.registerUser();

        /*
         * These values are intentionally wrong.
         */
        validLogin.setEnteredUsername("wrong");
        validLogin.setEnteredPassword("wrong");

        // ACT
        boolean actualResult = validLogin.loginUser();

        // ASSERT
        assertFalse(actualResult);
    }

    /*
     * =====================================================
     * SUCCESSFUL LOGIN MESSAGE
     * =====================================================
     */
    @Test
    public void testSuccessfulLoginMessage() {

        // ARRANGE
        validLogin.registerUser();

        validLogin.setEnteredUsername("kyl_1");
        validLogin.setEnteredPassword("Ch&&sec@ke99!");

        /*
         * loginUser() must run before returnLoginStatus()
         * because it calculates the authentication result.
         */
        validLogin.loginUser();

        String expected
                = "Welcome Kyle, Smith it is great to see you again.";

        // ACT
        String actual = validLogin.returnLoginStatus();

        // ASSERT
        assertEquals(expected, actual);
    }

    /*
     * =====================================================
     * FAILED LOGIN MESSAGE
     * =====================================================
     */
    @Test
    public void testFailedLoginMessage() {

        // ARRANGE
        validLogin.registerUser();

        validLogin.setEnteredUsername("wrong");
        validLogin.setEnteredPassword("wrong");

        validLogin.loginUser();

        String expected
                = "Username or password incorrect, please try again.";

        // ACT
        String actual = validLogin.returnLoginStatus();

        // ASSERT
        assertEquals(expected, actual);
    }
}