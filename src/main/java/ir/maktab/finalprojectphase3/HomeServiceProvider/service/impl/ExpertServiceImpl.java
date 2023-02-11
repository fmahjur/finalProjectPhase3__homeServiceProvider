package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.ExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.CommentMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.ExpertMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.SubServiceMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.IncorrectInformationException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ResourceNotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.ExpertService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.EmailValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.ExpertValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.PasswordValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.PictureValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository;
    private final OrderServiceImpl orderService;
    private final CommentServiceImp commentService;

    @Override
    public void add(ExpertRegistrationDTO expertRegistrationDTO) {
        Expert expert = ExpertMapper.INSTANCE.registerDtoToModel(expertRegistrationDTO);
        EmailValidator.isValid(expert.getEmailAddress());
        ExpertValidator.isExistExpert(expert.getEmailAddress());
        Optional<Expert> savedExpert = expertRepository.findByEmailAddress(expert.getEmailAddress());
        if (savedExpert.isPresent()) {
            throw new ResourceNotFoundException("Employee already exist with given email:" + expert.getEmailAddress());
        }
        PasswordValidator.isValid(expert.getPassword());
        PictureValidator.isValidImageSize(expert.getPersonalPhoto());
        expertRepository.save(expert);
    }

    @Override
    public void remove(ExpertEmailDTO expertEmailDTO) {
        Expert expert = ExpertMapper.INSTANCE.emailDtoToModel(expertEmailDTO);
        expert.setDeleted(true);
        expertRepository.save(expert);
    }

    @Override
    public void update(ExpertUpdateDTO expertUpdateDTO) {
        Expert expert = ExpertMapper.INSTANCE.updateDtoToModel(expertUpdateDTO);
        expertRepository.save(expert);
    }

    @Override
    public ExpertResponseDTO findByUsername(String expertUsername) {
        Expert expert = expertRepository.findByUsername(expertUsername).orElseThrow(() -> new NotFoundException("not found"));
        return ExpertMapper.INSTANCE.modelToResponseDto(expert);
    }

    @Override
    public void updateExpertSubService(SubServiceRequestDTO subServiceRequestDTO, ExpertEmailDTO expertEmailDTO) {
        Expert expert = ExpertMapper.INSTANCE.emailDtoToModel(expertEmailDTO);
        SubService subService = SubServiceMapper.INSTANCE.requestDtoToModel(subServiceRequestDTO);
        expert.getSubServices().add(subService);
        expertRepository.save(expert);
    }

    @Override
    public void receivedNewComment(CommentRequestDTO commentRequestDTO, ExpertGetCommentDTO expertGetCommentDTO) {
        Expert expert = ExpertMapper.INSTANCE.getCommentDtoToModel(expertGetCommentDTO);
        Comment comment = CommentMapper.INSTANCE.requestDtoToModel(commentRequestDTO);
        comment.setExpert(expert);
        expert.getComments().add(comment);
        expert.setRate();
        commentService.add(comment);
        expertRepository.save(expert);
    }

    @Override
    public List<ExpertResponseDTO> selectAll() {
        List<Expert> expertList = expertRepository.findAll();
        List<ExpertResponseDTO> expertResponseDTOList = new ArrayList<>();
        for (Expert expert : expertList)
            expertResponseDTOList.add(ExpertMapper.INSTANCE.modelToResponseDto(expert));
        return expertResponseDTOList;
    }

    @Override
    public List<ExpertResponseDTO> selectAllAvailableService() {
        List<Expert> expertList = expertRepository.findAllByDeletedIs(false);
        List<ExpertResponseDTO> expertResponseDTOList = new ArrayList<>();
        for (Expert expert : expertList)
            expertResponseDTOList.add(ExpertMapper.INSTANCE.modelToResponseDto(expert));
        return expertResponseDTOList;
    }


    @Override
    public void login(LoginDTO loginDTO) {
        Expert expert = ExpertMapper.INSTANCE.loginDtoToModel(loginDTO);
        Optional<Expert> expertByUsername = expertRepository.findByUsername(expert.getUsername());
        if (expertByUsername.isPresent())
            if (!Objects.equals(expert.getPassword(), expertByUsername.get().getPassword()))
                throw new IncorrectInformationException("Username or Password is Incorrect!");
    }

    @Override
    public Expert changePassword(ChangePasswordDTO changePasswordDTO, String newPassword, String confirmNewPassword) {
        Expert expert = ExpertMapper.INSTANCE.changePasswordDtoToModel(changePasswordDTO);
        PasswordValidator.isValidNewPassword(expert.getPassword(), newPassword, confirmNewPassword);
        PasswordValidator.isValid(newPassword);
        expert.setPassword(newPassword);
        return expertRepository.save(expert);
    }

    @Override
    public List<ExpertResponseDTO> selectExpertByExpertStatus(ExpertStatus expertStatus) {
        List<Expert> expertList = expertRepository.findAllByExpertStatus(expertStatus);
        List<ExpertResponseDTO> expertResponseDTOList = new ArrayList<>();
        for (Expert expert : expertList)
            expertResponseDTOList.add(ExpertMapper.INSTANCE.modelToResponseDto(expert));
        return expertResponseDTOList;
    }

    @Override
    public void submitAnOffer(Offer offer, Orders orders) {
        //TODO
        orderService.receivedNewOffer(offer, orders);
    }

    @Override
    public byte[] getImage(Long id) {
        Optional<Expert> getExpert = expertRepository.findById(id);
        byte[] personalPhoto = getExpert.get().getPersonalPhoto();
        return personalPhoto;
    }
}
