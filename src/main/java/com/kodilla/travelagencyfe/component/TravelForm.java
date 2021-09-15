package com.kodilla.travelagencyfe.component;

import com.kodilla.travelagencyfe.domain.Status;
import com.kodilla.travelagencyfe.domain.Travel;
import com.kodilla.travelagencyfe.service.TravelService;
import com.kodilla.travelagencyfe.views.TravelView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class TravelForm extends FormLayout {
    private final TextField origin = new TextField("Origin");
    private final TextField destination = new TextField("Destination");
    private final DatePicker departureDate= new DatePicker( "Departure date");
    private final DatePicker returnDate= new DatePicker("Return date");
    private final ComboBox<Status> status = new ComboBox<>("Status");
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");
    private final Binder<Travel> binder = new Binder<>(Travel.class);
    private final TravelView travelView;
    private final TravelService travelService = TravelService.getInstance();

    public TravelForm(TravelView travelView) {
        setComponents();
        HorizontalLayout buttons = new HorizontalLayout(save, cancel);
        add(origin, destination, departureDate, returnDate, status, buttons);
        binder.bindInstanceFields(this);
        this.travelView = travelView;
    }

    private void setComponents() {
        status.setItems(Status.values());

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(e -> save());
        cancel.addClickListener(e -> cancel());
    }

    private void save() {
        Travel travel = binder.getBean();
        if(travel.getId() == null) {
            travelService.createTravel(travel);
        } else travelService.save(travel);
        travelView.refresh();
        setTravel(null);
    }

    private void cancel() {
        travelView.refresh();
        setTravel(null);
    }

    public void setTravel(Travel travel) {
        binder.setBean(travel);

        if (travel==null) {
            setVisible(false);
        } else {
            setVisible(true);
            origin.focus();
        }
    }
}
