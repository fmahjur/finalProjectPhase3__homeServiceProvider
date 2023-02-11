package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.ExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;

import java.util.List;

public interface ExpertService {
    void add(ExpertRegistrationDTO expertRegistrationDTO);

    void remove(UserEmailDTO expertEmailDTO);

    void update(ExpertUpdateDTO expertUpdateDTO);

    ExpertResponseDTO findByUsername(String expertUsername);

    void updateExpertSubService(SubServiceRequestDTO subServiceRequestDTO, UserEmailDTO expertEmailDTO);

    void receivedNewComment(CommentRequestDTO commentRequestDTO, ExpertGetCommentDTO expertGetCommentDTO);

    List<ExpertResponseDTO> selectAll();

    List<ExpertResponseDTO> selectAllAvailableService();

    void login(LoginDTO loginDTO);

    Expert changePassword(ChangePasswordDTO changePasswordDTO, String newPassword, String confirmNewPassword);

    List<ExpertResponseDTO> selectExpertByExpertStatus(ExpertStatus expertStatus);

    void submitAnOffer(Offer offer, Orders orders);

    byte[] getImage(Long id);
}
