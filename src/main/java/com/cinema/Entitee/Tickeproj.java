package com.cinema.Entitee;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;


@Projection(name = "Ticketproj",types = Ticket.class)
public interface Tickeproj {
     Long getId();
     String getNomClient();
     double getPrix();
     Integer getCodePayemment();
     boolean getreservee();
     Place getPlace();

}
