package com.pruebaval.amazonas.pruebaval.model;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table(name = "episodes")
public class Episode {
    @Id
    @NaturalId
    @Column(name = "id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("air_date")
    private String air_date;

    @JsonProperty("characters")
    @ElementCollection
    private List<String> characters;

    private String url;
    private String created;
 

    @Column(name = "from_external_api")
    private Boolean fromExternalAPI;

    @Column(name = "expiration_time")
private LocalDateTime expirationTime;


@OneToMany(mappedBy = "episode")
@JsonIgnore
private List<EpisodeHistory> history = new ArrayList<>();

@Column(name = "consultation_count")
private Integer consultationCount;

  // Constructor
  public Episode() {
    this.history = new ArrayList<>();
}


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCharacters() {
        return characters;
    }
    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }

    public String getAir_date() {
        return air_date;
    }
    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }
 
    // public Boolean getFromDatabase() {
    //     return fromDatabase;
    // }
    // public void setFromDatabase(Boolean fromDatabase) {
    //     this.fromDatabase = fromDatabase;
    // }
    public Boolean getFromExternalAPI() {
        return fromExternalAPI;
    }
    public void setFromExternalAPI(Boolean fromExternalAPI) {
        this.fromExternalAPI = fromExternalAPI;
    }
    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }
    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
    public List<EpisodeHistory> getHistory() {
        return history;
    }
    public void setHistory(List<EpisodeHistory> history) {
        this.history = history;
    }


    public Integer getConsultationCount() {
        return consultationCount;
    }


    public void setConsultationCount(Integer consultationCount) {
        this.consultationCount = consultationCount;
    }

    
 
}
