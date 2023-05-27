package com.example.application.views.fitness;

import com.example.application.data.entity.Fitness;
import com.example.application.data.service.FitnessService;
import com.example.application.views.MenuLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.Arrays;

@PageTitle("Fitness")
@Route(value = "fitness", layout = MenuLayout.class)
public class FitnessView extends Div {
    private Grid<Fitness> grid;
    private final FitnessService fitnessService;

    public FitnessView(FitnessService fitnessService) {
        this.fitnessService = fitnessService;
        setSizeFull();
        addClassNames("fitness-view");

        VerticalLayout layout = new VerticalLayout(createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        Button goToFitnessFormView = new Button("Add a fitness entry");
        goToFitnessFormView.addClickListener(e -> goToFitnessFormView.getUI().ifPresent(ui ->
                ui.navigate("fitness-form")));

        layout.add(goToFitnessFormView);

        add(layout);
    }

    private Component createGrid() {
        grid = new Grid<>(Fitness.class, false);
        grid.addColumn("gym").setAutoWidth(true);
        grid.addColumn("water").setAutoWidth(true);
        grid.addColumn("sleep").setAutoWidth(true);
        grid.addColumn("calories").setAutoWidth(true);
        grid.addColumn("date").setAutoWidth(true);



        grid.setItems(Arrays.asList(fitnessService.getFitness()));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }
}
