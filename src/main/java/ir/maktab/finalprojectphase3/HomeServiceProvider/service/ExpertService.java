package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.ExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.FilterExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;

import java.util.List;

public interface ExpertService {
    void add(UserRegistrationDTO expertRegistrationDTO, byte[] expertPicture);

    void remove(UserEmailDTO expertEmailDTO);

    void update(Expert expert);

    Expert findByEmail(String email);

    Expert findById(Long id);

    Expert findByUsername(String expertUsername);

    double getExpertRate(Long expertId);

    void addSubServiceToExpert(Long subServiceId, Long expertId);

    void removeSubServiceFromExpert(Long subServiceRequestId, Long expertId);

    void receivedNewComment(CommentRequestDTO commentRequestDTO);

    List<ExpertResponseDTO> selectAll();

    List<ExpertResponseDTO> selectAllAvailableExpert();

    void login(LoginDTO loginDTO);

    Expert changePassword(ChangePasswordDTO changePasswordDTO);

    List<ExpertResponseDTO> selectExpertByExpertStatus(ExpertStatus expertStatus);

    void submitAnOffer(OfferRequestDTO offerRequestDTO);

    byte[] getImage(Long id);

    void updateCredit(Long expertId, Long newCredit);

    void changeExpertAccountActivation(Long expertId, boolean isActive);

    List<FilterExpertResponseDTO> expertsFilter(FilterExpertDTO expertDTO);

    List<OfferResponseDTO> showAllOfferDoByExpert(Long expertId);

    List<OfferResponseDTO> showAllExpertOffersWaiting(Long expertId);

    List<OfferResponseDTO> showAllExpertOffersAccepted(Long expertId);

    List<OfferResponseDTO> showAllExpertOffersRejected(Long expertId);
}
