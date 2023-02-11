package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.*;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.ExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExpertMapper {
    ExpertMapper INSTANCE = Mappers.getMapper(ExpertMapper.class);

    Expert responseDtoToModel(ExpertResponseDTO expertResponseDTO);

    Expert getCommentDtoToModel(ExpertGetCommentDTO expertGetCommentDTO);

    Expert registerDtoToModel(ExpertRegistrationDTO expertRegistrationDTO);

    Expert emailDtoToModel(ExpertEmailDTO expertEmailDTO);

    Expert updateDtoToModel(ExpertUpdateDTO expertUpdateDTO);

    Expert loginDtoToModel(LoginDTO loginDTO);
    Expert changePasswordDtoToModel(ChangePasswordDTO changePasswordDTO);

    ExpertResponseDTO modelToResponseDto(Expert expert);
}
