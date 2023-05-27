package com.example.application.views.register;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserRepository;
import com.example.application.data.service.UserService;
import com.example.application.views.MainView;
import com.example.application.views.login.LoginForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.BeanValidator;
import com.vaadin.flow.router.Route;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Route("register")
public class RegistrationForm extends VerticalLayout {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;


    // Injectam userRepository si passwordEncoder prin constructor
    public RegistrationForm(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

        Binder<User> binder = new Binder<>(User.class);
        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");

        binder.forField(username).withValidator(new BeanValidator(User.class, "username")).bind(User::getUsername, User::setUsername);
        binder.forField(password).withValidator(new BeanValidator(User.class, "password")).bind(User::getPassword, User::setPassword);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        Button registerButton = new Button("Register");
        Button loginButton = new Button("Login");
        Button homeButton = new Button("Home");

        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        homeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.addClickListener(event -> {
            User user = new User();
            binder.writeBeanIfValid(user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setLogged(false);
            userService.save(user);
            Notification.show("Registration successful!");
            UI.getCurrent().navigate(LoginForm.class);

        });
        loginButton.addClickListener(event -> {
            UI.getCurrent().navigate(RegistrationForm.class);
        });
        homeButton.addClickListener(event -> {
            UI.getCurrent().navigate(MainView.class);
        });
        add(username, password, registerButton,loginButton,homeButton);
    }
}


