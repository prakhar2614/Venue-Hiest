package com.example.VenueHeist.Repositories;

import com.example.VenueHeist.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
