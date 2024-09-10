package com.example.VenueHeist.Services;

import com.example.VenueHeist.CustomExceptions.InvalidMovieNameException;
import com.example.VenueHeist.CustomExceptions.InvalidTheaterException;
import com.example.VenueHeist.Entities.*;
import com.example.VenueHeist.Enums.SeatType;
import com.example.VenueHeist.Repositories.MovieRepository;
import com.example.VenueHeist.Repositories.ShowRepository;
import com.example.VenueHeist.Repositories.TheaterRepository;
import com.example.VenueHeist.RequestBody.AddShowRequest;
import com.example.VenueHeist.RequestBody.AddShowSeatsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;


    public String addShow(AddShowRequest addShowRequest) throws Exception{

        Movie movie = movieRepository.findMovieByMovieName(addShowRequest.getMovieName());
        if(movie == null){
            throw new InvalidMovieNameException("Movie Name entered does not exist");
        }
        Optional<Theater> theaterOptional = theaterRepository.findById(addShowRequest.getTheaterId());
        if(theaterOptional.isEmpty()){
            throw new InvalidTheaterException("Invalid Theater id");
        }
        Theater theater = theaterOptional.get();
        Show show= Show.builder()
                .showDate(addShowRequest.getShowDate())
                .showTime(addShowRequest.getShowTime())
                .movie(movie)
                .theater(theater)
                .build();

        movie.getShowList().add(show);
        theater.getShowList().add(show);
        show =  showRepository.save(show);

        return  "Show has been added with show id : " +show.getShowId();
    }

    public String addShowSeats(AddShowSeatsRequest addShowSeatsRequest) throws Exception{
        Optional<Show> optionalShow=showRepository.findById(addShowSeatsRequest.getShowId());

        if(optionalShow.isEmpty()){
            throw new Exception("Invalid show Id");
        }

        Show show = optionalShow.get();
        Theater theater = show.getTheater();

        List<TheaterSeats> theaterSeatsList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = new ArrayList<>();

        for(TheaterSeats theaterSeat : theaterSeatsList){

            String seatNo = theaterSeat.getSeatNo();
            SeatType seatType = theaterSeat.getSeatType();
            ShowSeat showSeat = ShowSeat.builder()
                    .isAvailable(true)
                    .foodAttached(false)
                    .seatNo(seatNo)
                    .seatType(seatType)
                    .show(show)
                    .build();
            if(seatType.equals(SeatType.PREMIUM)){
                showSeat.setPrice(addShowSeatsRequest.getPriceForPremiumSeats());
            }else{
                showSeat.setPrice(addShowSeatsRequest.getPriceForGoldSeats());
            }
            showSeatList.add(showSeat);
        }
        show.setShowSeatList(showSeatList);
        showRepository.save(show);

        return "Show seats have been added";

    }

}
