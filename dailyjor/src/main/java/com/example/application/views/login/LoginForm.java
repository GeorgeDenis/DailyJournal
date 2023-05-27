package com.example.application.views.login;

import com.example.application.data.entity.Book;
import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.views.MainView;
import com.example.application.views.books.BooksView;
import com.example.application.views.register.RegistrationForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
    private Button registerButton;
    private Button homeButton;
    private UserService userService;

    public LoginForm(BCryptPasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        username = new TextField("Username");
        password = new PasswordField("Password");
        loginButton = new Button("Login");
        registerButton = new Button("Register");
        homeButton = new Button("Home");

        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        homeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        loginButton.addClickListener(event -> {
            String inputUsername = username.getValue();
            String inputPassword = password.getValue();
            User userLogged = userService.findByIsLoggedTrue();
            if(userLogged != null){
                Notification.show("Already logged!"); // returneaza un array gol
                UI.getCurrent().navigate(MainView.class);
            }else {
                User user = userService.findByUsername(inputUsername);
                if (authenticate(username.getValue(), password.getValue())) {
                    user.setLogged(true); // Setează isLogged pe true în baza de date
                    userService.save(user);

                    UI.getCurrent().navigate(MainView.class);
                } else {
                    Notification.show("Invalid credentials");
                }
            }

        });
        registerButton.addClickListener(event -> {
            UI.getCurrent().navigate(RegistrationForm.class);
        });
        homeButton.addClickListener(event -> {
            UI.getCurrent().navigate(MainView.class);
        });

        add(username, password, loginButton,registerButton,homeButton);
    }
    private boolean authenticate(String username, String password) {
        User user = userService.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}


