package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.SubServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.SubServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.SubService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubServiceMapper {
    SubServiceMapper INSTANCE = Mappers.getMapper(SubServiceMapper.class);

    SubService responseDtoToModel(SubServiceResponseDTO subServiceResponseDTO);

    SubService requestDtoToModel(SubServiceRequestDTO subServiceRequestDTO);

    SubServiceResponseDTO modelToResponseDto(SubService subService);

    SubServiceRequestDTO modelToRequestDto(SubService subService);
}
