package com.naveen.userreg.repos;

import com.naveen.userreg.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepo extends JpaRepository<Show, Long> {

    List<Show> findByTheatreId(Long id);

    List<Show> findByTheatreIdAndMovieId(Long theatreId, Long movieId);

    int deleteByMovieId(Long id);

    List<Show> getByMovieId(Long id);
}
