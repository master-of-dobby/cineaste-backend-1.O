package com.naveen.userreg.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "theatre")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String location;
    @ElementCollection
    private List<String> amenities;
    private int capacity;
    @Column(name = "gold_capacity")
    private int goldCapacity;
    @Column(name = "diamond_capacity")
    private int diamondCapacity;
    @Column(name = "platinum_capacity")
    private int platinumCapacity;
    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

//    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
//    private List<Show> shows;
}
