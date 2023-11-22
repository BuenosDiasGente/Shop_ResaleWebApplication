package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getComments(Long adId) {
        return commentRepository.getCommentsByAdIdIs(adId);
    }

    @Override
    public Comment addComment(Long adId, Comment comment) {
        
        return null;
    }

    @Override
    public void deleteComment(Long adId, Long commentId) {

    }

    @Override
    public Comment patchComment(Long adId, Long commentId, String text) {
        return null;
    }
}
