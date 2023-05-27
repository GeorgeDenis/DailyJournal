package com.example.application.views.movies;


import com.example.application.data.entity.Movie;
import com.example.application.data.service.MovieService;
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

@PageTitle("Movies")
@Route(value = "movies", layout = MenuLayout.class)
public class MoviesView extends Div {
    private Grid<Movie> grid;
    private final MovieService movieService;

    public MoviesView(MovieService movieService) {
        this.movieService = movieService;
        setSizeFull();
        addClassNames("movies-view");

        VerticalLayout layout = new VerticalLayout(createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        Button goToMovieFormView = new Button("Add a Movie");
        goToMovieFormView.addClickListener(e -> goToMovieFormView.getUI().ifPresent(ui ->
                ui.navigate("movies-form")));

        layout.add(goToMovieFormView);

        add(layout);
    }

    private Component createGrid() {
        grid = new Grid<>(Movie.class, false);
        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("rating").setAutoWidth(true);
        grid.addColumn("finish").setAutoWidth(true);



        grid.setItems(Arrays.asList(movieService.getMovies()));
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }
}
