package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public List<Comment> getComments(Integer adId) {
        return commentRepository.getCommentsByAdIdIs(adId);
    }

    @Override
    public CommentDTO addComment(Integer adId, Comment comment) {
        return null;
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {

    }

    @Override
    public Comment patchComment(Integer adId, Integer commentId, String text) {
        return null;
    }
}
