package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinationsTest {
    private Destinations testDestination;
    Destination italy = new Destination("Italy");
    Destination spain = new Destination("Spain");
    Destination croatia = new Destination("Croatia");
    Destination vietnam = new Destination("Vietnam");
    Destination japan = new Destination("Japan");

    Destination mexico = new Destination("Mexico");
    Destination iceland = new Destination("Iceland");
    Destination usa = new Destination("USA");
    Destination france = new Destination("France");
    Destination switzerland = new Destination("Switzerland");

    Destination india = new Destination ("India");
    Destination korea = new Destination ("Korea");

    @BeforeEach
    void runBefore() {
        testDestination = new Destinations();
        testDestination.addedSummerDestinations();
        testDestination.addedWinterDestinations();

    }

    @Test
    void testConstructor() {
        assertEquals(5, testDestination.getNumSummerDestinations());
        assertEquals(5, testDestination.getNumWinterDestinations());
    }

    @Test
    void testSeasonChoice() {
        assertEquals(testDestination.getSummerDestinations(), testDestination.chooseWinterOrSummer("summer"));
        assertEquals(5, testDestination.getSummerDestinations().size());
        assertEquals(testDestination.getNumWinterDestinations(), testDestination.chooseWinterOrSummer("winter"));
        assertEquals(5, testDestination.getWinterDestinations().size());
    }

    @Test
    void testChooseSummerDestination() {
        assertEquals(italy, testDestination.chooseSummerDestination(italy));
        assertEquals(vietnam, testDestination.chooseSummerDestination(vietnam));
        assertEquals("This is not in our list of Winter destinations",
                testDestination.chooseWinterDestination(korea));
    }


    @Test
    void testGetCityFromSummerDestination() {
        assertEquals("Florence, Venice, Rome", testDestination.getCityFromSummerDestinations(italy));
        assertEquals("Dubrovnik, Zagreb, Split", testDestination.getCityFromSummerDestinations(croatia));
        assertEquals("Kyoto, Tokyo, Osaka", testDestination.getCityFromSummerDestinations(japan));
        assertEquals("Barcelona, Madrid, Seville", testDestination.getCityFromSummerDestinations(spain));
        assertEquals("Ho Chih Minh, Da Nang, Hanoi", testDestination.getCityFromSummerDestinations(vietnam));
    }

    @Test
    void testGetCityFromWinterDestination() {
        assertEquals("Cozumel, Tulum, Cancun, Mexico City", testDestination.getCityFromWinterDestinations(mexico));
        assertEquals("Paris, Strasbourg", testDestination.getCityFromWinterDestinations(france));
        assertEquals("Zurich, Lucerne, Geneva, Bern", testDestination.getCityFromWinterDestinations(switzerland));
        assertEquals("Reykjavik, Selfoss", testDestination.getCityFromWinterDestinations(iceland));
        assertEquals("Miami, Los Angeles, New York", testDestination.getCityFromWinterDestinations(usa));
    }

    @Test
    void testChooseWinterDestination() {
        assertEquals(mexico, testDestination.chooseWinterDestination(mexico));
        assertEquals(iceland, testDestination.chooseWinterDestination(iceland));
        assertEquals("This is not in our list of Winter destinations",
                testDestination.chooseWinterDestination(korea));
    }


    @Test
    void testGetCity() {
        assertEquals(null, testDestination.getCityFromWinterDestinations(korea));
    }

    @Test
    void testGetCityAgain() {
        assertEquals(null, testDestination.getCityFromWinterDestinations(korea));
    }

}




