import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class AccountRegistration {
    
    public String registerUser(String firstName, String lastName, String email, String dob, String password, String confirmPassword) {
        
        // 1. İsim Kontrolleri (Equivalence Partitioning)
        if (firstName == null || firstName.trim().isEmpty()) throw new IllegalArgumentException("İsim boş olamaz");
        if (firstName.matches(".*\\d.*")) throw new IllegalArgumentException("İsim rakam içeremez");

        // 2. E-posta Kontrolleri
        if (email == null || !email.contains("@") || !email.contains(".")) throw new IllegalArgumentException("Geçersiz e-posta");

        // 3. Şifre Uzunluğu Kontrolleri (Boundary Value Analysis)
        if (password == null || password.trim().isEmpty()) throw new IllegalArgumentException("Şifre boş olamaz");
        if (password.length() < 8) throw new IllegalArgumentException("Şifre en az 8 karakter olmalı");

        // 4. Şifre Onay Kontrolü
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) throw new IllegalArgumentException("Şifre onayı boş olamaz");
        if (!password.equals(confirmPassword)) throw new IllegalArgumentException("Şifreler uyuşmuyor");

        // 5. Doğum Tarihi ve Yaş Kontrolü (Boundary Value Analysis)
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate birthDate = LocalDate.parse(dob, formatter);
            LocalDate today = LocalDate.now(); 
            
            if (birthDate.isAfter(today)) throw new IllegalArgumentException("Doğum tarihi gelecek bir tarih olamaz");
            
            long age = ChronoUnit.YEARS.between(birthDate, today);
            if (age < 18) throw new IllegalArgumentException("Yaş 18'den küçük olamaz");
            
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) throw e;
            throw new IllegalArgumentException("Geçersiz tarih formatı, dd/MM/yyyy olmalı");
        }
        
        return "Kayıt Başarılı";
    }
}