package ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDTO {
    String orderNumber;
    Customer customer;
    List<OfferRequestDTO> offerRequestDTOList = new ArrayList<>();
    OrderStatus orderStatus;
}
