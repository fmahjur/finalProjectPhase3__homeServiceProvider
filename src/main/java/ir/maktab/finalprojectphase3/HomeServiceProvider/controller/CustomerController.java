package ir.maktab.finalprojectphase3.HomeServiceProvider.controller;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.BaseServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.CaptchaService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl.CustomerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceImpl customerService;
    private CaptchaService captchaService;

    @PostMapping("/signup")
    @ResponseBody
    public void singUp(@Valid @RequestBody UserRegistrationDTO customerRegistrationDTO) {
        customerService.add(customerRegistrationDTO);
    }

    @PostMapping("/login")
    @ResponseBody
    public void login(@Valid @RequestBody LoginDTO customerLoginDto) {
        customerService.login(customerLoginDto);
    }

    @PutMapping("/change-password")
    @ResponseBody
    public void changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        customerService.changePassword(changePasswordDTO);
    }

    @GetMapping("/show-all-services")
    public List<BaseServiceResponseDTO> findAllBaseService() {
        return customerService.showAllAvailableService();
    }

    @GetMapping("/show-all-sub-services-by-service/{baseServiceId}")
    public List<SubServiceResponseDTO> findAllSubService(@PathVariable Long baseServiceId) {
        return customerService.showSubServices(baseServiceId);
    }

    @PostMapping("/submit-order")
    @ResponseBody
    public void submitOrder(@RequestBody SubmitOrderDTO submitOrderDTO) {
        customerService.addNewOrder(submitOrderDTO);
    }

    @PutMapping("/edit-order")
    @ResponseBody
    public void editOrder(@RequestBody OrderUpdateDTO orderUpdateDTO) {
        customerService.editOrder(orderUpdateDTO);
    }

    @PutMapping("/chose-an-expert-for-order/{offerId}")
    @ResponseBody
    public void choseExpertForOrder(@PathVariable Long offerId) {
        customerService.choseAnExpertForOrder(offerId);
    }

    @PutMapping("/order-start/{orderId}")
    @ResponseBody
    public void orderStarted(@PathVariable Long orderId) {
        customerService.changeOrderStatusToStarted(orderId);
    }

    @PutMapping("/order-done/{orderId}")
    @ResponseBody
    public void orderDone(@PathVariable Long orderId) {
        customerService.changeOrderStatusToDone(orderId);
    }

    @PostMapping("/add-comment")
    @ResponseBody
    public void addCommentForExpertPerformance(@RequestBody CommentRequestDTO commentRequestDTO) {
        customerService.addNewComment(commentRequestDTO);
    }

    @GetMapping("/show-all-orders/{customerId}")
    public List<OrderResponseDTO> showAllCustomersOrder(@PathVariable Long customerId) {
        return customerService.showAllCustomerOrders(customerId);
    }

    @GetMapping("/show-order-details/{orderId}")
    public OrderResponseDTO showOrderDetails(@PathVariable Long orderId) {
        return customerService.showOrderDetails(orderId);
    }

    @GetMapping("/show-all-offer/{orderId}")
    public List<OfferResponseDTO> showAllOfferForOrder(@PathVariable Long orderId){
        return customerService.showAllOfferForOrder(orderId);
    }

    @PutMapping("/pay-by-credit/{orderId}/{customerId}/{expertId}/{amount}")
    @ResponseBody
    public void payFromCredit(@PathVariable Long orderId, @PathVariable Long customerId, @PathVariable Long expertId, @PathVariable Long amount) {
        customerService.payByCredit(orderId, customerId, expertId, amount);
    }

    @GetMapping("/payment")
    public String showPaymentPage(Model model){
        // create model object to store form data
        CardDTO card = new CardDTO();
        model.addAttribute("card", card);
        return "payment";
    }

    @PostMapping("/payment/pay")
    @ResponseBody
    private String pay(@Valid final CardDTO cardDto, final HttpServletRequest request) {
        final String response = request.getParameter("g-recaptcha-response");
        captchaService.processResponse(response);

        return "successful payment";
    }

}
