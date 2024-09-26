package com.mycompany.managelibrary.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class BookXML {
    private List<Book> books;
    private static final String FILE_PATH = "book.xml";

    public BookXML() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void loadBooks() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(BookListWrapper.class);
                Unmarshaller um = context.createUnmarshaller();

                BookListWrapper wrapper = (BookListWrapper) um.unmarshal(file);
                this.books = wrapper.getBooks();
            } else {
                this.books = new ArrayList<>();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveBooks() {
        try {
            JAXBContext context = JAXBContext.newInstance(BookListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            BookListWrapper wrapper = new BookListWrapper();
            wrapper.setBooks(this.books);

            marshaller.marshal(wrapper, new File(FILE_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static class BookListWrapper {
        private List<Book> books;

        public List<Book> getBooks() {
            return books;
        }

        public void setBooks(List<Book> books) {
            this.books = books;
        }
    }
}
