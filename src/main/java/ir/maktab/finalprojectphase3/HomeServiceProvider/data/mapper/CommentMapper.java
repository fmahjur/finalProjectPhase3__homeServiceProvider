package ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.CommentRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.CommentResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment responseDtoToModel(CommentResponseDTO commentResponseDTO);

    Comment requestDtoToModel(CommentRequestDTO commentRequestDTO);

    CommentResponseDTO modelToResponseDto(Comment baseService);

    CommentRequestDTO modelToRequestDto(Comment baseService);
}
