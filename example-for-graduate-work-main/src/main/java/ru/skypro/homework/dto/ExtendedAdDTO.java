package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAdDTO {
    Long adId;
    String userName;
    String userSurname;
    String userLogin;
    String userPhone;
    String adDescription;
    String adImage; //Image??
    Integer price;
    String adHeader;

}
