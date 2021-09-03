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

    public Travel(Long id, String origin, String destination, LocalDate departureDate, LocalDate returnDate, Status status, LocalDateTime creationDate) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.status = status;
        this.creationDate = creationDate;
    }
}
