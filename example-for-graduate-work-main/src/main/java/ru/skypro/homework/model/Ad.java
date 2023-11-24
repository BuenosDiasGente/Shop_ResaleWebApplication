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

    @OneToOne
 //   @JoinColumn(name="image_id")
    private Image adImage;//image

    private String header;//title
    private Integer price;
    private String description;
}
