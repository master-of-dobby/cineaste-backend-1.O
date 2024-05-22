package com.naveen.userreg.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "filmy_pass")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmyPass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long passId;
    @Column(name = "pass_type")
    private String passType;
    @Column(name = "movies_left")
    private int moviesLeft;
    private int price;
    @Column(name = "purchased_on")
    private Date purchasedOn;
    private Date validity;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

}
