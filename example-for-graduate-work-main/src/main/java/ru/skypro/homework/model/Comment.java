package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "users_id")
    private User userId;

    @ManyToOne
    //  @JoinColumn(name = "avatar_id")
    private Image userImage;

    @ManyToOne
    // @JoinColumn(name = "user_name")
    private User name;

    private Long timeOfCreation;

    private String commentText;

    @ManyToOne
    //@JoinColumn(name = "ad_id")
    private Ad adId;
}
