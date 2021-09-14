package com.kodilla.travelagencyfe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
