package com.cinema.Dao;

import com.cinema.Entitee.Cinema;
import com.cinema.Entitee.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource


public interface SeanceRepository extends JpaRepository<Seance,Long > {
}
