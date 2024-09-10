package com.example.VenueHeist.Transformers;

import com.example.VenueHeist.Entities.Theater;
import com.example.VenueHeist.RequestBody.AddTheaterRequest;

public class TheaterTransformer {

    public static Theater convertRequestToEntity(AddTheaterRequest addTheaterRequest){
        return Theater.builder()
                .name(addTheaterRequest.getName())
                .address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
                .build();

    }
}
