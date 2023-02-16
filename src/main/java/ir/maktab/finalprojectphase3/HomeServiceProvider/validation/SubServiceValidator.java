package ir.maktab.finalprojectphase3.HomeServiceProvider.validation;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.SubServiceRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubServiceValidator {
    private static SubServiceRepository subServiceRepository;

    @Autowired
    public SubServiceValidator(SubServiceRepository subServiceRepository) {
        SubServiceValidator.subServiceRepository = subServiceRepository;
    }

    public static void isExistSubService(String subServiceName) throws ValidationException {
        if (subServiceRepository.findByName(subServiceName).isPresent())
            throw new ValidationException("this SubService is already Exist!");
    }
}
