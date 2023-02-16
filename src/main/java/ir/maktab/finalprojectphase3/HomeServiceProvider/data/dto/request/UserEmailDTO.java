package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEmailDTO {
    @Email
    String email;
}
