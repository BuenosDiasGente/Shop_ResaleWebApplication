package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;

    private Integer userId; //Users??

    private String userImage;// Image??

    private String name; //Users??

    private Long timeOfCreation;

    private String commentText;

}
