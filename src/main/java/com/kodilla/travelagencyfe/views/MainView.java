package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.component.TravelForm;
import com.kodilla.travelagencyfe.domain.TravelService;
import com.kodilla.travelagencyfe.domain.Travel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    private TravelService travelService = TravelService.getInstance();
    private Grid<Travel> grid = new Grid<>(Travel.class);
    private TextField filterByOrigin = new TextField();
    private TextField filterByDestination = new TextField();
    private Button showTravels = new Button("Travels");
    private Button showUsers = new Button("Users");
    private Button showReservations = new Button("Reservations");
    private TextField from = new TextField("From:");
    private TextField to = new TextField("To:");
    private DatePicker departureDate= new DatePicker( "Departure date");
    private DatePicker returnDate= new DatePicker("Return date");
    private Button search = new Button("Search");

    public MainView() {
        grid.setColumns("origin", "destination", "departureDate", "returnDate");
        grid.addComponentColumn(travel-> reservationButton(grid, travel));
        configFilterByOrigin();
        configFilterByDestination();

        showUsers.addClickListener(e -> {
            showUsers.getUI().ifPresent(ui ->
                    ui.navigate("users"));
        });
        showReservations.addClickListener(e -> {
            showReservations.getUI().ifPresent(ui ->
                    ui.navigate("reservations"));
        });

        showReservations.addClickListener(e -> {
            showReservations.getUI().ifPresent(ui ->
                    ui.navigate("travels"));
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterByOrigin, filterByDestination, showUsers, showReservations);

        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();

        HorizontalLayout searchTravelSky = new HorizontalLayout(from, to, departureDate, returnDate, search);
        searchTravelSky.setSizeFull();
        add(toolbar, mainContent, searchTravelSky);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(travelService.getTravels());
    }

    private void updateOrigin() {
        grid.setItems(travelService.findByOrigin(filterByOrigin.getValue()));
    }

    private void updateDestination() {
        grid.setItems(travelService.findByDestination(filterByDestination.getValue()));
    }

    private void configFilterByOrigin() {
        filterByOrigin.setPlaceholder("Filter by origin");
        filterByOrigin.setClearButtonVisible(true);
        filterByOrigin.setValueChangeMode(ValueChangeMode.EAGER);
        filterByOrigin.addValueChangeListener(e -> updateOrigin());
    }

    private void configFilterByDestination() {
        filterByDestination.setPlaceholder("Filter by destination");
        filterByDestination.setClearButtonVisible(true);
        filterByDestination.setValueChangeMode(ValueChangeMode.EAGER);
        filterByDestination.addValueChangeListener(e -> updateDestination());
    }

    private Button reservationButton(Grid<Travel> grid, Travel travel){
        Button button = new Button("Reservation");
        return button;
    }

}
