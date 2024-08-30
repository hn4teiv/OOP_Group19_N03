package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class NNCollection {
    private List<Integer> numbers;

    public NNCollection() {
        numbers = new ArrayList<>();
    }

    public void addNumber(int number) {
        numbers.add(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
