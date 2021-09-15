package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.component.ReservationForm;
import com.kodilla.travelagencyfe.domain.Reservation;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "reservations")
public class ReservationView extends VerticalLayout {
    private final BackendEndpoint backendEndpoint = new BackendEndpoint();
    private final Grid<Reservation> grid = new Grid<>(Reservation.class);
    private final ReservationForm reservationForm = new ReservationForm(this);

    public ReservationView() {
        setComponents();
        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();
        add(mainContent, reservationForm);
        setSizeFull();
        refresh();
    }

    private void setComponents() {
        grid.removeAllColumns();
        grid.addColumn("user.username");
        grid.addColumn("travel.origin").setHeader("Travel origin");
        grid.addColumn("travel.destination").setHeader("Travel destination");
        grid.addColumn("travel.departureDate");
        grid.addColumns("travelType", "hotelStandard", "mealStandard", "status");
        grid.setSizeFull();
        grid.asSingleSelect().addValueChangeListener(e -> reservationForm.setReservation(grid.asSingleSelect().getValue()));
        reservationForm.setReservation(null);
    }

    public void refresh() {
        grid.setItems(backendEndpoint.getAllReservations());
    }
}
