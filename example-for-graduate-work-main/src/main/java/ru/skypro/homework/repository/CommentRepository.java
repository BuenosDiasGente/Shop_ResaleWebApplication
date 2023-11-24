package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * Получаем список комментариев из таблицы "comment" по id объявления (внешний ключ)
     */
    @Query(value = "SELECT * FROM comment WHERE ad_id_id = :adId", nativeQuery = true)
    List<Comment> getCommentsByAdIdIs(@Param("adId") Long adId);




}