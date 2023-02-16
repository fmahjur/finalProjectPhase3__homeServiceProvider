package ir.maktab.finalprojectphase3.HomeServiceProvider.validation;

import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static void isValid(String password) throws ValidationException {
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches())
            throw new ValidationException("invalid password!");
    }
    public static void isValidNewPassword(String oldPassword, String newPassword, String confirmNewPassword) throws ValidationException {
        if(oldPassword.equals(newPassword))
            throw new ValidationException("oldPassword and newPassword can not be same!");
        else if (!newPassword.equals(confirmNewPassword))
            throw new ValidationException("newPassword and confirmNewPassword must be same!");
    }
}
