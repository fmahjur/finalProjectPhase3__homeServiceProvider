package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OrderMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.SubServiceMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.OrderRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.OrderService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.OfferValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.OrderValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OfferServiceImpl offerService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(SubmitOrderDTO submitOrderDTO) {
        Orders orders = OrderMapper.INSTANCE.submitDtoToModel(submitOrderDTO);
        OrderValidator.isValidOrderStartDate(orders.getWorkStartDate());
        OrderValidator.isValidCustomerProposedPrice(orders);
        orderRepository.save(orders);
    }

    @Override
    public void remove(OrderNumberDTO orderNumberDTO) {
        Orders orders = OrderMapper.INSTANCE.orderNumberDtoToModel(orderNumberDTO);
        orders.setDeleted(true);
        orderRepository.save(orders);
    }

    @Override
    public void update(OrderUpdateDTO orderUpdateDTO) {
        Orders orders = OrderMapper.INSTANCE.orderUpdateDtoToModel(orderUpdateDTO);
        orderRepository.save(orders);
    }

    @Override
    public void receivedNewOffer(OfferRequestDTO offerRequestDTO, OrderRequestDTO orderRequestDTO) {
        OrderValidator.checkOrderStatus(orderRequestDTO);
        OfferValidator.isValidExpertProposedPrice(offerRequestDTO);
        OfferValidator.hasDurationOfWork(offerRequestDTO);
        offerRequestDTO.setOrderRequestDTO(orderRequestDTO);
        orderRequestDTO.getOfferRequestDTOList().add(offerRequestDTO);
        orderRequestDTO.setOrderStatus(OrderStatus.WAITING_FOR_CHOSE_EXPERT);
        offerService.add(offerRequestDTO);
        Orders orders = OrderMapper.INSTANCE.requestDtoToModel(orderRequestDTO);
        orderRepository.save(orders);
    }

    @Override
    public List<OrderResponseDTO> selectAll() {
        List<Orders> ordersList = orderRepository.findAll();
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for (Orders orders: ordersList)
            orderResponseDTOList.add(OrderMapper.INSTANCE.modelToResponseDto(orders));
        return orderResponseDTOList;
    }

    @Override
    public List<OrderResponseDTO> selectAllOrderBySubService(SubServiceRequestDTO subServiceRequestDTO) {
        SubService subService = SubServiceMapper.INSTANCE.requestDtoToModel(subServiceRequestDTO);
        List<Orders> allBySubService = orderRepository.findAllBySubServiceAndOrderStatusIs(subService, OrderStatus.WAITING_FOR_EXPERTS_OFFER);
        allBySubService.addAll(orderRepository.findAllBySubServiceAndOrderStatusIs(subService, OrderStatus.WAITING_FOR_CHOSE_EXPERT));
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for (Orders orders: allBySubService)
            orderResponseDTOList.add(OrderMapper.INSTANCE.modelToResponseDto(orders));
        return orderResponseDTOList;
    }

    @Override
    public List<OrderResponseDTO> selectAllCustomersOrders(Customer customer) {
        //TODO
        List<Orders> allOrdersByCustomer = orderRepository.findAllByCustomer(customer);
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for (Orders orders: allOrdersByCustomer)
            orderResponseDTOList.add(OrderMapper.INSTANCE.modelToResponseDto(orders));
        return orderResponseDTOList;
    }

    @Override
    public OrderResponseDTO getOrderDetail(String orderNumber) {
        Orders orderByOrderNumber = orderRepository.findByOrderNumber(orderNumber).orElseThrow(()->new NotFoundException("Not found!"));
        return OrderMapper.INSTANCE.modelToResponseDto(orderByOrderNumber);
    }
    @Override
    public void showAllOfferForOrder(OrderNumberDTO orderNumberDTO) {
        offerService.selectAllByOrder(orderNumberDTO);
    }
}
