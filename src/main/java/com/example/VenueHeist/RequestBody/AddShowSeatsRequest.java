package com.example.VenueHeist.RequestBody;


import lombok.Data;

@Data
public class AddShowSeatsRequest {

    private int priceForGoldSeats;
    private int priceForPremiumSeats;

    private int showId;
}
