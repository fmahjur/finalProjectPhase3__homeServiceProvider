package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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

    @Email(message = "invalid Email!")
    String email;
    String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",
            message = "invalid password!")
    String password;
    Long credit;
    byte[] personalPhoto;

}
