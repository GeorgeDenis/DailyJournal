package com.example.application.views.movieForm;

import com.example.application.data.entity.Movie;
import com.example.application.data.entity.User;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.UserService;
import com.example.application.views.MenuLayout;
import com.example.application.views.login.LoginForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.DoubleRangeValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@PageTitle("Movie Form")
@Route(value = "movies-form", layout = MenuLayout.class)
@Uses(Icon.class)
public class MoviesFormView extends Div {
    private TextField name = new TextField("Movie title");
    private NumberField rating = new NumberField("Movie rating");
    private DatePicker finish = new DatePicker("Finished movie on:");
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private Binder<Movie> binder = new Binder<>(Movie.class);
    private final UserService userService;
    public MoviesFormView(MovieService movieService, UserService userService) {
        this.userService = userService;
        addClassName("movie-form-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        binder.forField(finish)
                .bind(Movie::getFinish, Movie::setFinish);
        binder.forField(rating)
                .withValidator(new DoubleRangeValidator(
                        "Please enter a number between 1 and 10", 1.0, 10.0))
                        .bind(Movie::getRating, Movie::setRating);

        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {

            User userLogged = this.userService.findByIsLoggedTrue();
            if(userLogged == null){
                Notification.show("You need to be authenticated!"); // returneaza un array gol
                UI.getCurrent().navigate(LoginForm.class);
            }else {
                ResponseEntity<String> response = movieService.saveMovie(binder.getBean());

                if (response.getStatusCode() == HttpStatus.CREATED) {
                    Notification.show("Movie saved successfully.");
                    UI.getCurrent().navigate("movies");
                } else {
                    Notification.show(response.getBody());
                }
                clearForm();
            }
        });

    }
    private void clearForm() {
        binder.setBean(new Movie());
    }

    private Component createTitle() {
        return new H3("Movie information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(name,rating,finish);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }
}
