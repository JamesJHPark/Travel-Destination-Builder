package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DestinationTest {
    private Destination destination;

    @BeforeEach
    void runBefore() {
        destination = new Destination("Korea");
    }

    @Test
    void testConstructor() {
        assertEquals("Korea", destination.getDestinationCountryName());
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



