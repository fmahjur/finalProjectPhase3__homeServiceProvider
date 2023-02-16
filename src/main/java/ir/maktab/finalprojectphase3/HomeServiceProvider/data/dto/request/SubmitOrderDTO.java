package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubmitOrderDTO {
    Long customerId;
    SubService subService;
    String description;
    Double CustomerProposedPrice;
    Date workStartDate;
    int durationOfWork;
    String address;
}
