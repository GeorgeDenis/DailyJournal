package com.example.application.views.books;


import com.example.application.data.entity.Book;
import com.example.application.data.entity.User;
import com.example.application.data.service.BookService;
import com.example.application.views.MenuLayout;
import com.example.application.views.login.LoginForm;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.Arrays;
import java.util.List;

@PageTitle("Books")
@Route(value = "books", layout = MenuLayout.class)
public class BooksView extends Div {
    private Grid<Book> grid;
    private final BookService bookService;

    public BooksView(BookService bookService) {
        this.bookService = bookService;

        setSizeFull();
        addClassNames("books-view");

        VerticalLayout layout = new VerticalLayout(createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        Button goToBookFormView = new Button("Add a Book");
        Button goToDeleteForm = new Button("Delete a Book");
        goToBookFormView.addClickListener(e -> goToBookFormView.getUI().ifPresent(ui ->
                ui.navigate("books-form")));
        goToDeleteForm.addClickListener(e -> goToDeleteForm.getUI().ifPresent(ui ->
                ui.navigate("books-delete")));
        layout.add(goToBookFormView,goToDeleteForm);

        add(layout);

    }

    private Component createGrid() {
        grid = new Grid<>(Book.class, false);
        grid.addColumn("id").setAutoWidth(true);
        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("author").setAutoWidth(true);
        grid.addColumn("price").setAutoWidth(true);
        grid.addColumn("finish").setAutoWidth(true);


        grid.setItems(Arrays.asList(bookService.getBooks()));
        List<Book> books = List.of(bookService.getBooks());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }
}
