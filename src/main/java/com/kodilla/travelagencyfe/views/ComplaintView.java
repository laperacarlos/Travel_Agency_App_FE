package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.domain.Complaint;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "complaints")
public class ComplaintView extends VerticalLayout {
    private final BackendEndpoint backendEndpoint = new BackendEndpoint();
    private final Grid<Complaint> grid = new Grid<>(Complaint.class);

    public ComplaintView() {

        grid.removeAllColumns();
        grid.addColumns("reservation.id", "description");
        grid.addColumn("reservation.user.username");
        grid.addColumn("reservation.travel.origin");
        grid.addColumn("reservation.travel.destination");
        grid.setSizeFull();

        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();
        add(mainContent);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(backendEndpoint.getAllComplaints());
    }
}
