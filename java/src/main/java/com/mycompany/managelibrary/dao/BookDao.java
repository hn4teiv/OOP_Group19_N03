package com.mycompany.managelibrary.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mycompany.managelibrary.entity.Book;
import com.mycompany.managelibrary.entity.BookXML;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookDao {
    private static final String FILE_PATH = "data.xml";

    public ObservableList<Book> getListBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        try {
            File xmlFile = new File(FILE_PATH);
            if (!xmlFile.exists()) {
                createXmlFile(); // Tạo file nếu nó không tồn tại
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(BookXML.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            BookXML bookList = (BookXML) jaxbUnmarshaller.unmarshal(xmlFile);
            if (bookList != null && bookList.getBooks() != null) {
                books.addAll(bookList.getBooks());
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra thông báo lỗi
        }
        return books;
    }

    private void createXmlFile() {
        try {
            File xmlFile = new File(FILE_PATH);
            xmlFile.createNewFile(); // Tạo file mới
            FileWriter writer = new FileWriter(xmlFile);
            writer.write("<BookXML>\n<books></books>\n</BookXML>"); // Cấu trúc XML mặc định
            writer.close();
        } catch (Exception e) {
            e.printStackTrace(); // In ra thông báo lỗi nếu có
        }
    }

    public void add(Book book) {
        ObservableList<Book> books = getListBooks();
        int nextId = generateNextId(books); // Generate the next available ID
        book.setId(nextId);
        books.add(book);
        writeListToXml(books);
    }

    public void edit(Book book) {
        ObservableList<Book> books = getListBooks();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == book.getId()) {
                books.set(i, book);
                writeListToXml(books);
                return;
            }
        }
    }

    public boolean delete(int id) {
        ObservableList<Book> books = getListBooks();
        boolean removed = books.removeIf(book -> book.getId() == id);

        if (removed) {
            // Update IDs after deletion
            for (int i = 0; i < books.size(); i++) {
                books.get(i).setId(i + 1); // IDs start from 1
            }
            writeListToXml(books);
        }

        return removed;
    }

    private void writeListToXml(ObservableList<Book> books) {
        try {
            File xmlFile = new File(FILE_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(BookXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            BookXML bookList = new BookXML();
            bookList.setBooks(new ArrayList<>(books)); // Convert ObservableList to ArrayList

            jaxbMarshaller.marshal(bookList, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int generateNextId(ObservableList<Book> books) {
        int maxId = 0;
        for (Book book : books) {
            if (book.getId() > maxId) {
                maxId = book.getId();
            }
        }
        return maxId + 1; // Return the next available ID
    }
}
