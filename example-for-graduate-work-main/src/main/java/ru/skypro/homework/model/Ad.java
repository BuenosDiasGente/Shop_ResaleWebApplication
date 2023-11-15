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
    private Long id;

    @ManyToOne
    // @JoinColumn(name = "users_id")
    Users userId;

    @OneToOne
    private Image adImage;

    private String header;
    private Integer price;
    private String description;
}
