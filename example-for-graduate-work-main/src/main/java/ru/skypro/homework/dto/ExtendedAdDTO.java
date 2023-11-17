package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ExtendedAdDTO {
    Long adId;
    private String userName;
    private String userSurname;
    private String userLogin;
    private String userPhone;
    private String adDescription;
    private String adImage; //Image??
    private Integer price;
    private String adHeader;
}
