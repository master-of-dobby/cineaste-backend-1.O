package com.naveen.userreg.controllers;

import com.naveen.userreg.models.Show;
import com.naveen.userreg.models.ShowDTO;
import com.naveen.userreg.services.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://cineaste-fe.s3-website.eu-north-1.amazonaws.com"})
public class ShowController {

    private final ShowService showService;

    @PostMapping("show")
    public ResponseEntity<Show> addShow(@RequestBody Show show) {
        Show addedShow = showService.addShow(show);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedShow);
    }

    @GetMapping("shows")
    public ResponseEntity<List<Show>> getAllShows() {
        List<Show> shows = showService.getAllShows();
        return ResponseEntity.ok().body(shows);
    }

    @GetMapping("shows/theatre/{theatreId}")
    public ResponseEntity<List<Show>> getShowsByTheatreId(@PathVariable Long theatreId) {
        List<Show> showDTOs = showService.getShowsByTheatreId(theatreId);
        if (showDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(showDTOs);
    }

    @GetMapping("shows/theatre/{theatreId}/movie/{movieId}")
    public ResponseEntity<List<ShowDTO>> getShowsByTheatreIdAndMovieId(@PathVariable Long theatreId,
                                                                       @PathVariable Long movieId) {
        List<ShowDTO> showDTOs = showService.getShowsByTheatreIdAndMovieId(theatreId, movieId);
        if (showDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(showDTOs);
    }
}
