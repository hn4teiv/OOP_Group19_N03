package com.mycompany.managelibrary.entity;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username; // Phương thức getter cho username
    }

    public String getPassword() {
        return password; // Phương thức getter cho password
    }
}
