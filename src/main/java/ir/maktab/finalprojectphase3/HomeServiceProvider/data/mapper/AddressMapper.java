package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.AddressRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.AddressResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address responseDtoToModel(AddressResponseDTO addressResponseDTO);

    Address requestDtoToModel(AddressRequestDTO addressRequestDTO);

    AddressResponseDTO modelToResponseDto(Address address);

    AddressRequestDTO modelToRequestDto(Address address);
}
