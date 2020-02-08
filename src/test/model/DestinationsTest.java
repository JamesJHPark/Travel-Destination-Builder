package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinationsTest {
    private Destinations testDestination;

    @BeforeEach
    void runBefore() {
        testDestination = new Destinations();
    }

    @Test
    void testConstructor() {
        assertEquals(5, testDestination.getNumSummerDestinations());
        assertEquals(5, testDestination.getNumWinterDestinations());
    }

    // for this test below, can I combine two methods into this single test???
    // b/c viewDestinations is needed to test the chooseWinterOrSummer method.
    @Test
    void testSeasonChoice() {
        assertEquals(testDestination.viewSummerDestinations(), testDestination.chooseWinterOrSummer("summer"));
        assertEquals(testDestination.viewWinterDestinations(), testDestination.chooseWinterOrSummer("winter"));
    }

    @Test
    void testChooseSummerDestination() {
        assertEquals("Italy", testDestination.chooseSummerDestination("Italy"));
        assertEquals("Vietnam", testDestination.chooseSummerDestination("Vietnam"));
        assertEquals("Sorry, that's wrong input", testDestination.chooseSummerDestination("India"));
    }

    @Test
    void testGetCityFromSummerDestination() {
        assertEquals("Florence, Venice, Rome", testDestination.getCityFromSummerDestinations("Italy"));
        assertEquals("Dubrovnik, Zagreb, Split", testDestination.getCityFromSummerDestinations("Croatia"));
        assertEquals("Kyoto, Tokyo, Osaka", testDestination.getCityFromSummerDestinations("Japan"));
        assertEquals("Barcelona, Madrid, Seville", testDestination.getCityFromSummerDestinations("Spain"));
        assertEquals("Ho Chih Minh, Da Nang, Hanoi", testDestination.getCityFromSummerDestinations("Vietnam"));
    }

    @Test
    void testChooseWinterDestination() {
        assertEquals("Mexico", testDestination.chooseWinterDestination("Mexico"));
        assertEquals("Iceland", testDestination.chooseWinterDestination("Iceland"));
        assertEquals("Sorry, that's wrong input", testDestination.chooseWinterDestination("korea"));
    }

    @Test
    void testGetCityFromWinterDestination() {
        assertEquals("Cozumel, Tulum, Cancun, Mexico City", testDestination.getCityFromWinterDestinations("Mexico"));
        assertEquals("Hallstatt, Salzburg, Innsbruck", testDestination.getCityFromWinterDestinations("Austria"));
        assertEquals("Zurich, Lucerne, Geneva, Bern", testDestination.getCityFromWinterDestinations("Switzerland"));
        assertEquals("Reykjavik, Selfoss", testDestination.getCityFromWinterDestinations("Iceland"));
        assertEquals("Miami, Los Angeles, New York", testDestination.getCityFromWinterDestinations("USA"));
    }

    @Test
    void testGetCityAgain() {
        assertEquals(null, testDestination.getCityFromWinterDestinations("Korea"));
    }

    @Test
    void testGetCity() {
        assertEquals(null, testDestination.getCityFromSummerDestinations("Thailand"));
    }
}




