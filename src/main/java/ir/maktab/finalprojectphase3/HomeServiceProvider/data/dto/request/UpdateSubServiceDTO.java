package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateSubServiceDTO {
    Long baseServiceRequestID;
    Long subServiceID;
    String name;
    String description;
    Double basePrice;
}
