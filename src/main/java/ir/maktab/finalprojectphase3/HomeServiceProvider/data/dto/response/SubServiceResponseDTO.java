package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubServiceResponseDTO {
    BaseServiceResponseDTO baseServiceResponseDTO;
    String name;
    String description;
    Double basePrice;
}
