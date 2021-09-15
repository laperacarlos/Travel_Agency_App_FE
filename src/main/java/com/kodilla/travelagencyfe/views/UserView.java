package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.component.UserForm;
import com.kodilla.travelagencyfe.domain.User;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "users")
public class UserView extends VerticalLayout {
    private final BackendEndpoint backendEndpoint = new BackendEndpoint();
    private final Grid<User> grid = new Grid<>(User.class);
    private final UserForm form = new UserForm(this);
    private final Button addNewUser = new Button("Add new user");

    public UserView() {
        setComponents();
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        add(addNewUser, mainContent);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(backendEndpoint.getAllUsers());
    }

    private void setComponents() {
        grid.setColumns("username", "email", "creationDate", "isActive", "isAdministrator");
        grid.asSingleSelect().addValueChangeListener(e -> form.setUser(grid.asSingleSelect().getValue()));
        grid.setSizeFull();

        addNewUser.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setUser(new User());
        });

        form.setUser(null);
    }
}
