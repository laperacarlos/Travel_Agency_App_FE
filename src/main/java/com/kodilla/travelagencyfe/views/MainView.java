package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.service.TravelService;
import com.kodilla.travelagencyfe.domain.Travel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private TravelService travelService = TravelService.getInstance();
    private Grid<Travel> grid = new Grid<>(Travel.class);
    private Button showTravels = new Button("Travels");
    private Button showUsers = new Button("Users");
    private Button showReservations = new Button("Reservations");
    private TextField fromOrigin = new TextField();
    private TextField toDestination = new TextField();
    private Button searchTravels = new Button("Search");

    public MainView() {
        grid.setColumns("origin", "destination", "departureDate", "returnDate");
        grid.addComponentColumn(travel-> reservationButton(grid, travel));
        grid.addComponentColumn(travel-> checkWeatherButton(grid, travel));

        fromOrigin.setPlaceholder("Search travel by origin");
        fromOrigin.setClearButtonVisible(true);

        toDestination.setPlaceholder("Search travel by destination");
        toDestination.setClearButtonVisible(true);

        searchTravels.addClickListener(e -> {
            searchUpdate();
        });

        showUsers.addClickListener(e -> {
            showUsers.getUI().ifPresent(ui ->
                    ui.navigate("users"));
        });

        showReservations.addClickListener(e -> {
            showReservations.getUI().ifPresent(ui ->
                    ui.navigate("reservations"));
        });

        showTravels.addClickListener(e -> {
            showReservations.getUI().ifPresent(ui ->
                    ui.navigate("travels"));
        });

        HorizontalLayout toolbar = new HorizontalLayout(fromOrigin, toDestination, searchTravels);

        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();

        HorizontalLayout searchTravelSky = new HorizontalLayout(showUsers, showTravels, showReservations);
        searchTravelSky.setSizeFull();
        add(toolbar, mainContent, searchTravelSky);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(travelService.getOpenTravels());
    }

    private void searchUpdate() {
        grid.setItems(travelService.findByOriginAndDestination(fromOrigin.getValue(), toDestination.getValue()));
    }

    private Button reservationButton(Grid<Travel> grid, Travel travel){
        Button button = new Button("Reservation");
        return button;
    }

    private Button checkWeatherButton(Grid<Travel> grid, Travel travel){
        Button button = new Button("Check weather in " + travel.getDestination());
        return button;
    }

}
