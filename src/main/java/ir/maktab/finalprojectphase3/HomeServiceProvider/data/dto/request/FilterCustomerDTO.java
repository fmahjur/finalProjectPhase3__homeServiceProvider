package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterCustomerDTO {
    String firstname;
    String lastname;
    String email;
    String username;
    Boolean isActive;
    Long credit;
    LocalDateTime CreationDate;
}
