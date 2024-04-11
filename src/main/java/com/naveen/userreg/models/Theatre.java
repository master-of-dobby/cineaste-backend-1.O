package com.naveen.userreg.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "theatre")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String Location;
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
}
