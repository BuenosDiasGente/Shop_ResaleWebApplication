package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comment")
public class Comment {
    @Id
    @SequenceGenerator(name = "commentSequence", sequenceName = "comment_sequence", allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentSequence")
    private Integer id;  //id комментария

    @Column(name="created_at")
    private LocalDateTime createdAt; //время создания объвления

    private String text; //текст объявления

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id",nullable = false)
    private User user; //внешний ключ id пользователя, который разместил комментарий и стал автором

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id",nullable = false)
    private Ad ad; //внешний ключ id объявления, к которому разместили комментарии
}
