package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.component.ReservationForm;
import com.kodilla.travelagencyfe.domain.Reservation;
import com.kodilla.travelagencyfe.domain.Weather;
import com.kodilla.travelagencyfe.service.TravelService;
import com.kodilla.travelagencyfe.domain.Travel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    private final TravelService travelService = TravelService.getInstance();
    private final Grid<Travel> grid = new Grid<>(Travel.class);
    private final Button showTravels = new Button("Travels");
    private final Button showUsers = new Button("Users");
    private final Button showReservations = new Button("Reservations");
    private final TextField fromOrigin = new TextField();
    private final TextField toDestination = new TextField();
    private final Button searchTravels = new Button("Search");
    private final ReservationForm reservationForm = new ReservationForm(this);


    public MainView() {
        setComponents();
        HorizontalLayout topToolBar = new HorizontalLayout(fromOrigin, toDestination, searchTravels);
        HorizontalLayout mainContent = new HorizontalLayout(grid);
        HorizontalLayout bottomToolBar = new HorizontalLayout(showUsers, showTravels, showReservations);
        mainContent.setSizeFull();
        topToolBar.setSizeFull();
        bottomToolBar.setSizeFull();
        add(topToolBar, mainContent, bottomToolBar, reservationForm);
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
        button.addClickListener(event -> {
            reservationForm.setReservation(new Reservation());
            reservationForm.setTravelToReservation(travel);
        });
        return button;
    }

    private Button checkWeatherButton(Grid<Travel> grid, Travel travel){
        Button checkButton = new Button("Check weather in: " + travel.getDestination());
        Weather checkedWeather = travelService.checkWeather(travel.getDestination());
        checkButton.addClickListener(event -> {
            NativeButton buttonInside = new NativeButton("Have a nice day! :)");
            Span content = new Span("Actual weather in "+ travel.getDestination() +": " + checkedWeather.getDescription() +
                    ". Temperature: " + checkedWeather.getTemperature().getMetric() + " C.");
            Notification notification = new Notification(content, buttonInside);
            notification.setPosition(Notification.Position.MIDDLE);
            buttonInside.addClickListener(e -> notification.close());
            notification.open();
        });
        return checkButton;
    }

    private void setComponents() {
        grid.setColumns("origin", "destination", "departureDate", "returnDate");
        grid.addComponentColumn(travel-> reservationButton(grid, travel));
        grid.addComponentColumn(travel-> checkWeatherButton(grid, travel));

        fromOrigin.setPlaceholder("Search travel by origin");
        fromOrigin.setClearButtonVisible(true);

        toDestination.setPlaceholder("Search travel by destination");
        toDestination.setClearButtonVisible(true);

        searchTravels.addClickListener(e -> searchUpdate());

        showUsers.addClickListener(e -> showUsers.getUI().ifPresent(ui ->
                ui.navigate("users")));

        showReservations.addClickListener(e -> showReservations.getUI().ifPresent(ui ->
                ui.navigate("reservations")));

        showTravels.addClickListener(e -> showReservations.getUI().ifPresent(ui ->
                ui.navigate("travels")));

        reservationForm.setReservation(null);
    }
}
