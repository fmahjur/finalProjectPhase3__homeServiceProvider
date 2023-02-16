package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertResponseDTO {
    String firstname;
    String lastname;
    String emailAddress;
    ExpertStatus expertStatus;
}
