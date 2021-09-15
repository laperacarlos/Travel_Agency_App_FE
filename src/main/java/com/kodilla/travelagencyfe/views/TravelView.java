package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.component.TravelForm;
import com.kodilla.travelagencyfe.domain.Travel;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "travels")
public class TravelView extends VerticalLayout {
    private final BackendEndpoint backendEndpoint = new BackendEndpoint();
    private final Grid<Travel> grid = new Grid<>(Travel.class);
    private final TravelForm form = new TravelForm(this);
    private final Button addNewTravel = new Button("Add new travel");

    public TravelView() {
        setComponents();
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        add(addNewTravel, mainContent);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(backendEndpoint.getAllTravels());
    }

    private void setComponents() {
        grid.setColumns("origin", "destination", "departureDate", "returnDate", "status", "creationDate");
        grid.asSingleSelect().addValueChangeListener(e -> form.setTravel(grid.asSingleSelect().getValue()));
        grid.setSizeFull();

        addNewTravel.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setTravel(new Travel());
        });

        form.setTravel(null);
    }
}
