package ir.maktab.finalprojectphase3.HomeServiceProvider.service;

import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.CommentRequestDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.request.UserEmailDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.dto.response.CommentResponseDTO;
import ir.maktab.finalprojectphase3.HomeServiceProvider.data.model.Comment;

import java.util.List;

public interface CommentService {
    void add(Comment comment);

    void remove(CommentRequestDTO commentRequestDTO);

    void update(CommentRequestDTO commentRequestDTO);

    List<CommentResponseDTO> selectAllExpertComments(UserEmailDTO expertEmail);
}
