package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateCommentDTO {
    @Size(min = 8, max = 64)
    String commentText;
}
