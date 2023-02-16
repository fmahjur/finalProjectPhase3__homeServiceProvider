package ir.maktab.finalprojectphase3.HomeServiceProvider.exception;

public class InvalidReCaptchaException extends RuntimeException{
    public InvalidReCaptchaException(String message) {
        super(message);
    }
}
