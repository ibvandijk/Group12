package test;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestAddCursist {

    /**
     * @subcontract valid email {
     * @requires email matches regular expression for valid email;
     * @ensures true; }
     */
    @Test
    public void testValidEmail() {
        String email = "test@example.com";
        assertTrue(email.matches("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+"));
    }

    /**
     * @subcontract invalid email {
     * @requires email does not match regular expression for valid email;
     * @ensures false; }
     */
    @Test
    public void testInvalidEmail() {
        String email = "testexample.com";
        assertFalse(email.matches("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+"));
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
        assertTrue(percentage >= 0 && percentage <= 100);
    }

    /**
     * @subcontract invalid percentage {
     * @requires percentage is less than 0 or greater than 100;
     * @ensures false; }
     */
    @Test
    public void testInvalidPercentage() {
        int percentage = -10;
        assertFalse(percentage >= 0 && percentage <= 100);
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
        assertTrue(isValidDate(day, month, year));
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
        assertFalse(isValidDate(day, month, year));
    }

    /**
     *
     * Checks if the given day, month, and year represent a valid date.
     * 
     * @param day   the day of the month to check
     * @param month the month to check (1-12)
     * @param year  the year to check
     * @return true if the given date is valid, false otherwise
     * @subcontract invalid month {
     * @requires month < 1 || month > 12;
     * @ensures \result == false;
     *          }
     * @subcontract invalid day {
     * @requires day < 1 || day > 31;
     * @ensures \result == false;
     *          }
     * @subcontract invalid date in months with 30 days {
     * @requires (month == 4 || month == 6 || month == 9 || month == 11) && day ==
     *           31;
     * @ensures \result == false;
     *          }
     * @subcontract invalid date in February {
     * @requires month == 2;
     * @requires day > 29 || (day == 29 && (year % 4 != 0 || (year % 100 == 0 &&
     *           year % 400 != 0)));
     * @ensures \result == false;
     *          }
     * @subcontract valid date {
     * @requires 1 <= month <= 12;
     * @requires 1 <= day <= 31;
     * @ensures \result == true;
     *          }
     */
    private boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
            return false;
        }
        if (month == 2) {
            if (day == 29 && (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0))) {
                return false;
            }
            if (day > 29) {
                return false;
            }
        }
        return true;
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
        assertTrue(postcode.matches("[1-9][0-9]{3}\\s[A-Z]{2}"));
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
        assertFalse(postcode.matches("[1-9][0-9]{3}\\s[A-Z]{2}"));
    }
}
