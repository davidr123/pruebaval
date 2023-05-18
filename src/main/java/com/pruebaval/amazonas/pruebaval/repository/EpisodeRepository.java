
package com.pruebaval.amazonas.pruebaval.repository;

import com.pruebaval.amazonas.pruebaval.model.Episode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    Optional<Episode> findById(Integer id); 
    @Modifying
    @Query("DELETE FROM Episode e WHERE e.expirationTime < :currentDateTime")
    void deleteByExpirationTimeBefore(@Param("currentDateTime") LocalDateTime currentDateTime);
}
