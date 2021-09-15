package com.kodilla.travelagencyfe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Complaint {
    private Long id;
    private Reservation reservation;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime closingDate;
    private ComplaintAnswer complaintAnswer;
    private Status status;
}
