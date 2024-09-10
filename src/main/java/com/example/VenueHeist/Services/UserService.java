package com.example.VenueHeist.Services;

import com.example.VenueHeist.Entities.User;
import com.example.VenueHeist.Repositories.UserRepository;
import com.example.VenueHeist.RequestBody.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String addUser(AddUserRequest addUserRequest){
        User user = User.builder()
                .name(addUserRequest.getName())
                .emailId(addUserRequest.getEmail())
                .build();

        user = userRepository.save(user);
        return  "User has been saved with user Id : "+user.getUserId();
    }
}
