package com.example.VenueHeist.Services;

import com.example.VenueHeist.Entities.Theater;
import com.example.VenueHeist.Entities.TheaterSeats;
import com.example.VenueHeist.Enums.SeatType;
import com.example.VenueHeist.Repositories.TheaterRepository;
import com.example.VenueHeist.RequestBody.AddTheaterRequest;
import com.example.VenueHeist.RequestBody.AddTheaterSeatsRequest;
import com.example.VenueHeist.Transformers.TheaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest){
        Theater theater = TheaterTransformer.convertRequestToEntity(addTheaterRequest);

        theater = theaterRepository.save(theater);

        return "New theater is saved with theater id - " +theater.getTheaterId();
    }

    public String addTheaterSeats(AddTheaterSeatsRequest addTheaterSeatsRequest) {
        int noOfGoldSeats = addTheaterSeatsRequest.getNoOfGoldSeats();
        int noOfPremiumSeats = addTheaterSeatsRequest.getNoOfPremiumSeats();

        List<TheaterSeats> theaterSeatsList = new ArrayList<>();
        Theater theater = theaterRepository.findById(addTheaterSeatsRequest.getTheaterId()).get();
        int row=1;
        for(int i=1;i<=noOfGoldSeats/5;i++){
            for(int j=0;j<5;j++){
                char ch = (char)('A'+j);
                String seatNo = i+""+ch;

                TheaterSeats seat = TheaterSeats.builder()
                        .seatNo(seatNo)
                        .seatType(SeatType.GOLD)
                        .theater(theater)
                        .build();

                theaterSeatsList.add(seat);
            }
            row++;
        }
        for(int j=0;j<noOfGoldSeats%5;j++){
            char ch = (char)('A'+j);
            String seatNo = row+""+ch;

            TheaterSeats seat = TheaterSeats.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.GOLD)
                    .theater(theater)
                    .build();

            theaterSeatsList.add(seat);
        }
        row++;
        for(int i=row;i<noOfPremiumSeats/5+row;i++){
            for(int j=0;j<5;j++){
                char ch = (char)('A'+j);
                String seatNo = i+""+ch;

                TheaterSeats seat = TheaterSeats.builder()
                        .seatNo(seatNo)
                        .seatType(SeatType.PREMIUM)
                        .theater(theater)
                        .build();

                theaterSeatsList.add(seat);
            }
        }
        row+=noOfPremiumSeats/5;
        for(int j=0;j<noOfPremiumSeats%5;j++){
            char ch = (char)('A'+j);
            String seatNo = row+""+ch;

            TheaterSeats seat = TheaterSeats.builder()
                    .seatNo(seatNo)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();

            theaterSeatsList.add(seat);
        }
        theater.setTheaterSeatList(theaterSeatsList);
        theaterRepository.save(theater);
        return "Theater seats has been added";
    }
}
