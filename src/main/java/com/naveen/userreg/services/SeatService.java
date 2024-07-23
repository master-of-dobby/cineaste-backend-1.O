package com.naveen.userreg.services;

import com.naveen.userreg.models.Seat;
import com.naveen.userreg.models.SeatStatus;
import com.naveen.userreg.repos.SeatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepo seatRepo;

    public Seat addSeat(Seat seat) {
        return seatRepo.save(seat);
    }

    public List<Seat> getSeatsByShowId(Long showId) {
        return seatRepo.findByShowId(showId);
    }

    public Seat getSeatByShowIdAndSeatNumber(Long showId, String seatNumber) {
        return seatRepo.findByShowIdAndSeatNumber(showId, seatNumber);
    }

    public Seat setSeatAsBooked(Long showId, String seatNumber) {
        Seat requiredSeat = seatRepo.findByShowIdAndSeatNumber(showId, seatNumber);

        requiredSeat.setSeatStatus(SeatStatus.BOOKED);

        System.out.println(requiredSeat);
        System.out.println(requiredSeat.getSeatStatus());

        seatRepo.save(requiredSeat);

        return requiredSeat;
    }


    // Add additional methods for seat management as needed
}
