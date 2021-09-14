package com.kodilla.travelagencyfe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
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

    public Reservation(User user, Travel travel, TravelType travelType, HotelStandard hotelStandard, MealStandard mealStandard, Status status) {
        this.user = user;
        this.travel = travel;
        this.travelType = travelType;
        this.hotelStandard = hotelStandard;
        this.mealStandard = mealStandard;
        this.status = status;
    }
}
