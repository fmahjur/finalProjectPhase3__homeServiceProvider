package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderUpdateDTO {
    Long orderId;
    String description;
    Double CustomerProposedPrice;
    LocalDateTime workStartDate;
    int durationOfWork;
    String address;

    OrderStatus orderStatus;
}
