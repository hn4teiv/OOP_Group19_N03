package com.mycompany.app;

public class NameNumber {
    private String name;
    private int number;

    public NameNumber(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "NameNumber[name=" + name + ", number=" + number + "]";
    }
}
