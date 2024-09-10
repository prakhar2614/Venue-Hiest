package com.example.VenueHeist.Controllers;

import com.example.VenueHeist.RequestBody.AddShowRequest;
import com.example.VenueHeist.RequestBody.AddShowSeatsRequest;
import com.example.VenueHeist.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("addShow")
    public ResponseEntity addShow(@RequestBody AddShowRequest addShowRequest){
        try {
            String response = showService.addShow(addShowRequest);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("addShowSeats")
    public ResponseEntity addShowSeats(@RequestBody AddShowSeatsRequest addShowSeatsRequest){
        try{
            String response = showService.addShowSeats(addShowSeatsRequest);
            return new ResponseEntity(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
