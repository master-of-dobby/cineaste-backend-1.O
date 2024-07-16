package com.naveen.userreg.controllers;

import com.naveen.userreg.models.Seat;
import com.naveen.userreg.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
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

    // Add additional endpoints for seat management, booking, etc.
}
