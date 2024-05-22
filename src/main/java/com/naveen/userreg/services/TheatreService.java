package com.naveen.userreg.services;

import com.naveen.userreg.models.Movie;
import com.naveen.userreg.models.Theatre;
import com.naveen.userreg.repos.MoviesRepo;
import com.naveen.userreg.repos.TheatreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheatreService {

    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private MoviesRepo moviesRepo;

    public String addTheatre(Theatre theatre) {
        theatreRepo.save(theatre);
        return "Theatre Added Successfully ";
    }

    public List<Theatre> getTheatres() {
        return theatreRepo.findAll();
    }

    public Optional<Theatre> getTheatre(Long id) {
        return theatreRepo.findById(id);
    }

    public Optional<Theatre> getTheatreByName(String name) {
        return theatreRepo.findTheatreByName(name);
    }

    public Theatre updateTheatre(Long id, Theatre theatre) {

        Theatre existingTheatre = theatreRepo.findById(id).orElseThrow();

        existingTheatre.setMovie(theatre.getMovie());
        existingTheatre.setAmenities(theatre.getAmenities());
        existingTheatre.setCapacity(theatre.getCapacity());
        existingTheatre.setDiamondCapacity(theatre.getDiamondCapacity());
        existingTheatre.setGoldCapacity(theatre.getGoldCapacity());
        existingTheatre.setPlatinumCapacity(theatre.getPlatinumCapacity());
        existingTheatre.setLocation(theatre.getLocation());
        existingTheatre.setName(theatre.getName());

        return theatreRepo.save(existingTheatre);

    }

    public Theatre deleteTheatre(Long id) {
        Theatre deletedTheatre = theatreRepo.findById(id).orElseThrow();

        theatreRepo.deleteById(id);

        return deletedTheatre;
    }

    public boolean existsById(Long id) {
        return theatreRepo.existsById(id);
    }

    public List<Theatre> getTheatreById(Long id) {
       List<Theatre> resTheatres = new ArrayList<>();
       List<Theatre> totTheatres = theatreRepo.findAll();

       for(Theatre t : totTheatres){
           if(t.getMovie().getId() == id){
               resTheatres.add(t);
           }
       }

       return resTheatres;

    }

    public List<Theatre> getTheatreByMovieId(Long id) {
        List<Theatre> theatres = theatreRepo.findAll();
        List<Theatre> resultTheatres = new ArrayList<>();

        for(Theatre t : theatres){
            System.out.println(t.getMovie());
            if(t.getMovie().getId() == id){
                resultTheatres.add(t);
            }
        }

        if(resultTheatres.isEmpty())
            System.out.println("NO Theatres Found!");


        return resultTheatres;

    }
}
