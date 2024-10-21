package com.cinema.Entitee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Entity @AllArgsConstructor @NoArgsConstructor

public class Ticket  {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomClient;
    private double prix ;
    @Column(unique = false,nullable = true)
    private Integer codePayemment;
    private boolean reservee;
    @ManyToOne
    private ProjectionFilm projectionFilm;
    @ManyToOne
    private Place place;
}
