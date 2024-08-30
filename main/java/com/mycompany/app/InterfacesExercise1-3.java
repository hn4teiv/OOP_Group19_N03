// File: src/TestDebug.java

// Define packages and classes inline for demonstration purposes
package debug;
public class Debug {
    public static void debug(String message) {
        System.out.println("Debug: " + message);
    }
}

package debugoff;
public class Debug {
    public static void debug(String message) {
        // Do nothing
    }
}

// Import the `debug` class here by commenting/uncommenting
// import static debug.Debug.debug;  // Uncomment this line to use the `debug` version
import static debugoff.Debug.debug; // Uncomment this line to use the `debugoff` version

public class TestDebug {
    public static void main(String[] args) {
        debug("This is a test message.");
    }
}
