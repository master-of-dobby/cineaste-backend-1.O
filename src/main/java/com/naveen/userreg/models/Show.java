package com.naveen.userreg.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "shows")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id", referencedColumnName = "id")
    private Theatre theatre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @Column(name = "show_time")
    private Date showTime;

    @Column(name = "platinum_remaining")
    private int platinumRemaining;

    @Column(name = "diamond_remaining")
    private int diamondRemaining;

    @Column(name = "gold_remaining")
    private int goldRemaining;

    @Column(name = "eligible_age")
    private int eligibleAge;

//    @Column(name = "is_available")
//    private boolean isAvailable;
}
