package ir.maktab.finalprojectphase3.HomeServiceProvider.validation;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.BaseServiceRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseServiceValidator {
    private static BaseServiceRepository baseServiceRepository;

    @Autowired
    public BaseServiceValidator(BaseServiceRepository baseServiceRepository) {
        BaseServiceValidator.baseServiceRepository = baseServiceRepository;
    }

    public static void isExistService(String serviceName) throws ValidationException {
        if (baseServiceRepository.findByName(serviceName).isPresent())
            throw new ValidationException("this Service is already Exist!");
    }

    public static void isNotExistService(Long serviceId) throws ValidationException {
        if (baseServiceRepository.findById(serviceId).isEmpty())
            throw new ValidationException("this Service is not Exist!");
    }
}
