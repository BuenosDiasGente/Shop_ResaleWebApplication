package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments(Integer adId);

    Comment addComment(Integer adId, Comment comment); //здесь я не уверен какой id брать, вроде нужен объявления

    void deleteComment(Integer adId, Integer commentId);

    Comment patchComment(Integer adId, Integer commentId, String text);

}
