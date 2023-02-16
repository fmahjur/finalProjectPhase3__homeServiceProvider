package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OrderRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;

import java.util.List;

public interface OrderService {
    void add(Orders orders);

    void remove(Long orderId);

    void update(Orders orders);

    void receivedNewOffer(OfferRequestDTO offerRequestDTO);

    List<OrderResponseDTO> selectAll();

    List<OrderResponseDTO> selectAllOrderBySubServiceAndOrderStatus(String subServiceName);

    List<OrderResponseDTO> selectAllCustomersOrders(Customer customer);

    OrderResponseDTO getOrderDetail(Long orderId);

}
