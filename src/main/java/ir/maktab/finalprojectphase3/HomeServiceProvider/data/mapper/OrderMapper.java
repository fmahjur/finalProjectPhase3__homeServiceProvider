package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OrderRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.OrderUpdateDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.SubmitOrderDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.OrderResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Orders responseDtoToModel(OrderResponseDTO orderResponseDTO);

    Orders requestDtoToModel(OrderRequestDTO orderRequestDTO);

    Orders submitDtoToModel(SubmitOrderDTO submitOrderDTO);
    Orders orderUpdateDtoToModel(OrderUpdateDTO orderUpdateDTO);

    OrderResponseDTO modelToResponseDto(Orders orders);

    OrderRequestDTO modelToRequestDto(Orders orders);
}
