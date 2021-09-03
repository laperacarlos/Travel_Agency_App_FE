package com.kodilla.travelagencyfe.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime creationDate;
    private boolean isActive;
    private boolean isAdministrator;
    private List<Reservation> listOfReservations;

    public User(String username, String email, boolean isActive, boolean isAdministrator) {
        this.username = username;
        this.email = email;
        this.isActive = isActive;
        this.isAdministrator = isAdministrator;
    }
}

