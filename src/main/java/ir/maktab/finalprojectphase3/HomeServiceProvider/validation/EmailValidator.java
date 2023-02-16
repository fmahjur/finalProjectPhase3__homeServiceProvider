package ir.maktab.finalprojectphase3.HomeServiceProvider.validation;

import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static void isValid(final String email) throws ValidationException {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches())
            throw new ValidationException("invalid Email!");
    }
}
