package com.example.application.views.fitness.fitnessForm;

import com.example.application.data.entity.Fitness;
import com.example.application.data.entity.User;
import com.example.application.data.service.FitnessService;
import com.example.application.data.service.UserService;
import com.example.application.views.MenuLayout;
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
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@PageTitle("Fitness Form")
@Route(value = "fitness-form", layout = MenuLayout.class)
@Uses(Icon.class)
public class FitnessFormView extends Div {
    private Select<String> gym = new Select<>();
    private NumberField water = new NumberField("Water quantity");
    private NumberField sleep = new NumberField("Hours of sleep");
    private NumberField calories = new NumberField("Calories consumed");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");
    private Binder<Fitness> binder = new Binder<>(Fitness.class);
    private final UserService userService;
    public FitnessFormView(FitnessService fitnessService, UserService userService) {
        this.userService = userService;
        gym.setLabel("Gym");
        gym.setItems("Yes", "No");
        water.setMin(1);
        sleep.setMin(1);
        calories.setMin(1);
        addClassName("fitness-form-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);


        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {

            User userLogged = this.userService.findByIsLoggedTrue();
            if(userLogged == null){
                Notification.show("You need to be authenticated!"); // returneaza un array gol
                UI.getCurrent().navigate(LoginForm.class);
            }else {
                ResponseEntity<String> response = fitnessService.saveFitness(binder.getBean());

                if (response.getStatusCode() == HttpStatus.CREATED) {
                    Notification.show("Fitness entry saved successfully.");
                    UI.getCurrent().navigate("fitness");
                } else {
                    Notification.show(response.getBody());
                }
                clearForm();
            }
        });

    }
    private void clearForm() {
        binder.setBean(new Fitness());
    }

    private Component createTitle() {
        return new H3("Fitness information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(gym,water,sleep,calories);
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
