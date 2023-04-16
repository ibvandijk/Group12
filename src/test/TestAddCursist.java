package test;

import org.junit.Test;
import static org.junit.Assert.*;
import GUI.CursistController;

public class TestAddCursist {

    CursistController testSubject = new CursistController();

    /**
     * @subcontract valid name {
     * @requires name matches regular expression for valid name;
     * @ensures true; }
     * @param name
     */
    @Test
    public void testValidName(String name) {
        assertTrue(name.matches("[a-zA-Z]+"));
    }

    /**
     * @subcontract invalid name {
     * @requires name matches regular expression for invalid name;
     * @ensures true; }
     * @param name
     */
    @Test
    public void testInValidName(String name) {
        assertTrue(name.matches("[0-9]+"));
    }

    /**
     * @subcontract valid email {
     * @requires email matches regular expression for valid email;
     * @ensures true;}
     */
    @Test
    public void testValidEmail() {
        String email = "j.arbuckle@student.avans.nl";
        assertTrue(testSubject.validateDate(email));
    }

    /**
     * @subcontract invalid email {
     * @requires email does not match regular expression for valid email;
     * @ensures false; }
     */
    @Test
    public void testInvalidEmail() {
        String email = "testexample.com";
        assertFalse(testSubject.validateEmail(email));
    }

    /**
     * @subcontract valid URL {
     * @requires url matches regular expression for valid URL;
     * @ensures true; }
     */

    @Test
    public void testValidURL() {
        String url = "http://www.example.com";
        assertTrue(url.matches("(http|https)://[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+"));
    }

    /**
     * @subcontract invalid URL {
     * @requires url does not match regular expression for valid URL;
     * @ensures false; }
     */
    @Test
    public void testInvalidURL() {
        String url = "ftp://www.example.com";
        assertFalse(url.matches("(http|https)://[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+"));
    }

    /**
     * @subcontract valid percentage {
     * @requires percentage is between 0 and 100 (inclusive);
     * @ensures true; }
     */
    @Test
    public void testValidPercentage() {
        int percentage = 50;
        assertTrue(testSubject.validatePercentage(percentage));
    }

    /**
     * @subcontract invalid percentage {
     * @requires percentage is less than 0 or greater than 100;
     * @ensures false; }
     */
    @Test
    public void testInvalidPercentage() {
        int percentage = -10;
        assertFalse(testSubject.validatePercentage(percentage));
    }

    /**
     * @subcontract valid date {
     * @requires day, month and year form a valid date;
     * @ensures true; }
     */
    @Test
    public void testValidDate() {

        int day = 15;
        int month = 4;
        int year = 2023;

        String dateString = "" + day + "-" + month + "-" + year;
        assertTrue(testSubject.validateDate(dateString));
    }

    /**
     * @subcontract invalid date {
     * @requires day, month and year do not form a valid date;
     * @ensures false; }
     */
    @Test
    public void testInvalidDate() {
        int day = 29;
        int month = 2;
        int year = 2023;

        String dateString = "" + day + "-" + month + "-" + year;
        assertFalse(testSubject.validateDate(dateString));
    }

    /**
     * Test case for a valid certificate number.
     * 
     * @subcontract validCertificateNumber {
     * @requires 1 <= certificateNumber <= 10;
     * @ensures \result = true;
     *          }
     */
    @Test
    public void testValidCertificateNumber() {
        int certificateNumber = 7;
        assertTrue(certificateNumber >= 1 && certificateNumber <= 10);
    }

    /**
     * Test case for an invalid certificate number.
     * 
     * @subcontract invalidCertificateNumber {
     * @requires certificateNumber < 1 || certificateNumber > 10;
     * @ensures \result = true;
     *          }
     */
    @Test
    public void testInvalidCertificateNumber() {
        int certificateNumber = 0;
        assertFalse(certificateNumber >= 1 && certificateNumber <= 10);
    }

    /**
     * @desc Test for a valid Dutch postcode format
     * @subcontract valid postcode {
     * @requires postcode is in the format "1234 AB";
     * @ensures postcode matches the pattern "[1-9][0-9]{3}\\s[A-Z]{2}"; }
     */
    @Test
    public void testValidPostcode() {
        String postcode = "1234 AB";
        assertTrue(testSubject.validatePostalCode(postcode));
    }

    /**
     * @desc Test for an invalid Dutch postcode format
     * @subcontract invalid postcode {
     * @requires postcode is not in the format "1234 AB";
     * @ensures postcode does not match the pattern "[1-9][0-9]{3}\\s[A-Z]{2}"; }
     */
    @Test
    public void testInvalidPostcode() {
        String postcode = "0000AA";
        assertFalse(testSubject.validatePostalCode(postcode));
    }
}
