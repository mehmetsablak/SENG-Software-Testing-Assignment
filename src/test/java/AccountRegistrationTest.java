import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountRegistrationTest {

    private AccountRegistration registration;

    // Test Fixtures - Her testten önce çalışır[cite: 1]
    @BeforeEach
    public void setUp() {
        registration = new AccountRegistration();
    }

    // Test Fixtures - Her testten sonra çalışır[cite: 1]
    @AfterEach
    public void tearDown() {
        registration = null;
    }

    // --- GEÇERLİ DURUMLAR (HAPPY PATH) ---
    @Test
    public void test01_ValidSubmission_ShouldPass() {
        String result = registration.registerUser("Ali", "Veli", "ali@mail.com", "01/01/2000", "Password123", "Password123");
        assertEquals("Kayıt Başarılı", result);
    }

    // --- İSİM ALANI TESTLERİ (Equivalence Partitioning) ---
    @Test
    public void test02_EmptyFirstName_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("İsim boş olamaz", e.getMessage());
    }

    @Test
    public void test03_FirstNameWithNumbers_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali123", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("İsim rakam içeremez", e.getMessage());
    }

    @Test
    public void test04_FirstNameWithOnlySpaces_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("   ", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("İsim boş olamaz", e.getMessage());
    }

    // --- E-POSTA TESTLERİ (Equivalence Partitioning) ---
    @Test
    public void test05_EmailWithoutAtSymbol_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "alimail.com", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("Geçersiz e-posta", e.getMessage());
    }

    @Test
    public void test06_EmailWithoutDomain_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "ali@", "01/01/2000", "Pass1234", "Pass1234"));
        assertEquals("Geçersiz e-posta", e.getMessage());
    }

    // --- ŞİFRE TESTLERİ (Boundary Value Analysis & EP) ---
    @Test
    public void test07_PasswordTooShort_ShouldFail() { // BVA Sınır Altı
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "Pass123", "Pass123")); // 7 Karakter
        assertEquals("Şifre en az 8 karakter olmalı", e.getMessage());
    }

    @Test
    public void test08_PasswordExactly8Chars_ShouldPass() { // BVA Sınır Değeri
        String result = registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Pass1234");
        assertEquals("Kayıt Başarılı", result);
    }

    @Test
    public void test09_PasswordEmpty_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "", ""));
        assertEquals("Şifre boş olamaz", e.getMessage());
    }

    @Test
    public void test10_ConfirmPasswordEmpty_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "Pass1234", ""));
        assertEquals("Şifre onayı boş olamaz", e.getMessage());
    }

    @Test
    public void test11_PasswordsDoNotMatch_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2000", "Pass1234", "Diff1234"));
        assertEquals("Şifreler uyuşmuyor", e.getMessage());
    }

    // --- DOĞUM TARİHİ TESTLERİ (Boundary Value Analysis & EP) ---
    @Test
    public void test12_FutureDate_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/01/2050", "Pass1234", "Pass1234"));
        assertEquals("Doğum tarihi gelecek bir tarih olamaz", e.getMessage());
    }

    @Test
    public void test13_AgeUnder18_ShouldFail() { // BVA Sınır Altı
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "01/05/2010", "Pass1234", "Pass1234"));
        assertEquals("Yaş 18'den küçük olamaz", e.getMessage());
    }

    @Test
    public void test14_AgeExactly18_ShouldPass() { // BVA Sınır Değeri
        // Bugünün tarihi 1 Mayıs 2026 olduğu için, tam 18 yaş 1 Mayıs 2008'dir.
        String result = registration.registerUser("Ali", "Veli", "a@m.com", "01/05/2008", "Pass1234", "Pass1234");
        assertEquals("Kayıt Başarılı", result);
    }

    @Test
    public void test15_WrongDateFormat_ShouldFail() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> 
            registration.registerUser("Ali", "Veli", "a@m.com", "2000-01-01", "Pass1234", "Pass1234"));
        assertEquals("Geçersiz tarih formatı, dd/MM/yyyy olmalı", e.getMessage());
    }
}