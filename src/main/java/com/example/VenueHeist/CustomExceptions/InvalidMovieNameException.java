package com.example.VenueHeist.CustomExceptions;

public class InvalidMovieNameException extends Exception{

    public InvalidMovieNameException(String message)
    {
        super(message);
    }
}
