package com.example.VenueHeist.Repositories;

import com.example.VenueHeist.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmailId(String emailId);
}
