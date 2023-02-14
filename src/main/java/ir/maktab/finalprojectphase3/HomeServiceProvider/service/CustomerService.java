package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.BaseServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.CustomerResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;

import java.util.List;

public interface CustomerService {
    void add(CustomerRegistrationDTO customerRegistrationDTO);

    void remove(UserEmailDTO customerEmailDto);

    void update(CustomerUpdateDTO customerUpdateDTO);

    CustomerResponseDTO findByUsername(String customerUsername);

    List<CustomerResponseDTO> selectAll();

    List<CustomerResponseDTO> selectAllAvailableService();

    void login(LoginDTO customerLogin);

    Customer changePassword(ChangePasswordDTO changePasswordDTO, String newPassword, String confirmNewPassword);

    void addNewOrder(SubmitOrderDTO SubmitOrderDTO);

    void deleteOrder(OrderNumberDTO orderNumberDTO);

    void editOrder(OrderUpdateDTO orderUpdateDTO);

    void choseAnExpertForOrder(OrderUpdateDTO orderUpdateDTO, OfferRequestDTO offerRequestDTO);

    void changeOrderStatusToStarted(OrderUpdateDTO orderUpdateDTO);

    void changeOrderStatusToDone(OrderUpdateDTO orderUpdateDTO);

    void addNewComment(CommentRequestDTO comment, ExpertGetCommentDTO expert);

    List<OrderResponseDTO> showAllCustomerOrders(UserEmailDTO customerEmail);

    OrderResponseDTO showOrderDetails(String orderNumber);

    List<BaseServiceResponseDTO> showAllAvailableService();

    List<SubServiceResponseDTO> showSubServices(BaseServiceRequestDTO service);
}
