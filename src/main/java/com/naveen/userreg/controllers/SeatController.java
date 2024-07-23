package com.naveen.userreg.controllers;

import com.naveen.userreg.models.Seat;
import com.naveen.userreg.models.SeatStatus;
import com.naveen.userreg.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://cineaste-fe.s3-website.eu-north-1.amazonaws.com"})
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/seat")
    public ResponseEntity<Seat> addSeat(@RequestBody Seat seat) {
        return ResponseEntity.status(HttpStatus.OK).body(seatService.addSeat(seat));
    }

    @GetMapping("/seats/{showId}")
    public ResponseEntity<List<Seat>> getSeatsByShowId(@PathVariable("showId") Long showId) {
        return ResponseEntity.status(HttpStatus.OK).body(seatService.getSeatsByShowId(showId));
    }

    @GetMapping("/seat/{showId}/{seatNumber}")
    public ResponseEntity<Seat> getSeatByShowIdAndSeatNumber(@PathVariable("showId") Long showId,
                                                             @PathVariable("seatNumber") String seatNumber) {
        Seat seat = seatService.getSeatByShowIdAndSeatNumber(showId, seatNumber);
        if (seat == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(seat);
    }

    @PutMapping("/seat/{showId}/{seatNumber}")
    public ResponseEntity<Seat> setSeatAsBooked(@PathVariable("showId") Long showId, @PathVariable("seatNumber") String seatNumber){
        Seat requiredSeat = seatService.setSeatAsBooked(showId, seatNumber);

        if(requiredSeat == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Add additional endpoints for seat management, booking, etc.
}
