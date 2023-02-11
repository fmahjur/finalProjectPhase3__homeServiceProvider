package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.City;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponseDTO {
    City city;
    String addressDetail;
}
