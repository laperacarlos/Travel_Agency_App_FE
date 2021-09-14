package com.kodilla.travelagencyfe.service;

import com.kodilla.travelagencyfe.domain.Status;
import com.kodilla.travelagencyfe.domain.Travel;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;

import java.util.List;
import java.util.stream.Collectors;

public class TravelService {
    private final BackendEndpoint backendEndpoint = new BackendEndpoint();
    private static TravelService travelService;

    public static TravelService getInstance() {
        if (travelService == null) {
            travelService = new TravelService();
        }
        return travelService;
    }

    public List<Travel> getTravels() {
        return backendEndpoint.getAllTravels();
    }

    public List<Travel> getOpenTravels() {
        return backendEndpoint.getOpenTravels();
    }

    public List<Travel> findByOriginAndDestination(String origin, String destination) {
        return getTravels().stream()
                .filter(t -> t.getDestination().contains(destination) && t.getOrigin().contains(origin))
                .collect(Collectors.toList());
    }

    public void save(Travel travel) {
        backendEndpoint.updateTravel(travel);
    }

    public void createTravel(Travel travel) {
        travel.setStatus(Status.OPENED);
        backendEndpoint.createTravel(travel);
    }

    public void delete(Travel travel) {
    }
}
