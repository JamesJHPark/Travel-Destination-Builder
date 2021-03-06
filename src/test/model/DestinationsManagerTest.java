package model;

import exceptions.IllegalDestinationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DestinationsManagerTest {
    private DestinationsManager testDestination;
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

    Destination korea = new Destination ("Korea");

    @BeforeEach
    void runBefore() {
        testDestination = new DestinationsManager();
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
        assertEquals(5, testDestination.getSummerDestinations().size());
        assertEquals(5, testDestination.getWinterDestinations().size());
    }


    @Test
    void testGetCityFromItaly() {
        try {
            testDestination.getCityFromSummerDestinations(italy);
            assertEquals("Florence, Venice, Rome", testDestination.getCityFromSummerDestinations(italy));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have been thrown in this case!");
        }
    }

    @Test
    void testGetCityFromCroatia() {
        try {
            testDestination.getCityFromSummerDestinations(croatia);
            assertEquals("Dubrovnik, Zagreb, Split", testDestination.getCityFromSummerDestinations(croatia));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have been thrown in this case!");
        }
    }

    @Test
    void testGetCityFromSpain() {
        try {
            testDestination.getCityFromSummerDestinations(spain);
            assertEquals("Barcelona, Madrid, Seville", testDestination.getCityFromSummerDestinations(spain));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have been thrown in this case!");
        }
    }

    @Test
    void testGetCityFromJapan() {
        try {
            testDestination.getCityFromSummerDestinations(japan);
            assertEquals("Kyoto, Tokyo, Osaka", testDestination.getCityFromSummerDestinations(japan));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have been thrown in this case!");
        }
    }

    @Test
    void testGetCityFromVietnam() {
        try {
            testDestination.getCityFromSummerDestinations(vietnam);
            assertEquals("Ho Chih Minh, Da Nang, Hanoi",
                    testDestination.getCityFromSummerDestinations(vietnam));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have been thrown in this case!");
        }
    }

    @Test
    void testGetCityFromMexico() {
        try {
            testDestination.getCityFromWinterDestinations(mexico);
            assertEquals("Cozumel, Tulum, Cancun, Mexico City",
                    testDestination.getCityFromWinterDestinations(mexico));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have been thrown in this case!");
        }
    }

    @Test
    void testGetCityFromFrance() {
        try {
            testDestination.getCityFromWinterDestinations(france);
            assertEquals("Paris, Strasbourg",
                    testDestination.getCityFromWinterDestinations(france));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have been thrown in this case!");
        }
    }

    @Test
    void testGetCityFromSwitzerland() {
        try {
            testDestination.getCityFromWinterDestinations(switzerland);
            assertEquals("Zurich, Lucerne, Geneva, Bern",
                    testDestination.getCityFromWinterDestinations(switzerland));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have been thrown in this case!");
        }
    }


    @Test
    void testGetCityFromIceland() {
        try {
            testDestination.getCityFromWinterDestinations(iceland);
            assertEquals("Reykjavik, Selfoss",
                    testDestination.getCityFromWinterDestinations(iceland));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have been thrown in this case!");
        }
    }

    @Test
    void testGetCityFromUsa() {
        try {
            testDestination.getCityFromWinterDestinations(usa);
            assertEquals("Miami, Los Angeles, New York",
                    testDestination.getCityFromWinterDestinations(usa));
        } catch (IllegalDestinationException e) {
            fail("IllegalDestinationException shouldn't have thrown in this case!");
        }
    }

    @Test
    void testGetCityCatchExceptionSummer() {
        try {
            testDestination.getCityFromSummerDestinations(korea);
            fail("IllegalDestinationException should have been thrown!");
        } catch (IllegalDestinationException e) {
            System.out.println("expected to catch IllegalDestinationException!");
        }
    }

    @Test
    void testGetCityCatchExceptionWinter() {
        try {
            testDestination.getCityFromWinterDestinations(korea);
            fail("IllegalDestinationException should have been thrown!");
        } catch (IllegalDestinationException e) {
            System.out.println("expected to catch IllegalDestinationException!");

        }
    }

}




