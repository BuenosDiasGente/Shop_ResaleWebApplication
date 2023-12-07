package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.exception.NotFoundConfigException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static ru.skypro.homework.exception.ExceptionMessageConst.NOT_FOUND_EXCEPTION_DESCRIPTION;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

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
            //   comment.setCreatedAt(comment.getCreatedAt());
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
    public CommentDTO patchComment(Integer adId, Integer commentId,
                                   CreateOrUpdateCommentDTO createOrUpdateCommentDTO,
                                   Authentication authentication) {
        User user = userRepository.findUserByUserName(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Integer userId = user.getId();
        Ad ad = adRepository.findAdById(adId).get();
        Comment comment = commentRepository.findAllCommentByAdIdAndAuthorIdAndIdComment(adId, userId, commentId);
        if (isNull(comment)) {
            throw new NotFoundConfigException(NOT_FOUND_EXCEPTION_DESCRIPTION);
        } else {
            comment.setText(createOrUpdateCommentDTO.getText());
            comment.setUser(user);
            comment.setAd(ad);
            //comment.setCreatedAt(String.valueOf(LocalDateTime.now()));
//            String dateTime = parseLocalDateTime();
//            Integer parseString = parseString(dateTime);
//            comment.setCreatedAt(parseString);
            commentRepository.save(comment);
            return commentMapper.entityToDTO(comment);

        }
    }

    private String parseLocalDateTime() {
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return currentLocalDateTime.format(dateTimeFormatter);
    }

    private Integer parseString(String localDateTime) {
        String parse = new String(localDateTime);
        return Integer.parseInt(parse);

    }

}
