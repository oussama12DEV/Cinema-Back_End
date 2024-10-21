package com.cinema.Entitee;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private double Longitude;
    private double latitude;
    private double altitude;
    private int nombresalles;
    @ManyToOne
    private Ville ville;
    @OneToMany(mappedBy = "cinema")
    private Collection<Salle> salles;
}
