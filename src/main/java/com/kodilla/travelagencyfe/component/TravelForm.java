package com.kodilla.travelagencyfe.component;

import com.kodilla.travelagencyfe.domain.Status;
import com.kodilla.travelagencyfe.domain.Travel;
import com.kodilla.travelagencyfe.domain.TravelService;
import com.kodilla.travelagencyfe.views.MainView;
import com.kodilla.travelagencyfe.views.TravelView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class TravelForm extends FormLayout {
    private TextField origin = new TextField("Origin");
    private TextField destination = new TextField("Destination");
    private DatePicker departureDate= new DatePicker( "Departure date");
    private DatePicker returnDate= new DatePicker("Return date");
    private ComboBox<Status> status = new ComboBox<>("Status");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");
    private Binder<Travel> binder = new Binder<>(Travel.class);
    private TravelView travelView;
    private TravelService service = TravelService.getInstance();

    public TravelForm(TravelView travelView) {
        status.setItems(Status.values());
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(origin, destination, departureDate, returnDate, status, buttons);
        binder.bindInstanceFields(this);
        this.travelView = travelView;
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
    }

    private void save() {
        Travel travel = binder.getBean();
        service.save(travel);
        travelView.refresh();
        setTravel(null);
    }

    private void delete() {
        Travel travel = binder.getBean();
        service.delete(travel);
        travelView.refresh();
        setTravel(null);
    }

    public void setTravel(Travel travel) {
        binder.setBean(travel);

        if (travel== null) {
            setVisible(false);
        } else {
            setVisible(true);
            origin.focus();
        }
    }
}
