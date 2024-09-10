package com.example.VenueHeist.Services;

import com.example.VenueHeist.Entities.Movie;
import com.example.VenueHeist.Repositories.MovieRepository;
import com.example.VenueHeist.RequestBody.AddMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest addMovieRequest){
        Movie movie = Movie.builder()
                        .movieLanguage(addMovieRequest.getMovieLanguage())
                .movieName(addMovieRequest.getMovieName())
                .genre(addMovieRequest.getGenre())
                .duration(addMovieRequest.getDuration())
                .releaseDate(addMovieRequest.getReleaseDate())
                .rating(addMovieRequest.getRating())
                .build();
        movie = movieRepository.save(movie);
        return "The movie has been saved with movie id - "+movie.getMovieId();
    }
}
