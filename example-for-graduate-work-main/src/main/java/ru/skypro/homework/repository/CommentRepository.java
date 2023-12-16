package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    /**
     * Получаем список комментариев из таблицы "comment" по id объявления (внешний ключ)
     * @param adId
     * @return List<Comment>
     */
    List<Comment> findCommentsByAd_Pk(Integer adId);

    /***
     * Получение комментария из б/д по id объявления, id автора и по id комментария
     * @param adId
     * @param idAuthor
     * @param id
     * @return
     */
    @Query(value = "SELECT c FROM Comment c WHERE c.ad.pk = :adId AND c.user.id = :idAuthor AND c.id = :id")
    Comment findAllCommentByAdIdAndAuthorIdAndIdComment(Integer adId, Integer idAuthor, Integer id);

    /**
     * Удаляем комментарий из таблицы "comment" принадлежащий объявлению(внешний ключ pk) по id комментария
     * @param pk
     * @param id
     */
    void deleteCommentByAd_PkAndId(Integer pk, Integer id);


}
