package com.example.VenueHeist.CustomExceptions;

public class SeatUnavailableException extends Exception{

    public SeatUnavailableException(String message){
        super(message);
    }
}
