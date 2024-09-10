package com.example.VenueHeist.RequestBody;

import lombok.Data;

@Data
public class AddTheaterSeatsRequest {

    private int noOfGoldSeats;
    private int noOfPremiumSeats;
    private int theaterId;
}
