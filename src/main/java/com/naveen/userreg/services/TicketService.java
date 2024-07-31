package com.naveen.userreg.services;

import com.naveen.userreg.models.Ticket;
import com.naveen.userreg.models.Transaction;
import com.naveen.userreg.repos.TicketRepo;
import com.naveen.userreg.repos.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepo ticketRepo;
    private final TransactionRepo transactionRepo;


    public Ticket addTicket(Ticket ticket) {
        Transaction transaction = ticket.getTransaction();

        if(transaction != null && transaction.getId() != null){
            transaction = transactionRepo.save(transaction);
            ticket.setTransaction(transaction);
        }

        return ticketRepo.save(ticket);
    }

    public Ticket save(Ticket ticket) {
        // Log the ticket to be saved
        System.out.println("Saving ticket: " + ticket);

        // Perform any necessary logic here
        return ticketRepo.save(ticket);
    }

    public Ticket getTicket(Long id) {
        return ticketRepo.findById(id).orElseThrow();
    }

    public List<Ticket> getTickets() {
        return ticketRepo.findAll();
    }

    public void deleteTicket(Long id) {
        ticketRepo.deleteById(id);
    }
}
