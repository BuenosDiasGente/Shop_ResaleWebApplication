package ru.skypro.homework.service;

import ru.skypro.homework.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments(Long adId);

    Comment addComment(Long adId, Comment comment); //здесь я не уверен какой id брать, вроде нужен объявления

    void deleteComment(Long adId, Long commentId);

    Comment patchComment(Long adId, Long commentId, String text);


}
