package ir.maktab.finalprojectphase3.HomeServiceProvider.validation;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.enums.ExpertStatus;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.ExpertRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.SubServiceRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.NotFoundException;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ValidationException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ExpertValidator {
    private static ExpertRepository expertRepository;
    private static SubServiceRepository subServiceRepository;
    @Autowired
    private ExpertRepository expertRepository0;
    @Autowired
    private SubServiceRepository subServiceRepository0;

    @PostConstruct
    private void initStaticRepository() {
        expertRepository = expertRepository0;
        subServiceRepository = subServiceRepository0;
    }

    public static void isExistExpert(String email) throws ValidationException {
        if (expertRepository.existsByEmail(email))
            throw new ValidationException("this Email is already Exist!");
    }

    public static void isExpertConfirmed(Long expertId) throws ValidationException {
        Expert expert = expertRepository.findById(expertId).orElseThrow(()->new NotFoundException("not found!"));
        if (!expert.getExpertStatus().equals(String.valueOf(ExpertStatus.ACCEPTED)))
            throw new ValidationException("this Expert is not confirmed!");
    }

    public static void hasASubService(Expert expert, SubService subService) {
        Expert existExpert = expertRepository.findByEmail(expert.getEmail()).orElseThrow(
                ()->new NotFoundException("not found this expert!")
        );

        SubService existSubService = subServiceRepository.findByName(subService.getName()).orElseThrow(
                () -> new NotFoundException("not found this subService!")
        );

        if(existExpert.getSubServices().contains(existSubService))
            throw new ValidationException("this Expert does not have this subService!");
        throw new ValidationException("this Expert does not have this subService!");
    }
}
