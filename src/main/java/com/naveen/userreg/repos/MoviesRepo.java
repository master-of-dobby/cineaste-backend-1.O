package com.naveen.userreg.repos;

import com.naveen.userreg.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepo extends JpaRepository<Movie, Long> {

    Optional<Movie> findByName(String name);
}
