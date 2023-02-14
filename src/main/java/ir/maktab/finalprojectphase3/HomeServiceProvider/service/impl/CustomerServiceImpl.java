package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.BaseServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.CustomerResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OfferStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.CustomerMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OfferMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OrderMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.CustomerRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.IncorrectInformationException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.CustomerService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.CustomerValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.EmailValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.OrderValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderServiceImpl orderService;
    private final OfferServiceImpl offerService;
    private final ExpertServiceImpl expertService;
    private final BaseServiceServiceImpl baseServiceService;
    private final SubServiceServiceImpl subServiceService;

    @Override
    public void add(CustomerRegistrationDTO customerRegistrationDTO) {
        Customer customer = CustomerMapper.INSTANCE.registerDtoToModel(customerRegistrationDTO);
        EmailValidator.isValid(customer.getEmail());
        CustomerValidator.isExistCustomer(customer.getEmail());
        PasswordValidator.isValid(customer.getPassword());
        customerRepository.save(customer);
    }

    @Override
    public void remove(UserEmailDTO customerEmailDto) {
        Customer customer = CustomerMapper.INSTANCE.emailDtoToModel(customerEmailDto);
        customer.setDeleted(true);
        customerRepository.save(customer);
    }

    @Override
    public void update(CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = CustomerMapper.INSTANCE.updateDtoToModel(customerUpdateDTO);
        customerRepository.save(customer);
    }

    @Override
    public CustomerResponseDTO findByUsername(String customerUsername) {
        Customer customer = customerRepository.findByUsername(customerUsername).orElseThrow(() -> new NotFoundException("not found"));
        return CustomerMapper.INSTANCE.modelToResponseDto(customer);
    }

    @Override
    public List<CustomerResponseDTO> selectAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOList = new ArrayList<>();
        for (Customer customer : customerList)
            customerResponseDTOList.add(CustomerMapper.INSTANCE.modelToResponseDto(customer));
        return customerResponseDTOList;
    }

    @Override
    public List<CustomerResponseDTO> selectAllAvailableService() {
        List<Customer> availableCustomerList = customerRepository.findAllByDeletedIs(false);
        List<CustomerResponseDTO> customerResponseDTOList = new ArrayList<>();
        for (Customer customer : availableCustomerList)
            customerResponseDTOList.add(CustomerMapper.INSTANCE.modelToResponseDto(customer));
        return customerResponseDTOList;
    }

    @Override
    public void login(LoginDTO customerLogin) {
        Customer customer = CustomerMapper.INSTANCE.loginDtoToModel(customerLogin);
        Customer customerByUsername = customerRepository.findByUsername(customer.getUsername()).orElseThrow(
                () -> new NotFoundException("Not Found your username!")
        );
        if (!Objects.equals(customer.getPassword(), customerByUsername.getPassword()))
            throw new IncorrectInformationException("Username or Password is Incorrect!");
    }

    @Override
    public Customer changePassword(ChangePasswordDTO changePasswordDTO, String newPassword, String confirmNewPassword) {
        Customer customer = CustomerMapper.INSTANCE.changePasswordDtoToModel(changePasswordDTO);
        PasswordValidator.isValidNewPassword(customer.getPassword(), newPassword, confirmNewPassword);
        PasswordValidator.isValid(newPassword);
        customer.setPassword(newPassword);
        return customerRepository.save(customer);
    }

    @Override
    public void addNewOrder(SubmitOrderDTO SubmitOrderDTO) {
        orderService.add(SubmitOrderDTO);
    }

    @Override
    public void deleteOrder(OrderNumberDTO orderNumberDTO) {
        orderService.remove(orderNumberDTO);
    }

    @Override
    public void editOrder(OrderUpdateDTO orderUpdateDTO) {
        Orders orders = OrderMapper.INSTANCE.orderUpdateDtoToModel(orderUpdateDTO);
        orderService.update(orders);
    }

    @Override
    public void choseAnExpertForOrder(OrderUpdateDTO orderUpdateDTO, OfferRequestDTO offerRequestDTO) {
        Orders orders = OrderMapper.INSTANCE.orderUpdateDtoToModel(orderUpdateDTO);
        Offer offer = OfferMapper.INSTANCE.requestDtoToModel(offerRequestDTO);
        for (Offer offer1 : orders.getOffers()) {
            if (Objects.equals(offer1, offer)) {
                offer1.setOfferStatus(OfferStatus.ACCEPTED);
                orders.setWorkStartDate(offer1.getProposedStartDate());
            } else
                offer1.setOfferStatus(OfferStatus.REJECTED);
            offerService.update(offer1);
        }
        orders.setOrderStatus(OrderStatus.WAITING_FOR_EXPERT_TO_COME);
        orderService.update(orders);
    }

    @Override
    public void changeOrderStatusToStarted(OrderUpdateDTO orderUpdateDTO) {
        Orders orders = OrderMapper.INSTANCE.orderUpdateDtoToModel(orderUpdateDTO);
        OrderValidator.isValidOrderStartDate(orders.getWorkStartDate());
        orders.setOrderStatus(OrderStatus.STARTED);
        orderService.update(orders);
    }

    @Override
    public void changeOrderStatusToDone(OrderUpdateDTO orderUpdateDTO) {
        Orders orders = OrderMapper.INSTANCE.orderUpdateDtoToModel(orderUpdateDTO);
        orders.setOrderStatus(OrderStatus.DONE);
        orderService.update(orders);
    }

    @Override
    public void addNewComment(CommentRequestDTO comment, ExpertGetCommentDTO expert) {
        expertService.receivedNewComment(comment, expert);
    }

    @Override
    public List<OrderResponseDTO> showAllCustomerOrders(UserEmailDTO customerEmail) {
        Customer customer = CustomerMapper.INSTANCE.emailDtoToModel(customerEmail);
        return orderService.selectAllCustomersOrders(customer);
    }

    @Override
    public OrderResponseDTO showOrderDetails(String orderNumber) {
        return orderService.getOrderDetail(orderNumber);
    }

    @Override
    public List<BaseServiceResponseDTO> showAllAvailableService() {
        return baseServiceService.selectAll();
    }

    @Override
    public List<SubServiceResponseDTO> showSubServices(BaseServiceRequestDTO service) {
        return subServiceService.getSubServicesByService(service);
    }
}
