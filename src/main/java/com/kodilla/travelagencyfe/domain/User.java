package com.kodilla.travelagencyfe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime creationDate;
    private UserStatus isActive;
    private UserStatus isAdministrator;
    private List<Reservation> listOfReservations;

}

