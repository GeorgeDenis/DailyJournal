package com.example.application.views;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserRepository;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "main", layout = MenuLayout.class)
@RouteAlias(value = "", layout = MenuLayout.class)
public class MainView extends VerticalLayout {

    private final UserRepository userRepository;
    private Label label;


    public MainView(UserRepository userRepository) {
        this.userRepository = userRepository;
        User user = userRepository.findByIsLoggedTrue();

        if(user != null) {
            label = new Label("Welcome " + user.getUsername() + "!\nDailyJournal is a Java Spring application with a Vaadin frontend that allows users to keep track of various personal activities such as reading books, watching movies, and fitness entries.");
        }
        else {
            label = new Label("Welcome!\nDailyJournal is a Java Spring application with a Vaadin frontend that allows users to keep track of various personal activities such as reading books, watching movies, and fitness entries.");
        }

        add(label);
    }
}
