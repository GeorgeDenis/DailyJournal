package com.example.application.views;


import com.example.application.components.appnav.AppNav;
import com.example.application.components.appnav.AppNavItem;
import com.example.application.data.entity.User;
import com.example.application.data.service.UserService;
import com.example.application.views.books.BooksView;
import com.example.application.views.login.LoginForm;
import com.example.application.views.logout.LogoutForm;
import com.example.application.views.movies.MoviesView;
import com.example.application.views.register.RegistrationForm;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MenuLayout extends AppLayout {

    private H2 viewTitle;
    private final UserService userService;


    public MenuLayout(UserService userService) {
        this.userService = userService;
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);


        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("DailyJournal");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();
        nav.addItem(new AppNavItem("Login", LoginForm.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new AppNavItem("Register", RegistrationForm.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new AppNavItem("Logout", LogoutForm.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new AppNavItem("Main", MainView.class, LineAwesomeIcon.REGISTERED.create()));
        nav.addItem(new AppNavItem("Books", BooksView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        nav.addItem(new AppNavItem("Movies", MoviesView.class, LineAwesomeIcon.BOOK_SOLID.create()));



        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
    private void logout() {
        User user = userService.findByIsLoggedTrue();

        if (user != null) {
            user.setLogged(false);
            userService.save(user);

        } else {
            Notification.show("No user is logged in");
        }
    }
}
