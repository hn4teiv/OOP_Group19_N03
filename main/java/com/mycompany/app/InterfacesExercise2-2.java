// File: StringInitializationExample.java

public class StringInitializationExample {
    // Field initialized at the point of definition
    private String initializedInDefinition = "Initialized at Definition";

    // Field initialized by the constructor
    private String initializedInConstructor;

    // Constructor initializing the field
    public StringInitializationExample(String value) {
        this.initializedInConstructor = value;
    }

    // Method to display the values of the fields
    public void displayFields() {
        System.out.println("Field initialized at definition: " + initializedInDefinition);
        System.out.println("Field initialized in constructor: " + initializedInConstructor);
    }

    public static void main(String[] args) {
        // Create an instance with a specific value for the constructor-initialized field
        StringInitializationExample example = new StringInitializationExample("Initialized by Constructor");
        
        // Display the values of the fields
        example.displayFields();
    }
}
