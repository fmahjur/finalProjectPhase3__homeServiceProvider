package ir.maktab.finalprojectphase3.HomeServiceProvider.controller;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.ChangePasswordDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.LoginDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserRegistrationDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl.ExpertServiceImpl;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.PictureValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/Expert")
@RequiredArgsConstructor
public class ExpertController {
    private final ExpertServiceImpl expertService;

    @PostMapping("/signup")
    @ResponseBody
    public void singUp(@Valid @RequestParam String firstname,
                       @RequestParam String lastname,
                       @RequestParam String email,
                       @RequestParam String username,
                       @RequestParam String password,
                       @RequestParam Long credit,
                       @RequestParam("imageFile") MultipartFile file) {

        UserRegistrationDTO expertRegistrationDTO = new UserRegistrationDTO(firstname, lastname, email, username, password, credit);
        PictureValidator.isValidImageFile(file.getOriginalFilename());
        byte[] expertPicture = new byte[0];
        try {
            expertPicture = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        expertService.add(expertRegistrationDTO, expertPicture);
    }

    @PostMapping("/login")
    @ResponseBody
    public void login(@Valid @RequestBody LoginDTO expertLoginDto) {
        expertService.login(expertLoginDto);
    }

    @PutMapping("/change-password")
    @ResponseBody
    public void changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        expertService.changePassword(changePasswordDTO);
    }

    @GetMapping("/show-related-orders/{expertId}")
    public List<OrderResponseDTO> viewOrdersRelatedToExpert(@PathVariable Long expertId) {
        return expertService.showRelatedOrdersAvailableForExpert(expertId);
    }

    @PostMapping("/submit-offer-for-order")
    public void submitOfferForOrder(@RequestBody OfferRequestDTO offerRequestDTO) {
        expertService.submitAnOffer(offerRequestDTO);
    }

    @GetMapping("/show-expert-rate/{expertId}")
    public double viewExpertScore(@PathVariable Long expertId) {
        return expertService.getExpertRate(expertId);
    }

    @GetMapping("/show-all-offer-do-by-expert/{expertId}")
    public List<OfferResponseDTO> viewAllOfferDoByExpert(@PathVariable Long expertId) {
        return expertService.showAllOfferDoByExpert(expertId);
    }

    @GetMapping("/show-all-expert-offers-waiting/{expertId}")
    public List<OfferResponseDTO> viewAllExpertOffersWaiting(@PathVariable Long expertId) {
        return expertService.showAllExpertOffersWaiting(expertId);
    }

    @GetMapping("/show-all-expert-offers-accepted/{expertId}")
    public List<OfferResponseDTO> viewAllExpertOffersAccepted(@PathVariable Long expertId) {
        return expertService.showAllExpertOffersAccepted(expertId);
    }

    @GetMapping("/show-all-expert-offers-rejected/{expertId}")
    public List<OfferResponseDTO> viewAllExpertOffersRejected(@PathVariable Long expertId) {
        return expertService.showAllExpertOffersRejected(expertId);
    }
}
