package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<Comment> getComments(Integer adId, Authentication authentication);

    Comment addComment(Integer adId, Comment comment, Authentication authentication);

    void deleteComment(Integer adId, Integer commentId, Authentication authentication);

    CommentDTO patchComment(Integer adId, Integer commentId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO, Authentication authentication);

}
