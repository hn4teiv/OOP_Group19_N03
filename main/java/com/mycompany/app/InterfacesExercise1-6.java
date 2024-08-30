// File: ProtectedDataExample.java

// The first class with protected data
class DataHolder {
    // Protected data
    protected int protectedData = 42;

    // Method to display the protected data
    protected void displayData() {
        System.out.println("Protected Data: " + protectedData);
    }
}

// The second class that manipulates the protected data from DataHolder
public class DataManipulator {
    public void manipulateData() {
        // Create an instance of DataHolder
        DataHolder dataHolder = new DataHolder();

        // Access and manipulate protected data
        System.out.println("Original Data:");
        dataHolder.displayData(); // Accessing protected method

        // Manipulating the protected data
        dataHolder.protectedData = 99; // Modifying protected data

        System.out.println("Modified Data:");
        dataHolder.displayData(); // Accessing protected method again to show modified data
    }

    public static void main(String[] args) {
        // Create an instance of DataManipulator and perform the data manipulation
        DataManipulator manipulator = new DataManipulator();
        manipulator.manipulateData();
    }
}
