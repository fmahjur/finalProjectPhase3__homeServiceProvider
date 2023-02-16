package ir.maktab.finalprojectphase3.HomeServiceProvider.validation;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OrderRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OrderMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Date;

public class OrderValidator {
    public static void isValidOrderStartDate(LocalDateTime workStartDate) throws ValidationException {
        if (workStartDate.isBefore(ChronoLocalDateTime.from(LocalDate.now())))
            throw new ValidationException("work can not start before now!");
    }

    public static void isValidCustomerProposedPrice(Orders orders) throws ValidationException {
        if (!(orders.getCustomerProposedPrice() >= orders.getSubService().getBasePrice()))
            throw new ValidationException("The proposed price cannot be less than the base price!");
    }

    public static void checkOrderStatus(Orders orders){
        if (!(orders.getOrderStatus().equals(OrderStatus.WAITING_FOR_EXPERTS_OFFER) ||
            orders.getOrderStatus().equals(OrderStatus.WAITING_FOR_CHOSE_EXPERT)))
            throw new ValidationException("This order accepted by an other expert!");
    }
}
