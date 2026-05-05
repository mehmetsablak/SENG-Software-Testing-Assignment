package com.assignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AccountRegistration {
    
    public String registerUser(String firstName, String lastName, String email, String dob, String password, String confirmPassword) {
        
        // 1. First Name Checks
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if (firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot consist of only spaces");
        }
        if (!firstName.matches("^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$")) {
            throw new IllegalArgumentException("First name can only contain letters");
        }

        // 2. Last Name Checks
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if (lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot consist of only spaces");
        }
        if (!lastName.matches("^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$")) {
            throw new IllegalArgumentException("Last name can only contain letters");
        }

        // 3. Email Checks
        if (email == null || !email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email");
        }

        // 4. Password Length Checks (Boundary Value Analysis)
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        // 5. Password Confirmation Check
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Confirm password cannot be empty");
        }
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // 6. Date of Birth and Age Check (Boundary Value Analysis)
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(dob, formatter);
            LocalDate today = LocalDate.now(); 
            
            if (birthDate.isAfter(today)) {
                throw new IllegalArgumentException("Date of birth cannot be in the future");
            }
            
            long age = ChronoUnit.YEARS.between(birthDate, today);
            if (age < 18) {
                throw new IllegalArgumentException("Age cannot be under 18");
            }
            
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) throw e;
            throw new IllegalArgumentException("Invalid date format, must be dd/MM/yyyy");
        }
        
        return "Registration Successful";
    }
}