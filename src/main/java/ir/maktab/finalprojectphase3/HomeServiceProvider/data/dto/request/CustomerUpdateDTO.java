package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import jakarta.validation.constraints.Email;

public class CustomerUpdateDTO {
    String firstname;
    String lastname;
    @Email
    String emailAddress;
    String username;
    Long credit;
}
