package com.example.VenueHeist.Controllers;

import com.example.VenueHeist.RequestBody.AddTheaterRequest;
import com.example.VenueHeist.RequestBody.AddTheaterSeatsRequest;
import com.example.VenueHeist.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("addTheater")
    public ResponseEntity addTheater(AddTheaterRequest addTheaterRequest){
        String response = theaterService.addTheater(addTheaterRequest);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/addTheaterSeats")
    public ResponseEntity addTheaterSeats(AddTheaterSeatsRequest addTheaterSeatsRequest){
        String response = theaterService.addTheaterSeats(addTheaterSeatsRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
