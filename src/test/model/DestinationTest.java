package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DestinationTest {
    private Destination destination;
    private Destination italy = new Destination("Italy");

    @BeforeEach
    void runBefore() {
        destination = new Destination("Korea");
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
        assertFalse(destination.equals(italy));

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



