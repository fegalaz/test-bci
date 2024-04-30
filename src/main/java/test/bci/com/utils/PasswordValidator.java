package test.bci.com.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordValidator {
    private static final String PASS_PATTERN = "[A-Za-z0-9]{8,16}"; // Por ejemplo, entre 8 y 16 caracteres alfanuméricos
    private static final Pattern pattern = Pattern.compile(PASS_PATTERN);

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordValidator() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public static boolean isValidPassword(String password){
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String encriptarPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
