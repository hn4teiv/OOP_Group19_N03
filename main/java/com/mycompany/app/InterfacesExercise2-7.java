// File: NoConstructorExample.java

public class NoConstructorExample {
    // No constructor is defined here

    // Method to display a message
    public void displayMessage() {
        System.out.println("Object created using the default constructor.");
    }

    public static void main(String[] args) {
        // Create an object of NoConstructorExample
        NoConstructorExample example = new NoConstructorExample();
        
        // Call the method to verify the object creation
        example.displayMessage();
    }
}
