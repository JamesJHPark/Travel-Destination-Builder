package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DreamVacationTest {

    private DreamVacation testDreamDestinations;
    Destination italy = new Destination("Italy");
    Destination spain = new Destination("Spain");
    Destination croatia = new Destination("Croatia");
    Destination vietnam = new Destination("Vietnam");
    Destination japan = new Destination("Japan");

    @BeforeEach
    void runBefore() {
        testDreamDestinations = new DreamVacation();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testDreamDestinations.getNumDreamDestinations());
    }



    @Test
    void testAddDreamsDestinations() {
        testDreamDestinations.addDreamDestinations(italy);
        testDreamDestinations.addDreamDestinations(croatia);
        testDreamDestinations.addDreamDestinations(spain);
        testDreamDestinations.addDreamDestinations(japan);
        testDreamDestinations.addDreamDestinations(vietnam);
        assertEquals(5, testDreamDestinations.getNumDreamDestinations());
    }

    @Test
    void testViewDreamDestination() {
        testDreamDestinations.addDreamDestinations(italy);
        testDreamDestinations.addDreamDestinations(croatia);
        testDreamDestinations.addDreamDestinations(japan);
        testDreamDestinations.addDreamDestinations(spain);
        testDreamDestinations.addDreamDestinations(japan);
        ArrayList<Destination> result = testDreamDestinations.viewDreamDestinations();
        assertEquals(4, testDreamDestinations.getNumDreamDestinations());
        assertEquals("Italy", result.get(0).getDestinationCountryName());
        assertEquals("Croatia", result.get(1).getDestinationCountryName());
        assertEquals("Japan", result.get(2).getDestinationCountryName());
    }

    @Test
    void testAlreadyInDreamList() {
        testDreamDestinations.addDreamDestinations(japan);
        testDreamDestinations.addDreamDestinations(italy);
        testDreamDestinations.addDreamDestinations(spain);
        testDreamDestinations.addDreamDestinations(croatia);
        testDreamDestinations.addDreamDestinations(vietnam);
        assertTrue(testDreamDestinations.alreadyInDreamDestinations(japan));
        assertTrue(testDreamDestinations.alreadyInDreamDestinations(italy));
        assertTrue(testDreamDestinations.alreadyInDreamDestinations(spain));
        assertTrue(testDreamDestinations.alreadyInDreamDestinations(croatia));
        assertTrue(testDreamDestinations.alreadyInDreamDestinations(vietnam));
    }
    @Test
    void notAlreadyInDreamList() {
        testDreamDestinations.addDreamDestinations(italy);
        assertFalse(testDreamDestinations.alreadyInDreamDestinations(japan));
        assertFalse(testDreamDestinations.alreadyInDreamDestinations(spain));
        assertFalse(testDreamDestinations.alreadyInDreamDestinations(croatia));
        assertFalse(testDreamDestinations.alreadyInDreamDestinations(vietnam));

    }

    @Test
    void removeDreamDestinationTest() {
        testDreamDestinations.addDreamDestinations(spain);
        testDreamDestinations.addDreamDestinations(japan);
        ArrayList<Destination> result = testDreamDestinations.viewDreamDestinations();
        assertEquals("Spain", result.get(0).getDestinationCountryName());
        assertTrue(testDreamDestinations.removeDreamDestinations(spain));
        assertFalse(testDreamDestinations.removeDreamDestinations(spain));
        assertEquals("Japan", result.get(0).getDestinationCountryName());
    }

    @Test
    void getDestObjectTest() {
        testDreamDestinations.addDreamDestinations(spain);
        testDreamDestinations.addDreamDestinations(japan);
        ArrayList<String> result = testDreamDestinations.getDestinationObject();
        assertEquals("Spain", result.get(0));
        assertEquals("Japan", result.get(1));

    }

}




