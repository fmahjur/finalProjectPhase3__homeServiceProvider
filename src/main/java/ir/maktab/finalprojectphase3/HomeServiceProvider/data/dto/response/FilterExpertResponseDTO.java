package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterExpertResponseDTO {
    String firstname;
    String lastname;
    String email;
    String username;
    Boolean isActive;
    ExpertStatus expertStatus;
    Double rate;
    Long credit;
}
