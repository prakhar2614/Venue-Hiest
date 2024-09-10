package com.example.VenueHeist.Repositories;

import com.example.VenueHeist.Entities.Show;
import com.example.VenueHeist.Entities.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {

    ShowSeat findShowSeatBySeatNoAndShow(String seatNo, Show show);
}
