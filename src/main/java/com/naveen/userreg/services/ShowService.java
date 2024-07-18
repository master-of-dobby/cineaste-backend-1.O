package com.naveen.userreg.services;

import com.naveen.userreg.models.*;
import com.naveen.userreg.repos.MoviesRepo;
import com.naveen.userreg.repos.SeatRepo;
import com.naveen.userreg.repos.ShowRepo;
import com.naveen.userreg.repos.TheatreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepo showRepo;

    private final TheatreRepo theatreRepo;

    private final MoviesRepo moviesRepo;

    private final SeatRepo seatRepo;

    public Show addShow(Show show) {

        if (show.getId() != null) {
            throw new IllegalArgumentException("Id must be null for new entities");
        }

        //fetch and set the theatre
        Optional<Theatre> theatre = theatreRepo.findById(show.getTheatre().getId());
        theatre.ifPresent(show::setTheatre);

        // fetch and set the movie;
        Optional<Movie> movie = moviesRepo.findById(show.getMovie().getId());
        movie.ifPresent(show::setMovie);

        show = showRepo.save(show);

        int seatLimit = 1;
        char seatAlpha = 'A';
//        Long showId = show.getId();

        if(theatre.isPresent()) {

            for (int i = 1; i <= theatre.get().getPlatinumCapacity(); i++) {

                if (seatLimit == 16) {
                    seatLimit = 1;
                    seatAlpha++;
                }

                String seatNumber = seatAlpha + Integer.toString(seatLimit++);
                Seat st = new Seat();
                st.setShow(show);
                st.setSeatNumber(seatNumber);
                st.setSeatStatus(SeatStatus.AVAILABLE);

                seatRepo.save(st);
            }

            seatLimit = 1;
            seatAlpha++;

            // for diamond
            for (int i = 1; i <= theatre.get().getDiamondCapacity(); i++) {

                if (seatLimit == 16) {
                    seatLimit = 1;
                    seatAlpha++;
                }

                String seatNumber = seatAlpha + Integer.toString(seatLimit++);
                Seat st = new Seat();
                st.setShow(show);
                st.setSeatNumber(seatNumber);
                st.setSeatStatus(SeatStatus.AVAILABLE);

                seatRepo.save(st);
            }

            seatLimit = 1;
            seatAlpha++;

            // for gold too
            for (int i = 1; i <= theatre.get().getGoldCapacity(); i++) {

                if (seatLimit == 16) {
                    seatLimit = 1;
                    seatAlpha++;
                }

                String seatNumber = seatAlpha + Integer.toString(seatLimit++);
                Seat st = new Seat();
                st.setShow(show);
                st.setSeatNumber(seatNumber);
                st.setSeatStatus(SeatStatus.AVAILABLE);

                seatRepo.save(st);
            }

//            System.out.println(theatre.get().getPlatinumCapacity() + " " + theatre.get().getDiamondCapacity() + " " + theatre.get().getGoldCapacity());
        }
        else{
            System.out.println("Theatre NOT PRESENT!");
        }

        return show;
    }

    public List<Show> getAllShows() {
        return showRepo.findAll();
    }

    public List<Show> getShowsByTheatreId(Long theatreId) {
        return showRepo.findByTheatreId(theatreId);
    }

    public List<ShowDTO> getShowsByTheatreIdAndMovieId(Long theatreId, Long movieId) {
        List<Show> shows = showRepo.findByTheatreIdAndMovieId(theatreId, movieId);
        return shows.stream()
                       .map(this::convertToDTO)
                       .collect(Collectors.toList());
    }

    private ShowDTO convertToDTO(Show show) {
        ShowDTO dto = new ShowDTO();
        dto.setId(show.getId());
        dto.setShowTime(show.getShowTime());
        return dto;
    }


}
