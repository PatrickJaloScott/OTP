package manipulation;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StringManipulatorPatrickTest {

    static String testString;

    @BeforeAll
    public static void init() {
        testString = "";
        System.out.println("Initializing with empty string");
    }
    @BeforeEach
    void startEachTest() {
        System.out.println("Comparing empty string resource");
        if(!testString.isEmpty()) {
            testString = "";
            System.out.println("Clearing string resource");
        }
    }

    @Test
    void testReverse() {
        String catDog = "catdog";
        testString = StringManipulatorPatrick.reverse(catDog);
        assertEquals("godtac", testString);
    }

    @Test
    void testCapitalize() {
        String myName = "patrick";
        testString = StringManipulatorPatrick.capitalize(myName);
        assertEquals("PATRICK", testString);
    }

    @AfterEach
    void announceTestFinished() {
        System.out.println("Test finished");
    }

    @AfterAll
    public static void close() {
        testString = "";
        System.out.println("...\nFinished all tests");
    }
}