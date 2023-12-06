package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Override
    public List<Comment> getComments(Integer adId, Authentication authentication) {

        User user = userRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        List<Comment> commentsByAdPk = commentRepository.findCommentsByAd_Pk(adId);

        return commentsByAdPk;
    }

    @Override
    public Comment addComment(Integer adId, Comment comment, Authentication authentication) {

        User user = userRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Optional<Ad> adById = adRepository.findAdById(adId);
        if (!adById.isPresent()) {
            return null;
        } else {
            comment.setAd(adById.get());
            comment.setUser(user);
            //comment.setUser(userRepository.findById(adById.get().getAuthor().getId()).get());
            return commentRepository.save(comment);
        }
    }

    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        User user = userRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        //здесь логика если админ можно удалять все, если юзер только свое
        commentRepository.deleteCommentByAd_PkAndId(adId, commentId);

    }

    @Override
    public Comment patchComment(Integer adId, Integer commentId, String text, Authentication authentication) {
        User user = userRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        //Optional<Ad> adById = adRepository.findAdById(adId);
        //Optional<Comment> commentById = commentRepository.findById(commentId);

        /*if (!adById.isPresent() || !commentById.isPresent()) {
            return null;
        } else {
            commentById.get().setText(text);
        }
        Comment comment = commentById.get();
        commentRepository.save(comment);
        return comment;*/

        Optional<Comment> commentByAdPkAndId = commentRepository.findCommentByAd_PkAndId(adId, commentId);
        if (!commentByAdPkAndId.isPresent()) {
            return null;
        } else {
            commentByAdPkAndId.get().setText(text);
        }
        Comment comment = commentByAdPkAndId.get();
        commentRepository.save(comment);
        return comment;

    }
}
