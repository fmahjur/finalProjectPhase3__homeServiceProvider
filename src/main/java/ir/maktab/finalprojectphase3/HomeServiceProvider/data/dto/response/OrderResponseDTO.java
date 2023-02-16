package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDTO {
    String orderNumber;
    Long customerId;
    Long subServiceId;
    List<Offer> offers = new ArrayList<>();
    String description;
    Double CustomerProposedPrice;
    OrderStatus orderStatus;
    Comment comment;
    Date orderRegistrationDate;
    Date workStartDate;
    int durationOfWork;
    String address;
}
