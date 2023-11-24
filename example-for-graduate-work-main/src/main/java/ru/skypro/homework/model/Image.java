package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
   @Id
    private Long id;
    @Lob
    private byte[] picture;
/*
    @OneToOne(mappedBy = "image")
    private Ad ad;*/

}
