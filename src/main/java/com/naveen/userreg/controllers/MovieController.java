package com.naveen.userreg.controllers;


import com.naveen.userreg.models.Movie;
import com.naveen.userreg.models.Theatre;
import com.naveen.userreg.services.MovieService;
import com.naveen.userreg.services.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class MovieController {

    @Autowired
    private final MovieService movieService;

    @Autowired
    private final TheatreService theatreService;

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.addMovie(movie));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        if (!movieService.isExist(id)) {
            System.out.println("MOVIE NOT FOUND!");
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(movieService.updateMovie(id, movie));
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        if (!movieService.isExist(id)) {
            System.out.println("MOVIE NOT FOUND!");
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovie(id));
    }


    @GetMapping("/movie")
    public ResponseEntity<Movie> getMovie(@RequestParam String name) {
        Movie movie = movieService.getMovieByName(name);

        if (movie == null) {
            System.out.println("MOVIE NOT FOUND!");
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
        Movie movie = movieService.getMovie(id);

        if (!movieService.isExist(id)) {
            System.out.println("MOVIE NOT FOUND!");
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/movie/{id}/theatres")
    public ResponseEntity<List<Theatre>> moviesById(@PathVariable Long id) {
        List<Theatre> theatres = theatreService.getTheatreById(id);

        return ResponseEntity.status(HttpStatus.OK).body(theatres);
    }


}
