package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    // вспомогательный метод по полученияю id user
    @Named("authorToInt")
    default Integer authorToInt(User user) {
        return user.getId();
    }

    // вспомогательный метод для сохранения url image
    @Named("imageToString")
    default String imageToString(Image image) {
        if (image == null) {
            return null;}
        return "/users/me/image/" + image.getId();
    }

    //добавление комментария (DTO)
    @Mapping(target = "author", source = "user", qualifiedByName = "authorToInt")
    @Mapping(target = "pk", source = "comment.id")
    @Mapping(target = "authorImage", source = "user.image", qualifiedByName = "imageToString")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    CommentDTO entityToDTO(Comment comment);

    Comment CreateOrUpdateCommentDTOToEntity(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);


    }

