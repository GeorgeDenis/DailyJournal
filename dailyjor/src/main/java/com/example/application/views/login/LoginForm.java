package com.example.application.views.login;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.views.MainView;
import com.example.application.views.books.BooksView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Route("login")

public class LoginForm extends VerticalLayout {
    private final BCryptPasswordEncoder passwordEncoder;
    private TextField username;
    private PasswordField password;
    private Button loginButton;
    private UserService userService;

    public LoginForm(BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        username = new TextField("Username");
        password = new PasswordField("Password");
        loginButton = new Button("Login");

        loginButton.addClickListener(event -> {
            String inputUsername = username.getValue();
            String inputPassword = password.getValue();

            User user = userService.findByUsername(inputUsername);
            if (authenticate(username.getValue(), password.getValue())) {
                user.setLogged(true); // Setează isLogged pe true în baza de date
                userService.save(user);

                UI.getCurrent().navigate(MainView.class);
            } else {
                Notification.show("Invalid credentials");
            }
        });

        add(username, password, loginButton);
    }
    private boolean authenticate(String username, String password) {
        User user = userService.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}


