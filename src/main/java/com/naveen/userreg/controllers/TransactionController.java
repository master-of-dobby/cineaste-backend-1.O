package com.naveen.userreg.controllers;

import com.naveen.userreg.models.Transaction;
import com.naveen.userreg.services.TicketService;
import com.naveen.userreg.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000",  "http://cineaste-fe.s3-website.eu-north-1.amazonaws.com"})
public class TransactionController {

    private final TicketService ticketService;
    private final TransactionService transactionService;

//    @PostMapping("/transaction")
//    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
//        Optional<Transaction> prevTransaction = transactionService.getTransaction(transaction.getId());
//        if(prevTransaction.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
//        }
//
//        Transaction savedTransaction = transactionService.addTransaction(transaction);
//
//        if(savedTransaction == null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
//    }

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.addTransaction(transaction);

        if (savedTransaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable(name = "id") Long id){
        Optional<Transaction> t = transactionService.getTransaction(id);

        if(t.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(t.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
