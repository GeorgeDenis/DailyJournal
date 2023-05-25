package com.example.application.views.logout;

import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.views.MenuLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "logout", layout = MenuLayout.class)
public class LogoutView extends VerticalLayout {
    private UserService userService;

    public LogoutView(UserService userService) {
        this.userService = userService;

        logout();
    }

    private void logout() {
        User user = userService.findByIsLoggedTrue();
        if (user != null) {
            user.setLogged(false);
            userService.save(user);
            UI.getCurrent().navigate(LoginForm.class);
        } else {
            Notification.show("No user is logged in");
        }
    }
}

