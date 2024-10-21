package com.cinema.Entitee;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Entity @AllArgsConstructor @NoArgsConstructor @Data
public class Seance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
    @OneToMany(mappedBy = "seance")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<ProjectionFilm> projectionFilms;
}
