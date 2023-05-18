package com.pruebaval.amazonas.pruebaval.task;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pruebaval.amazonas.pruebaval.repository.EpisodeRepository;

@Component
public class EpisodeCleanupTask {
    private final EpisodeRepository episodeRepository;

    public EpisodeCleanupTask(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    @Scheduled(fixedRate = 3600000) // Se ejecuta cada hora (3600000 milisegundos = 1 hora)
    public void cleanupExpiredEpisodes() {
        episodeRepository.deleteByExpirationTimeBefore(LocalDateTime.now());
    }
}
