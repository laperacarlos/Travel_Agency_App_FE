package com.kodilla.travelagencyfe.component;

import com.kodilla.travelagencyfe.domain.*;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;
import com.kodilla.travelagencyfe.views.MainView;
import com.kodilla.travelagencyfe.views.ReservationView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;

public class ReservationForm extends FormLayout {
    private final ComboBox<TravelType> travelType = new ComboBox<>("Type of travel");
    private final ComboBox<HotelStandard> hotelStandard = new ComboBox<>("Hotel standard (stars)");
    private final ComboBox<MealStandard> mealStandard = new ComboBox<>("Meal standard");
    private final ComboBox<Status> status = new ComboBox<>("Status");
    private final Button travelFrom = new Button("travel from");
    private final Button travelTo= new Button("TravelTo");
    private final Button save = new Button("Save");
    private final Button cancel = new Button("Cancel");
    private final Binder<Reservation> binder = new Binder<>(Reservation.class);
    private MainView mainView;
    private ReservationView reservationView;
    private Travel travel;
    private final BackendEndpoint backendEndpoint = new BackendEndpoint();

    public ReservationForm(MainView mainView) {
        setComponents();
        HorizontalLayout topButtons = new HorizontalLayout(travelFrom, travelTo);
        HorizontalLayout bottomButtons = new HorizontalLayout(save, cancel);

        add(topButtons, travelType, hotelStandard, mealStandard, status, bottomButtons);
        binder.bindInstanceFields(this);
        this.mainView = mainView;
    }

    public ReservationForm(ReservationView reservationView) {
        setComponents();
        HorizontalLayout bottomButtons = new HorizontalLayout(save, cancel);

        add(travelType, hotelStandard, mealStandard, status, bottomButtons);
        binder.bindInstanceFields(this);
        this.reservationView = reservationView;
    }

    private void save() {
        Reservation reservation = binder.getBean();
        if(reservation.getId() == null) {
            //implementation of setting user only to check working scenario,
            //later on this problem will be solved by login mechanism
            reservation.setUser(backendEndpoint.getAllUsers().get(0));
            reservation.setTravel(travel);
            backendEndpoint.createReservation(reservation);
            mainView.refresh();
        } else {
            backendEndpoint.updateReservation(reservation);
            reservationView.refresh();
        }
        setReservation(null);
    }

    private void cancel() {
        if(binder.getBean().getId() != null) {
            reservationView.refresh();
        }
        setReservation(null);
    }

    public void setReservation(Reservation reservation) {

        binder.setBean(reservation);
        setVisible(reservation != null);
    }

    public void setTravelToReservation(Travel travel) {
        travelFrom.setText(travel.getOrigin());
        travelTo.setText(travel.getDestination());
        this.travel = travel;
    }

    private void setComponents() {
        travelFrom.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        travelTo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        travelType.setItems(TravelType.values());
        hotelStandard.setItems(HotelStandard.values());
        mealStandard.setItems(MealStandard.values());
        status.setItems(Status.values());

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(e -> save());
        cancel.addClickListener(e -> cancel());
    }
}
