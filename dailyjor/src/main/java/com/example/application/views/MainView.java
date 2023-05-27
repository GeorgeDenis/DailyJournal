package com.example.application.views;

import com.example.application.data.service.UserRepository;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "main", layout = MenuLayout.class)
@RouteAlias(value = "", layout = MenuLayout.class)


public class MainView extends VerticalLayout {

    private final UserRepository userRepository;

    public MainView(UserRepository userRepository) {
        this.userRepository = userRepository;

        Label label = new Label("Welcome,   !");
        add(label);
    }

}

