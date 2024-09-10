package com.example.VenueHeist.Repositories;

import com.example.VenueHeist.Entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  ShowRepository extends JpaRepository<Show, Integer> {
}
