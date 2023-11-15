package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    Long id;

    Integer userId; //Users??

    String userImage;// Image??

    String name; //Users??

    Long timeOfCreation;

    String commentText;

}
