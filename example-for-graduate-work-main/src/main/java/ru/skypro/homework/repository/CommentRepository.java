package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * Получаем список комментариев из таблицы "comment" по id объявления (внешний ключ)
     */
    List<Comment> findCommentsByAd_Pk(Integer adId);

    /**
     * Получаем список комментариев из таблицы "comment" по ad_id объявления (внешний ключ) и id самого коммента
     */
    Optional<Comment> findCommentByAd_PkAndId(Integer adId, Integer id);

    @Query(value = "SELECT c FROM Comment c WHERE c.ad.pk = :adId AND c.user.id = :idAuthor AND c.id = :id")
    Comment findAllCommentByAdIdAndAuthorIdAndIdComment(Integer adId, Integer idAuthor,Integer id);

    /**
     * Удаляем комментарий из таблицы "comment" принадлежащий объявлению(внешний ключ pk) по id комментария
     */
    void deleteCommentByAd_PkAndId(Integer pk, Integer id);


    /**
     * Удаляем все комментарии из таблицы "comment" принадлежащие объявлению(внешний ключ ad_id)
     */
    @Query(value = "DELETE FROM comment WHERE ad_id = :adId", nativeQuery = true)
    void deleteCommentsByAdId(Integer adId);

    @Query(value="DELETE FROM Comment c WHERE c.ad = :adId ")
    void deleteComment(Integer adId);
}
