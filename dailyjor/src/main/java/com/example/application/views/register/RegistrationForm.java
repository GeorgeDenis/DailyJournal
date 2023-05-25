package com.example.application.views.register;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserRepository;
import com.example.application.data.service.UserService;
import com.example.application.views.login.LoginForm;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
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

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    // Injectam userRepository si passwordEncoder prin constructor
    public RegistrationForm(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

        Binder<User> binder = new Binder<>(User.class);
        TextField username = new TextField("Username");
        PasswordField password = new PasswordField("Password");

        binder.forField(username).withValidator(new BeanValidator(User.class, "username")).bind(User::getUsername, User::setUsername);
        binder.forField(password).withValidator(new BeanValidator(User.class, "password")).bind(User::getPassword, User::setPassword);

        Button registerButton = new Button("Register", event -> {
            User user = new User();
            binder.writeBeanIfValid(user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setLogged(false);
            userRepository.save(user);
            Notification.show("Registration successful!");
            UI.getCurrent().navigate(LoginForm.class);

        });

        add(username, password, registerButton);
    }
}


