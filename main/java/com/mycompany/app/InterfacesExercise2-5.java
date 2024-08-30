// File: Dog.java

public class Dog {
    // Method to handle no arguments
    public void bark() {
        System.out.println("The dog barks!");
    }

    // Method to handle int argument
    public void bark(int times) {
        System.out.println("The dog barks " + times + " times!");
    }

    // Method to handle double argument
    public void bark(double duration) {
        System.out.println("The dog barks for " + duration + " seconds!");
    }

    // Method to handle char argument
    public void bark(char intensity) {
        System.out.println("The dog barks with intensity level: " + intensity);
    }

    // Method to handle boolean argument
    public void bark(boolean excited) {
        if (excited) {
            System.out.println("The dog barks excitedly!");
        } else {
            System.out.println("The dog barks calmly.");
        }
    }

    public static void main(String[] args) {
        // Create an instance of Dog
        Dog myDog = new Dog();
        
        // Call all overloaded bark() methods
        myDog.bark();                    // No arguments
        myDog.bark(3);                   // int argument
        myDog.bark(5.5);                 // double argument
        myDog.bark('A');                 // char argument
        myDog.bark(true);                // boolean argument
    }
}
