package com.naveen.userreg.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ElementCollection
    private List<String> genre;
    private String language;
    private Double rating;
    @Column(name = "release_date")
    private Date releaseDate;
    @Column(name = "trailer_url")
    private String trailerUrl;
    @Column(name = "image_url")
    private String imageUrl;
    private String description;
}
