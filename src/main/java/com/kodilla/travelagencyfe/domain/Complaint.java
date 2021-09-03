package com.kodilla.travelagencyfe.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Complaint {
    private Long id;
    private Reservation reservation;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime closingDate;
    private ComplaintAnswer complaintAnswer;
    private Status status;

    public Complaint(Reservation reservation, String description, LocalDateTime creationDate, Status status) {
        this.reservation = reservation;
        this.description = description;
        this.creationDate = creationDate;
        this.status = status;
    }
}
