package ir.maktab.finalprojectphase3.HomeServiceProvider.validation;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.CustomerRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerValidator {
    private static CustomerRepository customerRepository;

    @Autowired
    public CustomerValidator(CustomerRepository customerRepository) {
        CustomerValidator.customerRepository = customerRepository;
    }

    public static void isExistCustomer(String email) throws ValidationException {
        if (customerRepository.existsByEmail(email))
            throw new ValidationException("this Email is already Exist!");
    }
}
