package com.kodilla.travelagencyfe.service;

import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;

public class ReservationService {
    private final BackendEndpoint backendEndpoint = new BackendEndpoint();
    private static ReservationService reservationService;

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void createReservation() {

    }
}
