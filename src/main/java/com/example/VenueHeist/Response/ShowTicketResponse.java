package com.example.VenueHeist.Response;

import com.example.VenueHeist.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowTicketResponse {
    private String movieName;
    private String theaterInfo;
    private LocalDate showDate;
    private LocalTime showtime;
    private String seatNo;
    private SeatType seatType;
    private int totalAmountPaid;
}
