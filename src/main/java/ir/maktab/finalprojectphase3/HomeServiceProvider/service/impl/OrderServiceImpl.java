package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OfferMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OrderMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
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

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final SubServiceServiceImpl subServiceService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Orders orders) {
        //OrderValidator.isValidOrderStartDate(orders.getWorkStartDate());
        //OrderValidator.isValidCustomerProposedPrice(orders);
        orderRepository.save(orders);
    }

    @Override
    public void remove(Long orderId) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("not Found!"));
        orders.setDeleted(true);
        orderRepository.save(orders);
    }

    @Override
    public void update(Orders orders) {
        orderRepository.save(orders);
    }

    @Override
    public void receivedNewOffer(OfferRequestDTO offerRequestDTO) {
        Orders orders = findById(offerRequestDTO.getOrderId());
        Offer offer = OfferMapper.INSTANCE.requestDtoToModel(offerRequestDTO);
        OrderValidator.checkOrderStatus(orders);
        OfferValidator.isValidExpertProposedPrice(offerRequestDTO);
        OfferValidator.hasDurationOfWork(offerRequestDTO);
        //offer.setOrders(orders);
        orders.getOffers().add(offer);
        orders.setOrderStatus(OrderStatus.WAITING_FOR_CHOSE_EXPERT);
        //offerService.add(offer);
        orderRepository.save(orders);
    }

    public Orders findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("not found!"));
    }

    @Override
    public List<OrderResponseDTO> selectAll() {
        List<Orders> ordersList = orderRepository.findAll();
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for (Orders orders : ordersList)
            orderResponseDTOList.add(OrderMapper.INSTANCE.modelToResponseDto(orders));
        return orderResponseDTOList;
    }

    @Override
    public List<OrderResponseDTO> selectAllOrderBySubServiceAndOrderStatus(String subServiceName) {
        SubService subService = subServiceService.findByName(subServiceName);
        List<Orders> allBySubService = orderRepository.findAllBySubService(
                OrderStatus.WAITING_FOR_EXPERTS_OFFER, OrderStatus.WAITING_FOR_CHOSE_EXPERT, subService);
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for (Orders orders : allBySubService)
            orderResponseDTOList.add(OrderMapper.INSTANCE.modelToResponseDto(orders));
        return orderResponseDTOList;
    }

    @Override
    public List<OrderResponseDTO> selectAllCustomersOrders(Customer customer) {
        List<Orders> allOrdersByCustomer = orderRepository.findAllByCustomer(customer);
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for (Orders orders : allOrdersByCustomer)
            orderResponseDTOList.add(OrderMapper.INSTANCE.modelToResponseDto(orders));
        return orderResponseDTOList;
    }

    @Override
    public OrderResponseDTO getOrderDetail(Long orderId) {
        Orders order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Not found!"));
        return OrderMapper.INSTANCE.modelToResponseDto(order);
    }

    public void changeOrderStatus(Long orderId, OrderStatus orderStatus) {
        orderRepository.changeOrderStatus(orderId, orderStatus);
    }
}
