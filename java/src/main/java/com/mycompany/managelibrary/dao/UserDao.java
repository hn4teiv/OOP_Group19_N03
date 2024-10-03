package com.mycompany.managelibrary.dao;

import com.mycompany.managelibrary.entity.User;
import javafx.application.Platform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String XML_FILE_PATH = "users.xml";

    public boolean registerUser(User user) {
        if (checkUserExists(user.getUsername())) {
            return false; // Tên đăng nhập đã tồn tại
        }
        saveUserToXml(user);
        return true; // Đăng ký thành công
    }

    private boolean checkUserExists(String username) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(XML_FILE_PATH));
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            List<Element> users = getChildElements(root, "user");

            for (Element userElement : users) {
                String existingUsername = userElement.getElementsByTagName("username").item(0).getTextContent();
                if (existingUsername.equals(username)) {
                    return true; // Tên đăng nhập đã tồn tại
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Tên đăng nhập chưa tồn tại
    }

    private void saveUserToXml(User user) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document;

            File xmlFile = new File(XML_FILE_PATH);
            if (xmlFile.exists()) {
                document = builder.parse(xmlFile);
            } else {
                document = builder.newDocument();
                Element root = document.createElement("users");
                document.appendChild(root);
            }

            Element root = document.getDocumentElement();

            // Tạo phần tử user
            Element userElement = document.createElement("user");
            Element usernameElement = document.createElement("username");
            usernameElement.setTextContent(user.getUsername());
            Element passwordElement = document.createElement("password");
            passwordElement.setTextContent(user.getPassword());

            userElement.appendChild(usernameElement);
            userElement.appendChild(passwordElement);
            root.appendChild(userElement);

            // Ghi lại file XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Element> getChildElements(Element parent, String tagName) {
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < parent.getElementsByTagName(tagName).getLength(); i++) {
            elements.add((Element) parent.getElementsByTagName(tagName).item(i));
        }
        return elements;
    }

    public boolean checkUser(User user) {
        // Kiểm tra thông tin đăng nhập
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(XML_FILE_PATH));
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            List<Element> users = getChildElements(root, "user");

            for (Element userElement : users) {
                String existingUsername = userElement.getElementsByTagName("username").item(0).getTextContent();
                String existingPassword = userElement.getElementsByTagName("password").item(0).getTextContent();
                if (existingUsername.equals(user.getUsername()) && existingPassword.equals(user.getPassword())) {
                    return true; // Đăng nhập thành công
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Đăng nhập thất bại
    }
}
