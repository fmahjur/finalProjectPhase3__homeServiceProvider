package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.CustomerResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.FilterCustomerResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer registerDtoToModel(UserRegistrationDTO customerRegistrationDTO);
    Customer loginDtoToModel(LoginDTO customerLoginDTO);
    Customer emailDtoToModel(UserEmailDTO customerEmailDTO);
    Customer updateDtoToModel(CustomerUpdateDTO customerUpdateDTO);
    Customer changePasswordDtoToModel(ChangePasswordDTO changePasswordDTO);

    Customer filterDTOToModel(FilterCustomerDTO filterCustomerDTO);

    CustomerResponseDTO modelToResponseDto(Customer customer);
    FilterCustomerResponseDTO modelToFilterResponseDto(Customer customer);
}
