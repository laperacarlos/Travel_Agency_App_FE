package com.kodilla.travelagencyfe.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TravelService {

    private List<Travel> travels;
    private static TravelService travelService;

    private TravelService() {
        this.travels = exampleData();
    }

    public static TravelService getInstance() {
        if (travelService == null) {
            travelService = new TravelService();
        }
        return travelService;
    }

    public List<Travel> getTravels() {
        return new ArrayList<>(travels);
    }

    public void addBook(Travel travel) {
        this.travels.add(travel);
    }

    private List<Travel> exampleData() {
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

    public List<Travel> findByOrigin(String origin) {
        return travels.stream()
                .filter(t -> t.getOrigin().contains(origin))
                .collect(Collectors.toList());
    }

    public List<Travel> findByDestination(String destination) {
        return travels.stream()
                .filter(t -> t.getDestination().contains(destination))
                .collect(Collectors.toList());
    }

    public void save(Travel travel) {
        this.travels.add(travel);
    }

    public void delete(Travel travel) {
        this.travels.remove(travel);
    }


}
