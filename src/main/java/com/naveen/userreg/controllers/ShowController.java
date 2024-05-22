package com.naveen.userreg.controllers;

import com.naveen.userreg.models.Show;
import com.naveen.userreg.services.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ShowController {

    private final ShowService showService;

    @PostMapping("show")
    public ResponseEntity<Show> addShow( @RequestBody Show show){
        return ResponseEntity.status(HttpStatus.OK).body(showService.addShow(show));
    }

    @GetMapping("shows")
    public ResponseEntity<List<Show>> getAllShows(){
        return ResponseEntity.status(HttpStatus.OK).body(showService.getAllShows());
    }

    @GetMapping("show/{id}")
    public ResponseEntity<List<Show>> getShow(@PathVariable("id") Long id){
        List<Show> totalShows = showService.getShowByTheatreId(id);

        List<Show> availableShows = new ArrayList<>();

        Date currentTime = new Date();

        for(Show s : totalShows){
            if(s.getShowTime().after(currentTime)){
                availableShows.add(s);
            }
        }

        if(availableShows.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(availableShows);
    }



}
