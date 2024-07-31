package com.naveen.userreg.repos;

import com.naveen.userreg.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheatreRepo extends JpaRepository<Theatre, Long> {

    Optional<Theatre> findTheatreByName(String name);

    int deleteByMovieId(Long id);
}
