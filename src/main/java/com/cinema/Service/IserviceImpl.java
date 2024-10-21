package com.cinema.Service;

import com.cinema.Dao.*;
import com.cinema.Entitee.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
@Transactional
public class IserviceImpl implements Iservice {

    private CinemaRepository cinemaRepository;
    private CategorieRepository categorieRepository;
    private VilleRepository villeRepository;
    private FilmRepository filmRepository;
    private SalleRepository salleRepository;
    private SeanceRepository seanceRepository;
    private ProjectionFilmRepository projectionFilmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    private PlaceRepository placeRepository;

    @Override
    public void initCinema() {
        villeRepository.findAll().forEach(v -> {
            Stream.of("Megrama", "Imax", "Chahrazad", "Nojoum").forEach(c -> {
                Cinema cinema = new Cinema();
                cinema.setName(c);
                cinema.setVille(v);
                cinema.setNombresalles(7 + (int) (Math.random() * 3));
                cinemaRepository.save(cinema);
            });
        });
    }
    @Override
    public void initFilm() {
        List<Categorie> categories = categorieRepository.findAll();
        double[] durees = new double[]{1, 1.30, 2.30, 3, 2.45};
        Stream.of("Forrest Gump", "Diamant sur canapé ", "Sijjin", "Cléo de 5 à 7 ", "Douleur et Gloire ", "Cash")
                .forEach(f -> {
                    Film film = new Film();
                    film.setTitre(f);
                    film.setDuree(durees[new Random().nextInt(durees.length)]);
                    film.setCategorie(categories.get(new Random().nextInt(categories.size())));
                    film.setPhoto(f.replaceAll(" ", "") + ".jpg");
                    filmRepository.save(film);
                });
    }

    @Override
    public void initVille() {
        Stream.of("Casablanca", "Marrakech", "Rabat", "Tanger").forEach(v -> {
            Ville ville = new Ville();
            ville.setName(v);
            villeRepository.save(ville);

        });
    }

    @Override
    public void initSalle() {
        cinemaRepository.findAll().forEach(c -> {
            for (int i = 0; i < c.getNombresalles(); i++) {
                Salle salle = new Salle();
                salle.setName("Salle" + (i + 1));
                salle.setNmbrepaces(20 + (int) (Math.random() * 30));
                salle.setCinema(c);
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlace() {
        salleRepository.findAll().forEach(s -> {
            for (int i = 0; i < s.getNmbrepaces(); i++) {
                Place place = new Place();
                place.setNumeroPlace(i + 1);
                place.setSalle(s);
                placeRepository.save(place);

            }
        });
    }

    @Override
    public void initCategorie() {
        Stream.of("Drama", "Action", "Comedie", "Guerre", "Fantastique").forEach(c -> {
            Categorie categorie = new Categorie();
            categorie.setName(c);
            categorieRepository.save(categorie);
        });

    }

    @Override
    public void initSeance() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:20", "19:00", "22:30").forEach(c -> {
            Seance seance = new Seance();

            try {
                seance.setHeureDebut(dateFormat.parse(c));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
               seanceRepository.save(seance);
        });
    }

    @Override
    public void initProjection() {
        double[] prix = new double[]{20, 30, 50, 100, 200};
        List<Film> films = filmRepository.findAll();
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    int index = new Random().nextInt(films.size());
                    Film film = films.get(index);
                    //je fait ca pour cree tout les film dans une salle
                    //filmRepository.findAll().forEach(film -> {

                    seanceRepository.findAll().forEach(seance -> {
                        ProjectionFilm projectionFilm = new ProjectionFilm();
                        projectionFilm.setDateProjection(new Date());
                        projectionFilm.setSalle(salle);
                        projectionFilm.setFilm(film);
                        projectionFilm.setSeance(seance);
                        projectionFilm.setPrix(prix[new Random().nextInt(prix.length)]);
                        projectionFilmRepository.save(projectionFilm);
                    });
                    //});
                });
            });
        });
    }

    @Override
    public void initTicket() {
        projectionFilmRepository.findAll().forEach(projectionFilm -> {
            projectionFilm.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPrix(projectionFilm.getPrix());
                ticket.setPlace(place);
                ticket.setReservee(false);
                ticket.setProjectionFilm(projectionFilm);
                ticket.setCodePayemment(0);
                ticketRepository.save(ticket);
            });
        });
    }
}
