package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDTO {

    Long id;

    Integer userId;//Users??

    String  adImage;//Image??

    String header;
    Integer price;
    String description;
}
