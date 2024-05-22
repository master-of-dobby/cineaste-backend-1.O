package com.naveen.userreg.repos;

import com.naveen.userreg.models.FilmyPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmyPassRepo extends JpaRepository<FilmyPass, Long> {

    public FilmyPass findByUserId(Long id);

}
