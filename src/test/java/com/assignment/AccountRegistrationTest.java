package com.assignment;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountRegistrationTest {

    private AccountRegistration registration;

    // Test Fixtures
    @BeforeEach
    public void setUp() {
        registration = new AccountRegistration();
    }

    @AfterEach
    public void tearDown() {
        registration = null;
    }

    // --- VALID CASES (HAPPY PATH) ---
    @Test
    public void test01_ValidSubmission_ShouldPass() {
        String result = registration.registerUser("Ali", "Veli", "ali@mail.com", "01/01/2000", "Password123", "Password123");
        assertEquals("Registration Successful", result);
    }

    // --- FIRST NAME TESTS (Equivalence Partitioning) ---
    @Test
    public void test02_EmptyFirstName_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("First name cannot be empty", e.getMessage());
    }

    @Test
    public void test03_FirstNameWithNumbers_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali123", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("First name cannot contain numbers", e.getMessage());
    }

    @Test
    public void test04_FirstNameWithOnlySpaces_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("   ", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("First name cannot be empty", e.getMessage());
    }

    // --- EMAIL TESTS (Equivalence Partitioning) ---
    @Test
    public void test05_EmailWithoutAtSymbol_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "alimail.com", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("Invalid email", e.getMessage());
    }

    @Test
    public void test06_EmailWithoutDomain_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "ali@", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("Invalid email", e.getMessage());
    }

    // --- PASSWORD TESTS (Boundary Value Analysis & EP) ---
    @Test
    public void test07_PasswordTooShort_ShouldFail() { 
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "Pass123", "Pass123"));
        assertEquals("Password must be at least 8 characters long", e.getMessage());
    }

    @Test
    public void test08_PasswordExactly8Chars_ShouldPass() { 
        String result = registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Pass1234");
        assertEquals("Registration Successful", result);
    }

    @Test
    public void test09_PasswordEmpty_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "", ""));
        assertEquals("Password cannot be empty", e.getMessage());
    }

    @Test
    public void test10_ConfirmPasswordEmpty_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "Pass1234", ""));
        assertEquals("Confirm password cannot be empty", e.getMessage());
    }

    @Test
    public void test11_PasswordsDoNotMatch_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Diff1234"));
        assertEquals("Passwords do not match", e.getMessage());
    }

    // --- DATE OF BIRTH TESTS (Boundary Value Analysis & EP) ---
    @Test
    public void test12_FutureDate_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2050", "Pass1234", "Pass1234"));
        assertEquals("Date of birth cannot be in the future", e.getMessage());
    }

    @Test
    public void test13_AgeUnder18_ShouldFail() { 
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/05/2010", "Pass1234", "Pass1234"));
        assertEquals("Age cannot be under 18", e.getMessage());
    }

    @Test
    public void test14_AgeExactly18_ShouldPass() { 
        String result = registration.registerUser("Ali", "Veli", "a@m.com", "01/05/2008", "Pass1234", "Pass1234");
        assertEquals("Registration Successful", result);
    }

    @Test
    public void test15_WrongDateFormat_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "2000-01-01", "Pass1234", "Pass1234"));
        assertEquals("Invalid date format, must be dd/MM/yyyy", e.getMessage());
    }
}