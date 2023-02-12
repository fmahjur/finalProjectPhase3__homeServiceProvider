package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OfferRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OfferResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    Offer responseDtoToModel(OfferResponseDTO baseServiceResponseDTO);

    Offer requestDtoToModel(OfferRequestDTO baseServiceRequestDTO);

    OfferResponseDTO modelToResponseDto(Offer offer);

    OfferRequestDTO modelToRequestDto(Offer offer);
}
