package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OfferRequestDTO {
    Long orderId;
    Long offerId;
    Long expertID;
    Long offerPrice;
    Date proposedStartDate;
    int durationOfWork;
}
