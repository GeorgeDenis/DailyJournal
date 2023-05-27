package com.example.application.views.logout;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.views.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "logout")
public class LogoutForm extends VerticalLayout {
    private UserService userService;
    private Button logoutButton;

    @Autowired
    public LogoutForm(UserService userService) {
        this.userService = userService;
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        logoutButton = new Button("Logout");
        logoutButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        logoutButton.addClickListener(event ->{
            User user = userService.findByIsLoggedTrue();

            if (user != null) {
                user.setLogged(false);
                userService.save(user);

                UI.getCurrent().navigate(MainView.class);
            } else {
                Notification.show("No user is logged in");
                UI.getCurrent().navigate(MainView.class);
            }
        });
        add(logoutButton);
    }

    private void logout() {
        User user = userService.findByIsLoggedTrue();

        if (user != null) {
            user.setLogged(false);
            userService.save(user);

            UI.getCurrent().navigate(MainView.class);
        } else {
            Notification.show("No user is logged in");
            UI.getCurrent().navigate(MainView.class);
        }
    }
}
