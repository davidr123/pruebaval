package com.pruebaval.amazonas.pruebaval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebaval.amazonas.pruebaval.model.episodestatus;

@Repository
public interface EpisodeStatusRepository extends JpaRepository<episodestatus, Long> {
}
