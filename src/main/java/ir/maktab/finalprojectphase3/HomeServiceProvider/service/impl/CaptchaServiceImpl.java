package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.config.CaptchaSettings;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.GoogleResponse;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.InvalidReCaptchaException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ReCaptchaInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.regex.Pattern;

public class CaptchaService implements ir.maktab.finalprojectphase3.HomeServiceProvider.service.CaptchaService {

    @Autowired
    private CaptchaSettings captchaSettings;

    @Autowired
    private RestOperations restTemplate;

    private static Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Override
    public void processResponse(String response) {
        if(!responseSanityCheck(response)) {
            throw new InvalidReCaptchaException("Response contains invalid characters");
        }

        URI verifyUri = URI.create(String.format(
                "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
                getReCaptchaSecret(), response, getClientIP()));

        GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);

        if(!googleResponse.isSuccess()) {
            throw new ReCaptchaInvalidException("reCaptcha was not successfully validated");
        }
    }

    private boolean responseSanityCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }
}
