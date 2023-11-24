package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

/*
    @Query(value = "SELECT u FROM Ad u WHERE u.adId = :id")
    Ad findAdById(Long id);
*/


}
