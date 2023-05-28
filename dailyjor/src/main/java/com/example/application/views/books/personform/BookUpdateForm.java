package com.example.application.views.books.personform;

import com.example.application.data.entity.Book;
import com.example.application.data.entity.User;
import com.example.application.data.service.BookService;
import com.example.application.data.service.UserService;
import com.example.application.views.MenuLayout;
import com.example.application.views.login.LoginForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@PageTitle("Book update")
@Route(value = "books-update", layout = MenuLayout.class)
@Uses(Icon.class)
public class BookUpdateForm extends Div {
    private IntegerField id = new IntegerField("Book Id");
    private TextField name = new TextField("Book title");
    private TextField author = new TextField("Book author");
    private NumberField price = new NumberField("Book price");
    private DatePicker finish = new DatePicker("Finished book on:");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<Book> binder = new Binder<>(Book.class);
    private final UserService userService;

    public BookUpdateForm(BookService bookService, UserService userService) {
        this.userService = userService;
        addClassName("person-form-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);

        binder.forField(finish)
                .bind(Book::getFinish, Book::setFinish);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            User userLogged = userService.findByIsLoggedTrue();
            if(userLogged == null){
                Notification.show("You need to be authenticated!"); // returneaza un array gol
                UI.getCurrent().navigate(LoginForm.class);
            }else {
                ResponseEntity<String> response = bookService.updateBook(binder.getBean());

                if (response.getStatusCode() == HttpStatus.CREATED) {
                    Notification.show("Book updated successfully.");
                    UI.getCurrent().navigate("books");
                } else {
                    Notification.show(response.getBody());
                }
                clearForm();
            }
        });

    }

    private void clearForm() {
        binder.setBean(new Book());
    }

    private Component createTitle() {
        return new H3("Personal information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(id,name, author,  price, finish);
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
