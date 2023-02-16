package ir.maktab.finalprojectphase3.HomeServiceProvider.validation;

import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PictureValidator {
    private static final String IMAGE_FILE = "([^\\s]+(\\.(?i)(jpe?g))$)";
    private static final Pattern pattern = Pattern.compile(IMAGE_FILE);

    public static void isValidImageFile(String filePath) throws ValidationException {
        Matcher matcher = pattern.matcher(filePath);
        if (!matcher.matches())
            throw new ValidationException("invalid image!!! your image file must be jpg!");
    }

    public static void isValidImageSize(byte[] imageFile) throws ValidationException {
        long imageSize = (imageFile.length) / 1024;
        if (!(imageSize <= 300))
            throw new ValidationException("invalid image!!! your image size must be less than 300kb!");
    }
}
