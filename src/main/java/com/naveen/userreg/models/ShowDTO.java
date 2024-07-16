package com.naveen.userreg.models;

import com.naveen.userreg.repos.ShowRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// ShowDTO.java
@Getter
@Setter
public class ShowDTO {
    private Long id;
    private Date showTime;
    private Double ticketPrice;

}
