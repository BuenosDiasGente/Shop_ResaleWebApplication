package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.model.Comment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    //Comment createDTOToComment(CreateOrUpdateCommentDTO createOrUpdateCommentDTO); //добавление комментария
    CommentDTO entityToDTO(Comment comment); //добавление комментария



}
