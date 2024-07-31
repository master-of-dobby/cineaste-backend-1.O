package com.naveen.userreg.controllers;

import com.naveen.userreg.models.Ticket;
import com.naveen.userreg.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://cineaste-fe.s3-website.eu-north-1.amazonaws.com"})
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/book-ticket")
    public ResponseEntity<Ticket> bookTicket(@RequestBody Ticket ticket){
        Ticket bookedTicket = ticketService.addTicket(ticket);

        if(bookedTicket == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id){
        Ticket savedTicket = ticketService.getTicket(id);

        if(savedTicket == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedTicket);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getTickets(){
        List<Ticket> tickets = ticketService.getTickets();

        if(tickets == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(tickets);

    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Ticket> deleteTicket(@PathVariable Long id){
        Ticket ticket = ticketService.getTicket(id);

        if(ticket == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ticketService.deleteTicket(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
