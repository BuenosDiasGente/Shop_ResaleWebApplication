package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Data
public class CommentsDTO {
    Integer quantity;
    List<Comment> allComments;
}
