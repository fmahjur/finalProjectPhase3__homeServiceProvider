package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.CreditRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.ExpertResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Credit;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditMapper {
    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);

    Credit responseDtoToModel(CreditRequestDTO creditRequestDTO);
    CreditRequestDTO modelToRequestDto(Credit credit);
}
