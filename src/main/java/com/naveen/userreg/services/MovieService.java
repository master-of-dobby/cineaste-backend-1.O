package com.naveen.userreg.services;

import com.naveen.userreg.models.Movie;
//import com.naveen.userreg.models.Seat;
//import com.naveen.userreg.models.Show;
//import com.naveen.userreg.models.Theatre;
import com.naveen.userreg.repos.MoviesRepo;
import com.naveen.userreg.repos.SeatRepo;
import com.naveen.userreg.repos.ShowRepo;
import com.naveen.userreg.repos.TheatreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

//import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private final MoviesRepo moviesRepo;

    @Autowired
    private final SeatRepo seatRepo;

    @Autowired
    private final ShowRepo showRepo;

    @Autowired
    private final TheatreRepo theatreRepo;

    public Movie addMovie(Movie movie){
        return moviesRepo.save(movie);
    }

    public List<Movie> getAllMovies(){
        return moviesRepo.findAll();
    }

//    public List<Movie> searchMovies(String query) {
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                                                .withQuery(multiMatchQuery(query, "name", "genre", "description"))
//                                                .build();
//
//        SearchHits<Movie> searchHits = elasticsearchTemplate.search(searchQuery, Movie.class);
//        return searchHits.stream().map(hit -> hit.getContent()).toList();
//    }

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

    @Transactional
    public void deleteMovie(Long id) {

        int t = theatreRepo.deleteByMovieId(id);

        moviesRepo.deleteById(id);

    }
}

