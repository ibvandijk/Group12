package test;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestAddCursist {

    @Test
    public void testValidEmail() {
        String email = "test@example.com";
        assertTrue(email.matches("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+"));
    }

    @Test
    public void testInvalidEmail() {
        String email = "testexample.com";
        assertFalse(email.matches("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+"));
    }

    @Test
    public void testValidURL() {
        String url = "http://www.example.com";
        assertTrue(url.matches("(http|https)://[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+"));
    }

    @Test
    public void testInvalidURL() {
        String url = "ftp://www.example.com";
        assertFalse(url.matches("(http|https)://[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+"));
    }

    @Test
    public void testValidPercentage() {
        int percentage = 50;
        assertTrue(percentage >= 0 && percentage <= 100);
    }

    @Test
    public void testInvalidPercentage() {
        int percentage = -10;
        assertFalse(percentage >= 0 && percentage <= 100);
    }

    @Test
    public void testValidDate() {
        int day = 15;
        int month = 4;
        int year = 2023;
        assertTrue(isValidDate(day, month, year));
    }

    @Test
    public void testInvalidDate() {
        int day = 29;
        int month = 2;
        int year = 2023;
        assertFalse(isValidDate(day, month, year));
    }

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

    @Test
    public void testValidCertificateNumber() {
        int certificateNumber = 7;
        assertTrue(certificateNumber >= 1 && certificateNumber <= 10);
    }

    @Test
    public void testInvalidCertificateNumber() {
        int certificateNumber = 0;
        assertFalse(certificateNumber >= 1 && certificateNumber <= 10);
    }

    @Test
    public void testValidPostcode() {
        String postcode = "1234 AB";
        assertTrue(postcode.matches("[1-9][0-9]{3}\\s[A-Z]{2}"));
    }

    @Test
    public void testInvalidPostcode() {
        String postcode = "0000AA";
        assertFalse(postcode.matches("[1-9][0-9]{3}\\s[A-Z]{2}"));
    }
}
