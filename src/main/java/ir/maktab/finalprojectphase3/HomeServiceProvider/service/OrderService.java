package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void add(SubmitOrderDTO submitOrderDTO);
    void remove(OrderNumberDTO orderNumberDTO);
    void update(OrderUpdateDTO orderUpdateDTO);
    void receivedNewOffer(OfferRequestDTO offerRequestDTO, OrderRequestDTO orderRequestDTO);
    List<OrderResponseDTO> selectAll();
    List<OrderResponseDTO> selectAllOrderBySubService(SubServiceRequestDTO subServiceRequestDTO);
    List<OrderResponseDTO> selectAllCustomersOrders(Customer customer);
    OrderResponseDTO getOrderDetail(String orderNumber);
    void showAllOfferForOrder(OrderNumberDTO orderNumberDTO);
}
