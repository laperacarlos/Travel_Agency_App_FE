package com.kodilla.travelagencyfe.endpoint;

import com.kodilla.travelagencyfe.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackendEndpoint {

    public List<Reservation> getAllReservations() {
        User user = new User("username", "email", true, false);
        Travel travel = new Travel(1L, "Warsaw", "Boston",
                                  LocalDate.of(2021, 11, 7),
                                  LocalDate.of(2021, 11, 27),
                                  Status.OPENED,
                                  LocalDateTime.now().withNano(0));
        Reservation reservation = new Reservation(user, travel, TravelType.BASIC, HotelStandard.TWO, MealStandard.ONE_MEAL, Status.OPENED);
        List<Reservation> reservationList = new ArrayList<>();
        reservationList.add(reservation);
        return reservationList;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(Arrays.asList(
                new User("username", "email", true, true),
                new User("username1", "email1", true, false)));
    }

    public List<Complaint> getAllComplaints() {
        User user = new User("username", "email", true, false);
        Travel travel = new Travel(1L, "Warsaw", "Boston",
                LocalDate.of(2021, 11, 7),
                LocalDate.of(2021, 11, 27),
                Status.OPENED,
                LocalDateTime.now().withNano(0));
        Reservation reservation = new Reservation(user, travel, TravelType.BASIC, HotelStandard.TWO, MealStandard.ONE_MEAL, Status.OPENED);
        return new ArrayList<>(Arrays.asList(new Complaint(reservation, "Complaint description", LocalDateTime.now().withNano(0), Status.OPENED)));
    }

    public List<ComplaintAnswer> getAllComplaintAnswers() {
        User user = new User("username", "email", true, false);
        Travel travel = new Travel(1L, "Warsaw", "Boston",
                LocalDate.of(2021, 11, 7),
                LocalDate.of(2021, 11, 27),
                Status.OPENED,
                LocalDateTime.now().withNano(0));
        Reservation reservation = new Reservation(user, travel, TravelType.BASIC, HotelStandard.TWO, MealStandard.ONE_MEAL, Status.OPENED);
        Complaint compliant = new Complaint(reservation, "Complaint description", LocalDateTime.now().withNano(0), Status.OPENED);
        return new ArrayList<>(Arrays.asList(new ComplaintAnswer(compliant, "answer", LocalDateTime.now().withNano(0))));
    }

    public List<Travel> getAllTravels() {
        List<Travel> travels = new ArrayList<>();
        travels.add(new Travel(1L, "Warsaw", "Boston",
                LocalDate.of(2021, 11, 7),
                LocalDate.of(2021, 11, 27),
                Status.OPENED,
                LocalDateTime.of(2021, 8, 19, 14, 10)));
        travels.add(new Travel(1L, "Warsaw", "Paris",
                LocalDate.of(2021, 11, 7),
                LocalDate.of(2021, 11, 27),
                Status.OPENED,
                LocalDateTime.of(2021, 8, 19, 14, 10)));
        travels.add(new Travel(1L, "Warsaw", "Boston",
                LocalDate.of(2021, 11, 7),
                LocalDate.of(2021, 11, 27),
                Status.OPENED,
                LocalDateTime.of(2021, 8, 19, 14, 10)));

        travels.add(new Travel(1L, "Dresden", "Acapulco",
                LocalDate.of(2021, 11, 7),
                LocalDate.of(2021, 11, 27),
                Status.OPENED,
                LocalDateTime.of(2021, 8, 19, 14, 10)));
        travels.add(new Travel(1L, "Warsaw", "New York",
                LocalDate.of(2021, 11, 7),
                LocalDate.of(2021, 11, 27),
                Status.OPENED,
                LocalDateTime.of(2021, 8, 19, 14, 10)));
        return travels;
    }


}
