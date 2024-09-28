package com.mycompany.managelibrary.dao;

import com.mycompany.managelibrary.entity.Book;
import com.mycompany.managelibrary.entity.BookXML;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class BookDao {

    private static final String BOOK_XML_FILE = "src/main/resources/bookxml/books.xml";

    public void saveBooksToXml(List<Book> bookList) {
        try {
            JAXBContext context = JAXBContext.newInstance(BookXML.class);
            Marshaller marshaller = context.createMarshaller();

            // Định dạng đẹp cho file XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Tạo đối tượng BookList để chứa danh sách các sách
            BookXML books = new BookXML();
            books.setBooks(bookList);

            // Lưu vào file XML
            File file = new File(BOOK_XML_FILE);
            marshaller.marshal(books, file);

            System.out.println("Lưu thành công vào file XML: " + BOOK_XML_FILE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
