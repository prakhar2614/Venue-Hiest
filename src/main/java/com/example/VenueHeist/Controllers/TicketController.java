package com.example.VenueHeist.Controllers;

import com.example.VenueHeist.RequestBody.BookTicketRequest;
import com.example.VenueHeist.Response.ShowTicketResponse;
import com.example.VenueHeist.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("bookTicket")
    public ResponseEntity bookTicket(@RequestBody BookTicketRequest bookTicketRequest){
        try{
            String response = ticketService.bookTicket(bookTicketRequest);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("cancelTicket")
    public ResponseEntity cancelTicket(@RequestParam int ticketId){
        try{
            String response = ticketService.cancelTicket(ticketId);
            return new ResponseEntity(response,HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("showTicket")
    public ShowTicketResponse showTicket(@RequestParam("ticketId") int ticketId){
        return ticketService.showticket(ticketId);
    }
}
