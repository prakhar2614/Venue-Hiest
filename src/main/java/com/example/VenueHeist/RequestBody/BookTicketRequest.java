package com.example.VenueHeist.RequestBody;

import lombok.Data;

import java.util.List;

@Data
public class BookTicketRequest {
    private int showId;

    public List<String> seatList;

    private String emailId;
}
