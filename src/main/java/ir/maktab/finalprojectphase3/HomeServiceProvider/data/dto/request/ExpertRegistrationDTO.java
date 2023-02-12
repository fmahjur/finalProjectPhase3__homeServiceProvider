package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Credit;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertRegistrationDTO {
    String firstname;
    String lastname;
    String emailAddress;
    String username;
    String password;
    CreditRequestDTO creditRequestDTO;
    byte[] personalPhoto;

}
