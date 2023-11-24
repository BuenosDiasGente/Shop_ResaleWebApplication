package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    Comment createDTOToComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);

    @Mappings({@Mapping(target = "author", source = "user.id"),
            @Mapping(target = "authorImage", source ="user.image" ),
            @Mapping(target="authorFirstName",source = "user.firstName")
    })
    CommentDTO entityToDTO(Comment comment, User user); //добавление комментария
    List<CommentDTO> listEntityToCommentsDto(List<Comment> comments);
}
