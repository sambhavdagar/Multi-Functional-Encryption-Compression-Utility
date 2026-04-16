import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
    private String password;

    public PasswordValidator(String password) {
        this.password = password;
    }

    public List<String> getWeaknesses() {
        List<String> issues = new ArrayList<>();
        
        if (password.length() < 8) {
            issues.add("Password must be at least 8 characters long");
        }
        if (password.equals(password.toLowerCase())) {
            issues.add("Missing uppercase letter");
        }
        if (password.equals(password.toUpperCase())) {
            issues.add("Missing lowercase letter");
        }
        if (!password.matches(".*\\d.*")) {
            issues.add("Missing digit");
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            issues.add("Missing special character");
        }
        
        return issues;
    }
}
