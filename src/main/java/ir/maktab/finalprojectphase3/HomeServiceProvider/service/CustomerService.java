package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;

import java.util.List;

public interface CustomerService {
    void add(CustomerRegistrationDTO customerRegistrationDTO);

    void remove(UserEmailDTO customerEmailDto);

    void update(CustomerUpdateDTO customerUpdateDTO);

    Customer findByUsername(String customerUsername);

    List<CustomerResponseDTO> selectAll();

    List<CustomerResponseDTO> selectAllAvailableService();

    void login(LoginDTO customerLogin);

    Customer changePassword(ChangePasswordDTO changePasswordDTO);

    void addNewOrder(SubmitOrderDTO SubmitOrderDTO);

    void deleteOrder(Long orderId);

    void editOrder(OrderUpdateDTO orderUpdateDTO);

    void choseAnExpertForOrder(Long offerId);

    void changeOrderStatusToStarted(Long orderId);

    void changeOrderStatusToDone(Long orderId);

    void addNewComment(CommentRequestDTO comment);

    List<OrderResponseDTO> showAllCustomerOrders(Long customerId);

    OrderResponseDTO showOrderDetails(Long orderId);

    List<BaseServiceResponseDTO> showAllAvailableService();

    List<SubServiceResponseDTO> showSubServices(Long baseServiceId);

    Customer findById(Long id);

    List<OfferResponseDTO> showAllOfferForOrder(Long orderId);

    void updateCredit(Long customerId, Long newCredit);

    void payByCredit(Long orderId, Long customerId, Long expertId, Long amount);

    List<FilterCustomerResponseDTO> customersFilter(FilterCustomerDTO customerDTO);

    Long viewCredit(Long customerId);
}
