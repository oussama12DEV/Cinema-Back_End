package com.cinema.web;

import com.cinema.Dao.CinemaRepository;
import com.cinema.Dao.FilmRepository;
import com.cinema.Dao.TicketRepository;
import com.cinema.Dao.VilleRepository;
import com.cinema.Entitee.Cinema;
import com.cinema.Entitee.Film;
import com.cinema.Entitee.Ticket;
import com.cinema.Entitee.Ville;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class CinemaController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @GetMapping(value = "/imagefilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") long id) throws Exception{
        Film film = filmRepository.findById(id).get();
        String photoname = film.getPhoto();
        File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoname);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }
    @PostMapping("/PayerTicket")
    public List<Ticket> PayerTicket(@RequestBody InfoTicket infoTicket){
        List<Ticket> ListTicket = new ArrayList<>();
        infoTicket.getListId().forEach(IdTicket->{
            Ticket ticket = ticketRepository.findById(IdTicket).get();
            ticket.setReservee(true);
            ticket.setNomClient(infoTicket.getNomClient());
            ticket.setCodePayemment(infoTicket.getCodePayemment());
            ticketRepository.save(ticket);
            ListTicket.add(ticket);

        });
        return ListTicket;
    }


    @GetMapping("/listticket/{id}")
   public Ticket getticket(@PathVariable Long id)

    {
        return ticketRepository.findById(id).get();
   }
   @GetMapping("/villes/{id}")
    public List getcinema(@PathVariable(name = "id") long id ){
          List list = new ArrayList<>();
      Ville ville  = villeRepository.findById(id).get();
         ville.getCinemas().forEach(cinema -> {
             list.add(cinema.getName());
         });
        return list;
   }

}

@Data
class InfoTicket{
    private String nomClient;
    private Integer CodePayemment;
    private List<Long> listId = new ArrayList<>();
}