package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDTO {

    private Long id;

    private Integer userId;//Users??

    private String  adImage;//Image??

    private String header;
    private Integer price;
    private String description;
}
