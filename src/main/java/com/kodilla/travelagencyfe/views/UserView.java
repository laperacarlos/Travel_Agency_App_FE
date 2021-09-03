package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.domain.User;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "users")
public class UserView extends VerticalLayout {
    private BackendEndpoint backendEndpoint = new BackendEndpoint();
    private Grid<User> grid = new Grid<>(User.class);

    public UserView() {
        grid.setColumns("username", "email", "creationDate", "active", "administrator");

        grid.setSizeFull();

        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();
        add(mainContent);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(backendEndpoint.getAllUsers());
    }
}
