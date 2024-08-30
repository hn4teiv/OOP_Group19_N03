// File: ExampleClass.java

// A class with different access levels
public class ExampleClass {
    // Public field
    public int publicField = 1;

    // Private field
    private int privateField = 2;

    // Protected field
    protected int protectedField = 3;

    // Package-private field
    int packagePrivateField = 4;

    // Public method
    public void publicMethod() {
        System.out.println("Public Method");
    }

    // Private method
    private void privateMethod() {
        System.out.println("Private Method");
    }

    // Protected method
    protected void protectedMethod() {
        System.out.println("Protected Method");
    }

    // Package-private method
    void packagePrivateMethod() {
        System.out.println("Package-Private Method");
    }
}

// File: TestAccess.java

// A class to test access to ExampleClass members
public class TestAccess {
    public static void main(String[] args) {
        ExampleClass example = new ExampleClass();

        // Accessing fields
        System.out.println("Public field: " + example.publicField); // Works
        // System.out.println("Private field: " + example.privateField); // Error: privateField has private access in ExampleClass
        System.out.println("Protected field: " + example.protectedField); // Works
        System.out.println("Package-private field: " + example.packagePrivateField); // Works

        // Accessing methods
        example.publicMethod(); // Works
        // example.privateMethod(); // Error: privateMethod has private access in ExampleClass
        example.protectedMethod(); // Works
        example.packagePrivateMethod(); // Works
    }
}
