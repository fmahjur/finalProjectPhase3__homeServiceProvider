package ir.maktab.finalprojectphase3.HomeServiceProvider.service.impl;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.CommentRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.CommentResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.mapper.CommentMapper;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Comment;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Expert;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.repository.CommentRepository;
import ir.maktab.finalprojectphase3.HomeServiceProvider.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final ExpertServiceImpl expertService;

    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(CommentRequestDTO commentRequestDTO) {
        Comment comment = CommentMapper.INSTANCE.requestDtoToModel(commentRequestDTO);
        comment.setDeleted(true);
        commentRepository.save(comment);
    }

    @Override
    public void update(CommentRequestDTO commentRequestDTO) {
        Comment comment = CommentMapper.INSTANCE.requestDtoToModel(commentRequestDTO);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentResponseDTO> selectAllExpertComments(UserEmailDTO expertEmail) {
        Expert expert = expertService.findByEmail(expertEmail.getEmail());
        List<Comment> allExpertComment = commentRepository.findByExpert(expert);
        List<CommentResponseDTO> commentResponseDTOList = new ArrayList<>();
        for (Comment comment : allExpertComment)
            commentResponseDTOList.add(CommentMapper.INSTANCE.modelToResponseDto(comment));
        return commentResponseDTOList;
    }
}
