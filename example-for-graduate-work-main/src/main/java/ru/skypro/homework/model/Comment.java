package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  //id комментария

    private LocalDateTime createdAt; //время создания объвления
    private String text; //текст объявления

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id",nullable = false)
    private User user; //внешний ключ id пользователя, который размести комментарий и стал автором

    @ManyToOne(fetch = FetchType.LAZY)
   // @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ad_id",nullable = false)
    private Ad ad; //внешний ключ id объявления, к которому разместили комментарии*/ // видимо это тоже лишнее, мы можем добраться через юзера
}
