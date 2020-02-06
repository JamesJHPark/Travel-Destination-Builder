package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DreamVacationTest {

    private DreamVacation testDreamDestinations;

    @BeforeEach
    void runBefore() {
        testDreamDestinations = new DreamVacation();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testDreamDestinations.getNumDreamDestinations());
    }

    @Test
    void testAddDreamDestinations() {
        assertTrue(testDreamDestinations.addDreamDestinations("Italy"));
        assertFalse(testDreamDestinations.addDreamDestinations("Italy"));
        assertTrue(testDreamDestinations.addDreamDestinations("Croatia"));
        assertFalse(testDreamDestinations.addDreamDestinations("Croatia"));
    }
    @Test
    void testAddDreamsDestinations() {
        testDreamDestinations.addDreamDestinations("Italy");
        testDreamDestinations.addDreamDestinations("Croatia");
        testDreamDestinations.addDreamDestinations("Spain");
        testDreamDestinations.addDreamDestinations("Japan");
        testDreamDestinations.addDreamDestinations("Vietnam");
        assertFalse(testDreamDestinations.addDreamDestinations("Italy"));
        assertFalse(testDreamDestinations.addDreamDestinations("Croatia"));
        assertFalse(testDreamDestinations.addDreamDestinations("Spain"));
        assertFalse(testDreamDestinations.addDreamDestinations("Japan"));
        assertFalse(testDreamDestinations.addDreamDestinations("Vietnam"));
        assertEquals(5, testDreamDestinations.getNumDreamDestinations());
    }

    @Test
    void testViewDreamDestination() {
        testDreamDestinations.addDreamDestinations("Italy");
        testDreamDestinations.addDreamDestinations("Croatia");
        testDreamDestinations.addDreamDestinations("Japan");
        assertTrue(testDreamDestinations.addDreamDestinations("Spain"));
        assertFalse(testDreamDestinations.addDreamDestinations("Japan"));
        ArrayList<String> result = testDreamDestinations.viewDreamDestinations();
        assertEquals(4, testDreamDestinations.getNumDreamDestinations());
        assertEquals("Italy", result.get(0));
        assertEquals("Croatia", result.get(1));
        assertEquals("Japan", result.get(2));
    }

}




