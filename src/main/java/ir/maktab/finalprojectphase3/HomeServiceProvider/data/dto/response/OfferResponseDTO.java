package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OfferResponseDTO {
    Expert expert;
    Orders orders;
    Long offerPrice;
    Date proposedStartDate;
    int durationOfWork;
}
