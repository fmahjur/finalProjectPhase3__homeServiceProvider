package ir.maktab.finalprojectphase3.HomeServiceProvider.controller;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.ChangePasswordDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.ExpertRegistrationDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.LoginDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl.ExpertServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Expert")
@RequiredArgsConstructor
public class ExpertController {
    private final ExpertServiceImpl expertService;

    @PostMapping("/signup")
    @ResponseBody
    public void singUp(@Valid @RequestBody ExpertRegistrationDTO expertRegistrationDTO) {
        expertService.add(expertRegistrationDTO);
    }

    @PostMapping("/login")
    @ResponseBody
    public void login(@Valid @RequestBody LoginDTO expertLoginDto) {
        expertService.login(expertLoginDto);
    }

    @PostMapping("/change-password")
    @ResponseBody
    public void changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        expertService.changePassword(changePasswordDTO);
    }

    @GetMapping("/show-related-orders")
    public List<OrderResponseDTO> viewOrdersRelatedToExpert(Long expertId) {
        return expertService.showRelatedOrdersAvailableForExpert(expertId);
    }

    @PostMapping("/submit-offer-for-order")
    public void submitOfferForOrder(@RequestBody OfferRequestDTO offerRequestDTO) {
        expertService.submitAnOffer(offerRequestDTO);
    }
}
