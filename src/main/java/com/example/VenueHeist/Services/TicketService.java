package com.example.VenueHeist.Services;

import com.example.VenueHeist.CustomExceptions.SeatUnavailableException;
import com.example.VenueHeist.Entities.Show;
import com.example.VenueHeist.Entities.ShowSeat;
import com.example.VenueHeist.Entities.Ticket;
import com.example.VenueHeist.Entities.User;
import com.example.VenueHeist.Repositories.ShowRepository;
import com.example.VenueHeist.Repositories.ShowSeatRepository;
import com.example.VenueHeist.Repositories.TicketRepository;
import com.example.VenueHeist.Repositories.UserRepository;
import com.example.VenueHeist.RequestBody.BookTicketRequest;
import com.example.VenueHeist.Response.ShowTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    public String bookTicket(BookTicketRequest bookTicketRequest) throws Exception{
        Optional<Show> optionalShow = showRepository.findById(bookTicketRequest.getShowId());
        if(optionalShow.isEmpty()){
            throw new Exception("Invalid show id");
        }
        Show show = optionalShow.get();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        int totalAmount = 0;
        int count = 0;
        for(String seatNo: bookTicketRequest.getSeatList()){
            for(ShowSeat showSeat: showSeatList) {
                if(showSeat.getSeatNo().equals(seatNo) && showSeat.getShow().equals(show)) {
                    if (!showSeat.isAvailable()) {
                        throw new SeatUnavailableException("Seat number :" + seatNo + " seat is already booked");
                    } else {
                        showSeat.setAvailable(Boolean.FALSE);
                        totalAmount += showSeat.getPrice();
                        count++;
                    }
                }
            }
        }
        if(count< bookTicketRequest.getSeatList().size()){
            throw new Exception("Invalid seat number");
        }

        User user = userRepository.findUserByEmailId(bookTicketRequest.getEmailId());
        Ticket ticket = Ticket.builder()
                .seatNoBooked(bookTicketRequest.getSeatList().toString())
                .totalAmountPaid(totalAmount)
                .show(show)
                .user(user)
                .build();

        show.getTicketList().add(ticket);
        user.getTicketList().add(ticket);
        ticket = ticketRepository.save(ticket);

        ShowTicketResponse showTicketResponse =  ShowTicketResponse.builder()
                .movieName(ticket.getShow().getMovie().getMovieName())
                .showDate(ticket.getShow().getShowDate())
                .showtime(ticket.getShow().getShowTime())
                .theaterInfo(show.getTheater().getName()+" "+ show.getTheater().getAddress())
                .seatNo(ticket.getSeatNoBooked())
                .totalAmountPaid(ticket.getTotalAmountPaid())
                .build();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hostyourwebyb@gmail.com");
        simpleMailMessage.setTo(user.getEmailId());
        simpleMailMessage.setSubject("Movie Ticket Confirmation");
        simpleMailMessage.setText(showTicketResponse.toString());
        javaMailSender.send(simpleMailMessage);

        showRepository.save(show);
        return "This is the ticket with ticket Id : "+ticket.getTicketId();
    }

    public String cancelTicket(int ticketId) throws Exception{
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if(optionalTicket.isEmpty()){
            throw new Exception("Invalid ticket Id");
        }
        Ticket ticket= optionalTicket.get();
        Show show = ticket.getShow();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        int amount =0;
        String seatsBooked = ticket.getSeatNoBooked();
        List<String> ticketSeats = Arrays.asList(seatsBooked.substring(1, seatsBooked.length()-1).split(", "));
        for(String seatNumber: ticketSeats){
            ShowSeat showSeat = showSeatRepository.findShowSeatBySeatNoAndShow(seatNumber, show);
            amount+=showSeat.getPrice();
            showSeat.setAvailable(true);
            showSeatRepository.save(showSeat);
        }

        ticketRepository.delete(ticket);
        return "Ticket with with Id -"+ticketId+" has been cancelled. Amount refunded = "+ amount;
    }

    public ShowTicketResponse showticket(int ticketId){
        Ticket ticket = ticketRepository.findById(ticketId).get();
        Show show = ticket.getShow();
        String movieName = show.getMovie().getMovieName();
        String theaterInfo = show.getTheater().getName()+", "+show.getTheater().getAddress();
        LocalDate showDate = show.getShowDate();
        LocalTime showtime = show.getShowTime();
        String seatNo= ticket.getSeatNoBooked();
        int totalAmountPaid = ticket.getTotalAmountPaid();

        ShowTicketResponse showTicketResponse = ShowTicketResponse.builder()
                .movieName(movieName)
                .theaterInfo(theaterInfo)
                .showDate(showDate)
                .showtime(showtime)
                .seatNo(seatNo)
                .totalAmountPaid(totalAmountPaid)
                .build();

        User user = ticket.getUser();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("hostyourwebyb@gmail.com");
        simpleMailMessage.setTo(user.getEmailId());
        simpleMailMessage.setSubject("Movie Ticket Confirmation");
        simpleMailMessage.setText(showTicketResponse.toString());
        javaMailSender.send(simpleMailMessage);
        return showTicketResponse;
    }
}
