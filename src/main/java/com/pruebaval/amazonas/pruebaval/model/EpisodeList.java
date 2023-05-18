package com.pruebaval.amazonas.pruebaval.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EpisodeList {
    @JsonProperty("results")
    private List<Episode> episodes;

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public List<Episode> getResults() {
        return episodes;
    }
}
