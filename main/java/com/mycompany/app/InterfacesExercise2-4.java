// File: DefaultConstructorExample.java

public class DefaultConstructorExample {
    // Default constructor
    public DefaultConstructorExample() {
        // Print a message when the default constructor is called
        System.out.println("Default Constructor is called.");
    }

    // Overloaded constructor that takes a String argument
    public DefaultConstructorExample(String message) {
        // Print the provided message along with a custom message
        System.out.println("Overloaded Constructor is called. Message: " + message);
    }

    public static void main(String[] args) {
        // Create an object using the default constructor
        DefaultConstructorExample example1 = new DefaultConstructorExample();
        
        // Create an object using the overloaded constructor
        DefaultConstructorExample example2 = new DefaultConstructorExample("Hello, this is a custom message!");
    }
}
