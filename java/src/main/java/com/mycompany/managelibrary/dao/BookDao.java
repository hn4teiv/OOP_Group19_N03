package com.mycompany.managelibrary.dao;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.managelibrary.entity.Book;

public class BookDao {
    private static final String FILE_PATH = "book.xml";
    private List<Book> books = new ArrayList<>();

    public List<Book> getListBook() {
        return books;
    }

    public void add(Book book) {
        books.add(book);
    }

    public void edit(Book book) {
    }

    public void delete(Book book) {
        books.remove(book);
    }
}
