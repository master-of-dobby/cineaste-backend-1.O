package com.naveen.userreg.controllers;

import com.naveen.userreg.models.Movie;
import com.naveen.userreg.models.Theatre;
import com.naveen.userreg.services.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class TheatreController {

    @Autowired
    private final TheatreService theatreService;

    @PostMapping("theatre")
    public ResponseEntity<String> addTheatre(@RequestBody Theatre theatre) {
        return ResponseEntity.status(HttpStatus.OK).body(theatreService.addTheatre(theatre));
    }

    @GetMapping("/theatres")
    public ResponseEntity<List<Theatre>> getTheatres() {
        return ResponseEntity.status(HttpStatus.OK).body(theatreService.getTheatres());
    }

    @GetMapping("/theatre/{id}")
    public ResponseEntity<Theatre> getTheatre(@PathVariable Long id) {
        Optional<Theatre> theatreOptional = theatreService.getTheatre(id);
        System.out.println(theatreOptional.isPresent() + " " + theatreOptional.get().getMovie());

        if (theatreOptional.isPresent()) {
            return ResponseEntity.ok(theatreOptional.get());

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/theatre")
    public ResponseEntity<Theatre> getTheatreByName(@RequestParam(name = "name") String name) {
        Optional<Theatre> theatreOptional = theatreService.getTheatreByName(name);

        if (theatreOptional.isPresent()) {
            return ResponseEntity.ok(theatreOptional.get());

        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/theatres-by-movie/{id}")
    public ResponseEntity<List<Theatre>> getTheatresList(@PathVariable Long id){
//        if(!theatreService.existsById(id)) {
//            System.out.println("Theatre NOT FOUND!");
//            return ResponseEntity.notFound().build();
//        }
        List<Theatre> theatreList = theatreService.getTheatreByMovieId(id);

        System.out.print(theatreList);

        return ResponseEntity.status(HttpStatus.OK).body(theatreList);
    }


    @PutMapping("/theatre/{id}")
    public ResponseEntity<Theatre> updateTheatre(@PathVariable Long id, @RequestBody Theatre theatre) throws ChangeSetPersister.NotFoundException {
        if (!theatreService.existsById(id)) {
            System.out.println("Theatre NOT FOUND!");
            return ResponseEntity.notFound().build();
        }
        theatreService.updateTheatre(id, theatre);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/theatre/{id}")
    public ResponseEntity<Theatre> deleteTheatre(@PathVariable Long id) {
        if (!theatreService.existsById(id)) {
            System.out.println("Theatre NOT FOUND!");
            return ResponseEntity.notFound().build();
        }
        theatreService.deleteTheatre(id);
        return ResponseEntity.ok().build();
    }


}
