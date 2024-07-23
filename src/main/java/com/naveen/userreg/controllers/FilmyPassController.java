package com.naveen.userreg.controllers;

import com.naveen.userreg.models.FilmyPass;
import com.naveen.userreg.services.FilmyPassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://cineaste-fe.s3-website.eu-north-1.amazonaws.com"})
public class FilmyPassController {

    private final FilmyPassService filmyPassService;

    @PostMapping("/pass")
    public ResponseEntity<FilmyPass> addPass(@RequestBody FilmyPass filmyPass) {
        return ResponseEntity.status(HttpStatus.OK).body(filmyPassService.addPass(filmyPass));
    }

    @GetMapping("/pass")
    public ResponseEntity<List<FilmyPass>> getPasses() {
        return ResponseEntity.status(HttpStatus.OK).body(filmyPassService.getPasses());
    }

    @GetMapping("pass/{id}")
    public ResponseEntity<FilmyPass> getPassByUser(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(filmyPassService.getPassByUserId(id));
    }

    @PutMapping("pass/{id}")
    public ResponseEntity<FilmyPass> updatePass(@PathVariable Long id, FilmyPass pass) {
        if (filmyPassService.getPassByUserId(id) == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserNotFound");
        }
        return ResponseEntity.status(HttpStatus.OK).body(filmyPassService.updatePass(id, pass));
    }

    @DeleteMapping("pass/{id}")
    public ResponseEntity<FilmyPass> deletePass(@PathVariable Long id) {
        if (filmyPassService.getPassByUserId(id) == null) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER_NOT_FOUND");
        }

        return ResponseEntity.status(HttpStatus.OK).body(filmyPassService.deletePass(id));
    }
}
