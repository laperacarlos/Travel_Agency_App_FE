package com.kodilla.travelagencyfe.views;

import com.kodilla.travelagencyfe.domain.ComplaintAnswer;
import com.kodilla.travelagencyfe.endpoint.BackendEndpoint;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "answers")
public class ComplaintAnswerView extends VerticalLayout {
    private BackendEndpoint backendEndpoint = new BackendEndpoint();
    private Grid<ComplaintAnswer> grid = new Grid<>(ComplaintAnswer.class);

    public ComplaintAnswerView() {

        grid.removeAllColumns();
        grid.addColumn("complaint.id").setHeader("Complaint Id");
        grid.addColumn( "answer");
        grid.setSizeFull();

        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();
        add(mainContent);
        setSizeFull();
        refresh();
    }

    public void refresh() {
        grid.setItems(backendEndpoint.getAllComplaintAnswers());
    }
}
