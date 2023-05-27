package com.example.application.views.books.personform;

import com.example.application.data.entity.User;
import com.example.application.data.service.MovieService;
import com.example.application.data.service.UserService;
import com.example.application.views.MenuLayout;
import com.example.application.views.books.BooksView;
import com.example.application.views.login.LoginForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@PageTitle("Movie delete")
@Route(value = "movie-delete", layout = MenuLayout.class)
@Uses(Icon.class)
public class MovieDeleteForm extends Div {

    private NumberField id = new NumberField("Movie id");
    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private final UserService userService;

    public MovieDeleteForm(MovieService movieService, UserService userService) {
        this.userService = userService;
        addClassName("deletemovie-form-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            User userLogged = userService.findByIsLoggedTrue();
            if(userLogged == null){
                Notification.show("You need to be authenticated!"); // returneaza un array gol
                UI.getCurrent().navigate(LoginForm.class);
            }else {
                Double idValue = id.getValue();
                if (idValue != null) {
                    int movieId = idValue.intValue();
                    ResponseEntity<String> response = movieService.deleteMovie(movieId);

                    if (response.getStatusCode() == HttpStatus.CREATED) {
                        Notification.show("Movie deleted successfully.");
                        UI.getCurrent().navigate(BooksView.class);
                    } else {
                        Notification.show(response.getBody());
                    }
                    clearForm();
                } else {
                    // Handle the case when idValue is null.
                }
            }
        });

    }

    private void clearForm() {
        id.clear();
    }

    private Component createTitle() {
        return new H3("Delete a movie");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(id);
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

