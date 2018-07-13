package basic.annotation;

import java.util.List;

public class PasswordUtils {

    @UseCase(id = 7, description = "Passwords must contain at lease one numeric")
    public boolean validatePassword(String password) {
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id = 8)
    public String encrptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id = 9, description = "New password can't equal previously used ones")
    public boolean checkForNewPassword(List<String> prevPasswords, String password) {
        return !prevPasswords.contains(password);
    }

}
