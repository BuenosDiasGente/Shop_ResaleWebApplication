package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAdDTO {
    @Size(min = 4, max = 32)
    private String title;
    @Min(0)
    @Max(10_000_000)
    private Integer price;
    @Size(min = 8, max = 64)
    private String description;
}
