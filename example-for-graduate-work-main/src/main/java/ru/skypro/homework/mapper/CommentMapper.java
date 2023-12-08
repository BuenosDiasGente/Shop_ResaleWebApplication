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

    @Named("authorToInt")
    default Integer authorToInt(User user) {
        return user.getId();
    }

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
  //   @Mapping(target = "createdAt", qualifiedByName = "localDateTime")
    CommentDTO entityToDTO(Comment comment);

    //Получение списка комментариев
    List<CommentDTO> commentsDTOToList(List<CommentDTO> comments);

    //создание или обновление комментария
   // @Mapping(target = "createdAt", qualifiedByName = "localDateTime")
    Comment CreateOrUpdateCommentDTOToEntity(CreateOrUpdateCommentDTO createOrUpdateCommentDTO);

//    @Named("localDateTime")
//    default St localDateTime(String dataTime) {
//        return Integer.valueOf(parseInt(dataTime, 10));
//    }

    }

