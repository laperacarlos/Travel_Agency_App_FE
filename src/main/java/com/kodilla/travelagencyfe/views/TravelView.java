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
    private BackendEndpoint backendEndpoint = new BackendEndpoint();
    private Grid<Travel> grid = new Grid<>(Travel.class);
    private TravelForm form = new TravelForm(this);
    private Button addNewTravel = new Button("Add new travel");

    public TravelView() {
        grid.setColumns("origin", "destination", "departureDate", "returnDate", "status", "creationDate");

        addNewTravel.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setTravel(new Travel());
        });

        grid.setSizeFull();

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        add(addNewTravel, mainContent);
        form.setTravel(null);
        setSizeFull();
        refresh();
        grid.asSingleSelect().addValueChangeListener(e -> form.setTravel(grid.asSingleSelect().getValue()));
    }

    public void refresh() {
        grid.setItems(backendEndpoint.getAllTravels());
    }
}
