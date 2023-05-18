package com.pruebaval.amazonas.pruebaval.service;


import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pruebaval.amazonas.pruebaval.model.Episode;
import com.pruebaval.amazonas.pruebaval.model.EpisodeHistory;
import com.pruebaval.amazonas.pruebaval.repository.EpisodeRepository;
@Service
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    public Episode getEpisodeByNumber(Integer episodeNumber) {
        Optional<Episode> optionalEpisode = episodeRepository.findById(episodeNumber);
        if (optionalEpisode.isPresent()) {
            Episode episode = optionalEpisode.get();
            episode.setFromExternalAPI(false); // El episodio se obtuvo de la base de datos interna

            // Actualizar la fecha de expiración del episodio
            LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);
            episode.setExpirationTime(expirationTime);

            // Registrar la consulta en el historial
            EpisodeHistory history = new EpisodeHistory();
            history.setEpisode(episode);
            history.setConsultedAt(LocalDateTime.now());
            episode.getHistory().add(history);

            // Incrementar el contador de consultas
            episode.setConsultationCount(episode.getConsultationCount() + 1);

            return episode;
        } else {
            Episode episode = fetchEpisodeFromExternalAPI(episodeNumber);
            episode.setFromExternalAPI(true); // El episodio se obtuvo de la API externa
            episode.setId(episodeNumber); // Asignar el número de episodio como el id

            // Establecer la fecha de expiración del episodio
            LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);
            episode.setExpirationTime(expirationTime);

            // Registrar la consulta inicial en el historial
            EpisodeHistory history = new EpisodeHistory();
            history.setEpisode(episode);
            history.setConsultedAt(LocalDateTime.now());
            episode.getHistory().add(history);

            // Establecer el contador de consultas en 1
            episode.setConsultationCount(1);

            return episodeRepository.saveAndFlush(episode);
        }
    }

    private Episode fetchEpisodeFromExternalAPI(Integer episodeNumber) {
        String apiUrl = "https://rickandmortyapi.com/api/episode/" + episodeNumber;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Episode> response = restTemplate.getForEntity(apiUrl, Episode.class);
    
        if (response.getStatusCode().is2xxSuccessful()) {
            Episode episode = response.getBody();
            return episodeRepository.saveAndFlush(episode);
        } else {
            // Manejar el caso de error de la llamada a la API
            // Por ejemplo, puedes lanzar una excepción o devolver un episodio vacío
            return new Episode();
        }
    }

    public Episode getEpisodeFromDatabase(Integer episodeNumber) {
        Optional<Episode> optionalEpisode = episodeRepository.findById(episodeNumber);
        return optionalEpisode.orElse(null);
    }

    public Episode saveEpisode(Episode episode) {
        return episodeRepository.save(episode);
    }

}
