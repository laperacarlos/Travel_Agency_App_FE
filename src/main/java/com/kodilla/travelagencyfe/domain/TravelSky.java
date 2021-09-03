package com.kodilla.travelagencyfe.domain;

import java.time.LocalDateTime;
import java.util.List;

public class TravelSky {
    private Long id;
    private String origin;
    private String destination;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private Status status;
    private LocalDateTime creationDate;
    private List<Reservation> listOfReservations;
}
