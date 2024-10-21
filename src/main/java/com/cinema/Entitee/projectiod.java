package com.cinema.Entitee;

import lombok.Data;
import lombok.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;


@Projection(name = "p1",types = ProjectionFilm.class)
public interface projectiod {
    long getId();
     Date getDateProjection();
     Salle getSalle();
     Film getFilm();
     double getPrix();
     Collection<Ticket> getTickets();
     Seance getSeance();

}
