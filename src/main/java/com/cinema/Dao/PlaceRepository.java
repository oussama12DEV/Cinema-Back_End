package com.cinema.Dao;

import com.cinema.Entitee.Cinema;
import com.cinema.Entitee.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface PlaceRepository extends JpaRepository<Place,Long > {
}
