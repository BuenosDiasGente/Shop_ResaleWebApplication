package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;

    private Integer userId; //User??

    private String userImage;// Image??

    private String name; //User??

    private Long timeOfCreation;

    private String commentText;

}
