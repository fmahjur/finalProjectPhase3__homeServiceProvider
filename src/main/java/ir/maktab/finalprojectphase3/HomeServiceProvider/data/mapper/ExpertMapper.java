package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.ChangePasswordDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.ExpertRegistrationDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.LoginDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.ExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.FilterExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpertMapper {
    ExpertMapper INSTANCE = Mappers.getMapper(ExpertMapper.class);

    Expert responseDtoToModel(ExpertResponseDTO expertResponseDTO);

    Expert registerDtoToModel(ExpertRegistrationDTO expertRegistrationDTO);

    Expert emailDtoToModel(UserEmailDTO expertEmailDTO);

    Expert loginDtoToModel(LoginDTO loginDTO);

    Expert changePasswordDtoToModel(ChangePasswordDTO changePasswordDTO);

    ExpertResponseDTO modelToResponseDto(Expert expert);

    FilterExpertResponseDTO modelToFilterResponseDto(Expert expert);
}
