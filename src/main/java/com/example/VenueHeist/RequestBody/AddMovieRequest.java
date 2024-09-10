package com.example.VenueHeist.RequestBody;

import com.example.VenueHeist.Enums.Genre;
import com.example.VenueHeist.Enums.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data

public class AddMovieRequest {
    private String movieName;

    @Enumerated(value= EnumType.STRING)
    private Genre genre;

    @Enumerated(value= EnumType.STRING)
    private Language movieLanguage;

    private LocalDate releaseDate;

    private double duration;

    private double rating;
}
