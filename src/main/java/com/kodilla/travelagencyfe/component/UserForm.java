package com.kodilla.travelagencyfe.component;

import com.kodilla.travelagencyfe.domain.User;
import com.kodilla.travelagencyfe.domain.UserStatus;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;
import com.kodilla.travelagencyfe.views.UserView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;


public class UserForm extends FormLayout {
    private final TextField username = new TextField("Username");
    private final TextField email = new TextField("E-mail");
    private final ComboBox<UserStatus> isActive = new ComboBox<>("Active");
    private final ComboBox<UserStatus> isAdministrator = new ComboBox<>("Administrator");
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");
    private final Binder<User> binder = new Binder<>(User.class);
    private final UserView userView;
    private final BackendEndpoint backendEndpoint = new BackendEndpoint();

    public UserForm(UserView userView) {
        setComponents();
        HorizontalLayout buttons = new HorizontalLayout(save, cancel);
        add(username, email, isActive, isAdministrator, buttons);
        binder.bindInstanceFields(this);
        this.userView = userView;
    }

    private void setComponents() {
        isActive.setItems(UserStatus.values());
        isAdministrator.setItems(UserStatus.values());

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(e -> save());
        cancel.addClickListener(e -> cancel());
    }

    private void save() {
        User user = binder.getBean();
        if(user.getId() == null) {
            backendEndpoint.createUser(user);
        } else backendEndpoint.updateUser(user);
        userView.refresh();
        setUser(null);
    }

    private void cancel() {
        userView.refresh();
        setUser(null);
    }

    public void setUser(User user) {
        binder.setBean(user);

        if (user==null) {
            setVisible(false);
        } else {
            setVisible(true);
            username.focus();
        }
    }
}
