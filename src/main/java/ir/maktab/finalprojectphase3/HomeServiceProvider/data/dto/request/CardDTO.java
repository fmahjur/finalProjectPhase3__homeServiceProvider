package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import jakarta.validation.constraints.Pattern;

public class CartDTO {
    @Pattern(
            regexp = "(?<!\\d)\\d{16}(?!\\d)|(?<!\\d[ _-])(?<!\\d)\\d{4}(?=([_ -]))(?:\\1\\d{4}){3}(?![_ -]?\\d)",
            message = "the format of the card-number is incorrect!")
    String cardNumber;

    @Pattern(regexp = "^[0-9]{3,4}$", message = "the format of the cvv2 is incorrect!")
    String cvv2;

    String expireDate;
    String password;
    

}
