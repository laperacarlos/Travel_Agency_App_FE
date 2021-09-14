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
    private TextField username = new TextField("Username");
    private TextField email = new TextField("E-mail");
    private ComboBox<UserStatus> isActive = new ComboBox<>("Active");
    private ComboBox<UserStatus> isAdministrator = new ComboBox<>("Administrator");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");
    private Binder<User> binder = new Binder<>(User.class);
    private UserView userView;
    private final BackendEndpoint backendEndpoint = new BackendEndpoint();

    public UserForm(UserView userView) {
        isActive.setItems(UserStatus.values());
        isAdministrator.setItems(UserStatus.values());
        HorizontalLayout buttons = new HorizontalLayout(save, cancel);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(username, email, isActive, isAdministrator, buttons);
        binder.bindInstanceFields(this);
        this.userView = userView;
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
