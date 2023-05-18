package com.pruebaval.amazonas.pruebaval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaval.amazonas.pruebaval.model.Episode;
import com.pruebaval.amazonas.pruebaval.service.EpisodeService;

@RestController
@RequestMapping("/episodes")
public class EpisodeController {
    
    private final EpisodeService episodeService;
    
    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }
    
    @GetMapping("/{episodeNumber}")
    public ResponseEntity<Episode> getEpisodeByNumber(@PathVariable Integer episodeNumber) {
        Episode episode = episodeService.getEpisodeByNumber(episodeNumber);
            // Consultar el historial del episodio y obtener el recuento
    int consultationCount = episode.getHistory().size();

    // Actualizar el recuento de consultas en el objeto Episode
    episode.setConsultationCount(consultationCount);

    // Guardar el episodio actualizado en la base de datos
    episodeService.saveEpisode(episode);
        return ResponseEntity.ok(episode);
    }
}
