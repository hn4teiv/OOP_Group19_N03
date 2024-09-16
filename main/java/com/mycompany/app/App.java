package com.mycompany.app;

public class App {
    public static void main(String[] args) {
        // Create and use Book
        Book book = new Book("To Kill a Mockingbird", "Harper Lee");
        System.out.println(book);

        // Create and use Time
        Time time = new Time(14, 30);
        System.out.println("Current time: " + time);

        // Use Recursion
        int factorialOf5 = Recursion.factorial(5);
        System.out.println("Factorial of 5: " + factorialOf5);

        // Create and use NNCollection
        NNCollection collection = new NNCollection();
        collection.addNumber(1);
        collection.addNumber(2);
        collection.addNumber(3);
        System.out.println("Number Collection: " + collection);

        // Create and use NameNumber
        NameNumber nameNumber = new NameNumber("Alice", 42);
        System.out.println(nameNumber);

        // Create and use DisruptLecture
        DisruptLecture disruptLecture = new DisruptLecture();
        disruptLecture.displayMessage();

        // Create and use TestArithmetic
        TestArithmetic testArithmetic = new TestArithmetic();
        int sum = testArithmetic.add(5, 3);
        int difference = testArithmetic.subtract(5, 3);
        int product = testArithmetic.multiply(5, 3);
        double quotient = testArithmetic.divide(5, 3);

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + difference);
        System.out.println("Product: " + product);
        System.out.println("Quotient: " + quotient);

        // Create and use GlyphTest
        GlyphTest glyphTest = new GlyphTest();
        glyphTest.printGlyph();

        // Create and use Transmogrify
        Transmogrify transmogrify = new Transmogrify();
        transmogrify.performTransformation();
    }
}
