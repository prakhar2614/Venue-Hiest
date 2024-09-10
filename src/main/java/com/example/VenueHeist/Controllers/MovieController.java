package com.example.VenueHeist.Controllers;


import com.example.VenueHeist.RequestBody.AddMovieRequest;
import com.example.VenueHeist.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity addMovie(@RequestBody AddMovieRequest addMovieRequest){
        String response = movieService.addMovie(addMovieRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
