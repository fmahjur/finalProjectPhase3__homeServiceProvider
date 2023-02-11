package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubServiceResponseDTO {
    BaseServiceRequestDTO baseServiceRequestDTO;
    String name;
    String description;
    Double basePrice;
}
