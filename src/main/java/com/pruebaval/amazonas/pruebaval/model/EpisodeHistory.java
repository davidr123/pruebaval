package com.pruebaval.amazonas.pruebaval.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EpisodeHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "episode_id", referencedColumnName = "id")
    private Episode episode;

    @Column(name = "consulted_at")
    private LocalDateTime consultedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public LocalDateTime getConsultedAt() {
        return consultedAt;
    }

    public void setConsultedAt(LocalDateTime consultedAt) {
        this.consultedAt = consultedAt;
    }

    
}
