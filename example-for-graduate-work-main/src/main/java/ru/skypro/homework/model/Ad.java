package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad {
    @Id
    private Long id;//Integer pk

    @ManyToOne
    // @JoinColumn(name = "users_id")
   private User userId;//author


    private String header;//title
    private Integer price;
    private String description;
}
