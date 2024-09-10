package com.example.VenueHeist.Repositories;

import com.example.VenueHeist.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findMovieByMovieName(String movieName);
}
