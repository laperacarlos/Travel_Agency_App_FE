package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.domain.Reservation;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "reservations")
public class ReservationView extends VerticalLayout {
    private BackendEndpoint backendEndpoint = new BackendEndpoint();
    private Grid<Reservation> grid = new Grid<>(Reservation.class);

    public ReservationView() {

        grid.removeAllColumns();
        grid.addColumn("user.username");
        grid.addColumn("travel.origin").setHeader("Travel origin");
        grid.addColumn("travel.destination").setHeader("Travel destination");
        grid.addColumn("travel.departureDate");
        grid.addColumns("travelType", "hotelStandard", "mealStandard", "status");

        grid.setSizeFull();

        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();
        add(mainContent);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(backendEndpoint.getAllReservations());
    }

}
