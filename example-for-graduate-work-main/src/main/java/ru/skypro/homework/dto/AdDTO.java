package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDTO {

    Long id;

    Integer userId;//Users??

    String  adImage;//Image??

    String header;
    Integer price;
    String description;
}
