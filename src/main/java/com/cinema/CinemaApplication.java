package com.cinema;

import com.cinema.Entitee.Ticket;
import com.cinema.Service.Iservice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.io.File;

@SpringBootApplication
@AllArgsConstructor
public class CinemaApplication implements CommandLineRunner {
    private Iservice iservice;
       // private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args)
    {
        SpringApplication.run(CinemaApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        /*pour integrer le id de chaque film quand il sireailizer un film

        repositoryRestConfiguration.exposeIdsFor(File.class);

         */
       // repositoryRestConfiguration.exposeIdsFor(Ticket.class);
        iservice.initVille();
        iservice.initCinema();
        iservice.initCategorie();
        iservice.initFilm();
        iservice.initSalle();
        iservice.initPlace();
        iservice.initSeance();
        iservice.initProjection();
        iservice.initTicket();

    }
}
