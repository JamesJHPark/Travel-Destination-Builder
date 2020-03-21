package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DestinationTest {
    private Destination destination;
    private Destination italy;

    @BeforeEach
    void runBefore() {
        destination = new Destination("Korea");
        italy = new Destination("Italy");
    }

    @Test
    void testConstructor() {
        assertEquals("Korea", destination.getDestinationCountryName());
    }

    @Test
    void hashCodeTest() {
        destination.getDestinationCountryName();
        assertFalse(destination.getDestinationCountryName().equals("USA"));
        assertEquals(72683689, destination.hashCode());
        assertFalse(destination.equals("Korea"));
        assertFalse(destination.equals(italy));
        assertFalse(destination.equals("Spain"));
        assertTrue(destination.equals(destination));

    }

    @Test
    void testGetCountryName() {
        assertEquals("Korea", destination.getDestinationCountryName());
    }

    @Test
    void toStringMethod() {
        assertEquals("Korea", destination.toString());

    }
}



