package com.naveen.userreg.services;

import com.naveen.userreg.models.FilmyPass;
import com.naveen.userreg.repos.FilmyPassRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmyPassService {

    private final FilmyPassRepo filmyPassRepo;

    public FilmyPass addPass(FilmyPass filmyPass) {
        return filmyPassRepo.save(filmyPass);
    }

    public List<FilmyPass> getPasses() {
        return filmyPassRepo.findAll();
    }

    public FilmyPass getPassByUserId(Long id) { return filmyPassRepo.findByUserId(id); }

    public FilmyPass updatePass(Long id, FilmyPass pass) {
        FilmyPass p = filmyPassRepo.findById(id).orElseThrow();

        p.setPassType(pass.getPassType());
        p.setMoviesLeft(pass.getMoviesLeft());
        p.setPassType(pass.getPassType());
        p.setPurchasedOn(pass.getPurchasedOn());
        p.setValidity(pass.getValidity());
        p.setUser(pass.getUser());

        return p;
    }

    public FilmyPass deletePass(Long id) {

        FilmyPass deletedPass = getPassByUserId(id);

        filmyPassRepo.deleteById(id);

        return deletedPass;
    }
}
