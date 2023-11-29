package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

   /* @Override
    public List<Comment> getComments(Integer adId) {
        return commentRepository.getCommentsByAdIdIs(adId);
    }*/

    @Override
    public List<Comment> getComments(Integer adId) {
        return commentRepository.findCommentsByAd_Pk(adId);
    }

    @Override
    public Comment addComment(Integer adId, Comment comment) {

        Optional<Ad> adById = adRepository.findAdById(adId);
        if (!adById.isPresent()) {
            return null;
        }
        comment.setAd(adById.get());
        comment.setUser(userRepository.findById(adById.get().getAuthor().getId()).get());
        return commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Integer adId, Integer commentId) {

        commentRepository.deleteCommentByIdAndAd_Pk(adId, commentId);

    }

    @Override
    public Comment patchComment(Integer adId, Integer commentId, String text) {
        return null;
    }
}
