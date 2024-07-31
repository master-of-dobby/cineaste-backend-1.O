package com.naveen.userreg.repos;

import com.naveen.userreg.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat, Long> {

    List<Seat> findByShowId(Long showId);

    Seat findByShowIdAndSeatNumber(Long showId, String seatNumber);

    int deleteByShowId(Long id);
}
