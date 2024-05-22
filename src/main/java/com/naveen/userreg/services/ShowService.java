package com.naveen.userreg.services;

import com.naveen.userreg.models.Show;
import com.naveen.userreg.repos.ShowRepo;
import com.naveen.userreg.repos.TheatreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepo showRepo;
//    private final TheatreRepo

    public Show addShow(Show show) {
        return showRepo.save(show);
    }


    public List<Show> getAllShows() {
        return showRepo.findAll();
    }

    public List<Show> getShowByTheatreId(Long id) {
        List<Show> availableShows = showRepo.findByTheatreId(id);

        return availableShows;
    }
}
