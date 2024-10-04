package com.mycompany.managelibrary.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Books")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookXML {
    @XmlElement(name = "Book")
    private List<Book> books = null;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
