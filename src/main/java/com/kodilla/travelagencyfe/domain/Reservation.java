package com.kodilla.travelagencyfe.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    private Long id;
    private User user;
    private Travel travel;
    private TravelType travelType;
    private HotelStandard hotelStandard;
    private MealStandard mealStandard;
    private Complaint complaint;
    private LocalDateTime creationDate;
    private Status status;
}
