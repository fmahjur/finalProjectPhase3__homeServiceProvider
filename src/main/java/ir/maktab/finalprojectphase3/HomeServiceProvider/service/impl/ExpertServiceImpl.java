package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.ExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.FilterExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.CommentMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.ExpertMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Comment;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.IncorrectInformationException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ResourceNotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.ExpertService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.EmailValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.ExpertValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.PasswordValidator;
import ir.maktab.finalprojectphase3.HomeServiceProvider.validation.PictureValidator;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertRepository;

    private final SubServiceServiceImpl subServiceService;
    private final OrderServiceImpl orderService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(UserRegistrationDTO expertRegistrationDTO, byte[] expertPicture) {
        Expert expert = ExpertMapper.INSTANCE.registerDtoToModel(expertRegistrationDTO);
        expert.setPersonalPhoto(expertPicture);
        EmailValidator.isValid(expert.getEmail());
        ExpertValidator.isExistExpert(expert.getEmail());
        Optional<Expert> savedExpert = expertRepository.findByEmail(expert.getEmail());
        if (savedExpert.isPresent()) {
            throw new ResourceNotFoundException("Employee already exist with given email:" + expert.getEmail());
        }
        PasswordValidator.isValid(expert.getPassword());
        PictureValidator.isValidImageSize(expert.getPersonalPhoto());
        expertRepository.save(expert);
    }

    @Override
    public void remove(UserEmailDTO expertEmailDTO) {
        Expert expert = ExpertMapper.INSTANCE.emailDtoToModel(expertEmailDTO);
        expert.setDeleted(true);
        expertRepository.save(expert);
    }

    @Override
    public void update(Expert expert) {
        expertRepository.save(expert);
    }

    @Override
    public Expert findByEmail(String email) {
        return expertRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("not found"));
    }

    @Override
    public Expert findById(Long id) {
        return expertRepository.findById(id).orElseThrow(() -> new NotFoundException("not found"));
    }

    @Override
    public Expert findByUsername(String expertUsername) {
        return expertRepository.findByUsername(expertUsername).orElseThrow(() -> new NotFoundException("not found"));
    }

    @Override
    public double getExpertRate(Long expertId) {
        return findById(expertId).getRate();
    }

    @Override
    public void addSubServiceToExpert(Long subServiceId, Long expertId) {
        ExpertValidator.isExpertConfirmed(expertId);
        Expert expert = findById(expertId);
        SubService subService = subServiceService.findById(subServiceId);
        expert.addSubService(subService);
        expertRepository.save(expert);
    }

    @Override
    public void removeSubServiceFromExpert(Long subServiceId, Long expertId) {
        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new NotFoundException("not found!"));
        SubService subService = subServiceService.findById(subServiceId);
        ExpertValidator.hasASubService(expert, subService);
        expert.deleteSubService(subService);
        expertRepository.save(expert);
    }

    @Override
    public void receivedNewComment(CommentRequestDTO commentRequestDTO) {
        Expert expert = findById(commentRequestDTO.getExpertId());
        Comment comment = CommentMapper.INSTANCE.requestDtoToModel(commentRequestDTO);
        expert.getComments().add(comment);
        expert.setRate();
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
    public List<ExpertResponseDTO> selectAllAvailableExpert() {
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
    public Expert changePassword(ChangePasswordDTO changePasswordDTO) {
        PasswordValidator.isValidNewPassword(changePasswordDTO.getPassword(),
                changePasswordDTO.getNewPassword(),
                changePasswordDTO.getConfirmNewPassword());
        Expert expert = findByUsername(changePasswordDTO.getUsername());
        expert.setPassword(changePasswordDTO.getPassword());
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

    public List<OrderResponseDTO> showRelatedOrdersAvailableForExpert(Long expertId) {
        Expert expert = findById(expertId);
        List<OrderResponseDTO> orderResponseList = new ArrayList<>();
        expert.getSubServices().forEach(subService -> {
            List<OrderResponseDTO> orderResponseDTOList = orderService.selectAllOrderBySubServiceAndOrderStatus(subService.getName());
            orderResponseList.addAll(orderResponseDTOList);
        });
        return orderResponseList;
    }

    @Override
    public void submitAnOffer(OfferRequestDTO offerRequestDTO) {
        orderService.receivedNewOffer(offerRequestDTO);
    }

    @Override
    public byte[] getImage(Long id) {
        Optional<Expert> getExpert = expertRepository.findById(id);
        byte[] personalPhoto = getExpert.get().getPersonalPhoto();
        return personalPhoto;
    }

    @Override
    public void updateCredit(Long expertId, Long newCredit) {
        expertRepository.updateCredit(expertId, newCredit);
    }

    @Override
    public void changeExpertAccountActivation(Long expertId, boolean isActive) {
        expertRepository.changeActivation(expertId, isActive);
    }

    @Override
    public List<FilterExpertResponseDTO> expertsFilter(FilterExpertDTO expertDTO) {
        List<Predicate> predicateList = new ArrayList<>();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Expert> expertCriteriaQuery = criteriaBuilder.createQuery(Expert.class);
        Root<Expert> expertRoot = expertCriteriaQuery.from(Expert.class);
        createFilters(expertDTO, predicateList, criteriaBuilder, expertRoot);
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        expertCriteriaQuery.select(expertRoot).where(predicates);
        List<Expert> resultList = entityManager.createQuery(expertCriteriaQuery).getResultList();
        List<FilterExpertResponseDTO> filterExpertResponseDTOList = new ArrayList<>();
        for (Expert expert : resultList)
            filterExpertResponseDTOList.add(ExpertMapper.INSTANCE.modelToFilterResponseDto(expert));
        return filterExpertResponseDTOList;
    }

    private void createFilters(FilterExpertDTO expertDTO, List<Predicate> predicateList, CriteriaBuilder criteriaBuilder, Root<Expert> expertRoot) {
        if (expertDTO.getFirstname() != null) {
            String firstname = "%" + expertDTO.getFirstname() + "%";
            predicateList.add(criteriaBuilder.like(expertRoot.get("firstname"), firstname));
        }
        if (expertDTO.getLastname() != null) {
            String lastname = "%" + expertDTO.getLastname() + "%";
            predicateList.add(criteriaBuilder.like(expertRoot.get("lastname"), lastname));
        }
        if (expertDTO.getEmail() != null) {
            String email = "%" + expertDTO.getEmail() + "%";
            predicateList.add(criteriaBuilder.like(expertRoot.get("email"), email));
        }
        if (expertDTO.getUsername() != null) {
            String username = "%" + expertDTO.getUsername() + "%";
            predicateList.add(criteriaBuilder.like(expertRoot.get("username"), username));
        }
        if (expertDTO.getIsActive() != null) {
            predicateList.add(criteriaBuilder.equal(expertRoot.get("isActive"), expertDTO.getIsActive()));
        }
        if (expertDTO.getExpertStatus() != null) {
            predicateList.add(criteriaBuilder.equal(expertRoot.get("expertStatus"), expertDTO.getExpertStatus()));
        }

        if (expertDTO.getRate() != null) {
            predicateList.add(criteriaBuilder.lt(expertRoot.get("rate"), expertDTO.getRate()));
        }
        if (expertDTO.getCredit() != null) {
            predicateList.add(criteriaBuilder.gt(expertRoot.get("credit"), expertDTO.getCredit()));
        }
    }
}
