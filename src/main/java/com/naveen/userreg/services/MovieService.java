package com.naveen.userreg.services;

import com.naveen.userreg.models.Movie;
import com.naveen.userreg.repos.MoviesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private final MoviesRepo moviesRepo;

    public Movie addMovie(Movie movie){
        return moviesRepo.save(movie);
    }

    public List<Movie> getAllMovies(){
        return moviesRepo.findAll();
    }

    @Transactional
    public Movie updateMovie(Long id, Movie movie) {
        Movie existingMovie = moviesRepo.findById(id).orElseThrow(() -> new RuntimeException("MOVIE NOT FOUND with id " + id));

        existingMovie.setDescription(movie.getDescription());
        existingMovie.setGenre(movie.getGenre());
        existingMovie.setName(movie.getName());
        existingMovie.setLanguage(movie.getLanguage());
        existingMovie.setRating(movie.getRating());
        existingMovie.setReleaseDate(movie.getReleaseDate());
        existingMovie.setImageUrl(movie.getImageUrl());
        existingMovie.setTrailerUrl(movie.getTrailerUrl());



        return  moviesRepo.save(existingMovie);
    }

    public boolean isExist(Long id){
        return moviesRepo.existsById(id);
    }

    public Movie getMovie(Long id) {
        return moviesRepo.findById(id).orElseThrow();
    }

    public Movie getMovieByName(String name){
        return moviesRepo.findByName(name).orElseThrow();
    }

    public void deleteMovie(Long id) {
        moviesRepo.deleteById(id);
    }
}

