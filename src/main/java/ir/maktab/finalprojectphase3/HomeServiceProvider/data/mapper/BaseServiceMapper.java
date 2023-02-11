package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.BaseServiceRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.BaseServiceResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.BaseService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BaseServiceMapper {
    BaseServiceMapper INSTANCE = Mappers.getMapper(BaseServiceMapper.class);

    BaseService responseDtoToModel(BaseServiceResponseDTO baseServiceResponseDTO);

    BaseService requestDtoToModel(BaseServiceRequestDTO baseServiceRequestDTO);

    BaseServiceResponseDTO modelToResponseDto(BaseService baseService);

    BaseServiceRequestDTO modelToRequestDto(BaseService baseService);
}
