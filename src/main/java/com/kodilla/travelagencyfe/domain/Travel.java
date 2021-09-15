package com.kodilla.travelagencyfe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Travel {
    private Long id;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Status status;
    private LocalDateTime creationDate;
    private List<Reservation> listOfReservations;
}
