package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OfferStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.OrderStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.CustomerMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.OrderMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.CustomerRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.IncorrectInformationException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.InsufficientFoundsException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.OrderStatusException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.CustomerService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.CustomerValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.EmailValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.OrderValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.PasswordValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(UserRegistrationDTO customerRegistrationDTO) {
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
    public Customer findByUsername(String customerUsername) {
        return customerRepository.findByUsername(customerUsername).orElseThrow(() -> new NotFoundException("not found"));
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
        Customer customer = findByUsername(customerLogin.getUsername());
        if (!Objects.equals(customer.getPassword(), customerLogin.getPassword()))
            throw new IncorrectInformationException("Username or Password is Incorrect!");
    }

    @Override
    public Customer changePassword(ChangePasswordDTO changePasswordDTO) {
        PasswordValidator.isValidNewPassword(changePasswordDTO.getPassword(),
                changePasswordDTO.getNewPassword(),
                changePasswordDTO.getConfirmNewPassword());
        Customer customer = findByUsername(changePasswordDTO.getUsername());
        customer.setPassword(changePasswordDTO.getNewPassword());
        return customerRepository.save(customer);
    }

    @Override
    public void addNewOrder(SubmitOrderDTO submitOrderDTO) {
        Customer customer = findById(submitOrderDTO.getCustomerId());
        Orders orders = OrderMapper.INSTANCE.submitDtoToModel(submitOrderDTO);
        orders.setCustomer(customer);
        orderService.add(orders);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderService.remove(orderId);
    }

    @Override
    public void editOrder(OrderUpdateDTO orderUpdateDTO) {
        Orders order = orderService.findById(orderUpdateDTO.getOrderId());
        order.setDescription(orderUpdateDTO.getDescription());
        order.setCustomerProposedPrice(orderUpdateDTO.getCustomerProposedPrice());
        order.setWorkStartDate(orderUpdateDTO.getWorkStartDate());
        order.setDurationOfWork(orderUpdateDTO.getDurationOfWork());
        order.setAddress(orderUpdateDTO.getAddress());
        orderService.update(order);
    }

    @Override
    public void choseAnExpertForOrder(Long offerId) {
        Offer offer = offerService.findById(offerId);
        Orders orders = offer.getOrders();
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
    public void changeOrderStatusToStarted(Long orderId) {
        Orders orders = orderService.findById(orderId);
        OrderValidator.isValidOrderStartDate(orders.getWorkStartDate());
        orders.setOrderStatus(OrderStatus.STARTED);
        orderService.update(orders);
    }

    @Override
    public void changeOrderStatusToDone(Long orderId) {
        Orders orders = orderService.findById(orderId);
        orders.setOrderStatus(OrderStatus.DONE);
        orderService.update(orders);
    }

    @Override
    public void addNewComment(CommentRequestDTO comment) {
        expertService.receivedNewComment(comment);
    }

    @Override
    public List<OrderResponseDTO> showAllCustomerOrders(Long customerId) {
        Customer customer = findById(customerId);
        return orderService.selectAllCustomersOrders(customer);
    }

    @Override
    public OrderResponseDTO showOrderDetails(Long orderId) {
        return orderService.getOrderDetail(orderId);
    }

    @Override
    public List<BaseServiceResponseDTO> showAllAvailableService() {
        return baseServiceService.selectAll();
    }

    @Override
    public List<SubServiceResponseDTO> showSubServices(Long baseServiceId) {
        return subServiceService.getSubServicesByService(baseServiceId);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("not Found!"));
    }

    @Override
    public List<OfferResponseDTO> showAllOfferForOrder(Long orderId) {
        return offerService.selectAllByOrder(orderId);
    }

    @Override
    public void updateCredit(Long customerId, Long newCredit) {
        customerRepository.updateCredit(customerId, newCredit);
    }

    @Override
    public void payByCredit(Long orderId, Long customerId, Long expertId, Long amount) {
        Orders order = orderService.findById(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.DONE))
            throw new OrderStatusException("the status of this order is not yet \"DONE\"!");
        Customer customer = findById(customerId);
        if (customer.getCredit() < amount)
            throw new InsufficientFoundsException("Insufficient founds!");
        Expert expert = expertService.findById(expertId);
        orderService.changeOrderStatus(orderId, OrderStatus.PAID);
        updateCredit(customerId, customer.getCredit() - amount);
        expertService.updateCredit(expertId, expert.getCredit() + (long) (amount * 0.7));
    }

    @Override
    public List<FilterCustomerResponseDTO> customersFilter(FilterCustomerDTO customerDTO) {
        List<Predicate> predicateList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> customerCriteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = customerCriteriaQuery.from(Customer.class);
        createFilters(customerDTO, predicateList, criteriaBuilder, customerRoot);
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        customerCriteriaQuery.select(customerRoot).where(predicates);
        List<Customer> resultList = entityManager.createQuery(customerCriteriaQuery).getResultList();
        List<FilterCustomerResponseDTO> filterCustomerResponseDTOList = new ArrayList<>();
        for (Customer customer : resultList)
            filterCustomerResponseDTOList.add(CustomerMapper.INSTANCE.modelToFilterResponseDto(customer));
        return filterCustomerResponseDTOList;
    }

    private void createFilters(FilterCustomerDTO customerDTO, List<Predicate> predicateList, CriteriaBuilder criteriaBuilder, Root<Customer> customerRoot) {
        if (customerDTO.getFirstname() != null) {
            String firstname = "%" + customerDTO.getFirstname() + "%";
            predicateList.add(criteriaBuilder.like(customerRoot.get("firstname"), firstname));
        }
        if (customerDTO.getLastname() != null) {
            String lastname = "%" + customerDTO.getLastname() + "%";
            predicateList.add(criteriaBuilder.like(customerRoot.get("lastname"), lastname));
        }
        if (customerDTO.getEmail() != null) {
            String email = "%" + customerDTO.getEmail() + "%";
            predicateList.add(criteriaBuilder.like(customerRoot.get("email"), email));
        }
        if (customerDTO.getUsername() != null) {
            String username = "%" + customerDTO.getUsername() + "%";
            predicateList.add(criteriaBuilder.like(customerRoot.get("username"), username));
        }
    }

    @Override
    public Long viewCredit(Long customerId) {
        Customer customer = findById(customerId);
        return customer.getCredit();
    }
}
