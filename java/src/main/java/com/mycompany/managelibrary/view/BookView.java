package com.mycompany.managelibrary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BookView {
    private TableView<Book> table;
    private TextField idField;
    private TextField nameField;
    private TextField priceField;
    private TextField quantityField;
    private TextField publisherField;
    private TextField authorField;
    private Button addButton;
    private Button editButton;
    private Button deleteButton;

    private ObservableList<Book> bookList;

    public BookView(Stage primaryStage) {
        primaryStage.setTitle("Book Management");

        bookList = FXCollections.observableArrayList();

        table = new TableView<>();
        idField = new TextField();
        nameField = new TextField();
        priceField = new TextField();
        quantityField = new TextField();
        publisherField = new TextField();
        authorField = new TextField();
        addButton = new Button("Add Book");
        editButton = new Button("Edit Book");
        deleteButton = new Button("Delete Book");

        TableColumn<Book, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());

        TableColumn<Book, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        TableColumn<Book, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        TableColumn<Book, String> publisherColumn = new TableColumn<>("Publisher");
        publisherColumn.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        table.getColumns().addAll(idColumn, nameColumn, priceColumn, quantityColumn, publisherColumn, authorColumn);

        table.setItems(bookList);

        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setVgap(8);
        form.setHgap(10);

        form.add(new Label("ID:"), 0, 0);
        form.add(idField, 1, 0);
        form.add(new Label("Name:"), 0, 1);
        form.add(nameField, 1, 1);
        form.add(new Label("Price:"), 0, 2);
        form.add(priceField, 1, 2);
        form.add(new Label("Quantity:"), 0, 3);
        form.add(quantityField, 1, 3);
        form.add(new Label("Publisher:"), 0, 4);
        form.add(publisherField, 1, 4);
        form.add(new Label("Author:"), 0, 5);
        form.add(authorField, 1, 5);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton);

        BorderPane layout = new BorderPane();
        layout.setCenter(table);
        layout.setBottom(form);
        layout.setRight(buttonBox);

        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        addButton.setOnAction(e -> addBook());
        editButton.setOnAction(e -> editBook());
        deleteButton.setOnAction(e -> deleteBook());
    }

    private void addBook() {
        Book book = new Book(
                Integer.parseInt(idField.getText()),
                nameField.getText(),
                Double.parseDouble(priceField.getText()),
                Integer.parseInt(quantityField.getText()),
                publisherField.getText(),
                authorField.getText()
        );
        bookList.add(book);
        clearForm();
    }

    private void editBook() {
        Book selectedBook = table.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            selectedBook.setId(Integer.parseInt(idField.getText()));
            selectedBook.setName(nameField.getText());
            selectedBook.setPrice(Double.parseDouble(priceField.getText()));
            selectedBook.setQuantity(Integer.parseInt(quantityField.getText()));
            selectedBook.setPublisher(publisherField.getText());
            selectedBook.setAuthor(authorField.getText());
            table.refresh();
            clearForm();
        }
    }

    private void deleteBook() {
        Book selectedBook = table.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            bookList.remove(selectedBook);
            clearForm();
        }
    }

    private void clearForm() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        quantityField.clear();
        publisherField.clear();
        authorField.clear();
    }

    public void setBookForm(Book book) {
        idField.setText(String.valueOf(book.getId()));
        nameField.setText(book.getName());
        priceField.setText(String.valueOf(book.getPrice()));
        quantityField.setText(String.valueOf(book.getQuantity()));
        publisherField.setText(book.getPublisher());
        authorField.setText(book.getAuthor());
    }
}
