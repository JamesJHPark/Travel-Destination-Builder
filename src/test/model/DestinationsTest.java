package model;

import exceptions.IllegalCityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
        assertEquals(5, testDestination.getSummerDestinations().size());
        assertEquals(5, testDestination.getWinterDestinations().size());
    }

   /* @Test
    void testWinterOrSummer() {
        ArrayList<Destination> summerList = testDestination.chooseWinterOrSummer("summer");
        String summerList1 = summerList.toString()
                //REFERENCES: code taken from URL:
                //           https://stackoverflw.com/questions/4389480/print-array-without-brackets-and-commas
                //           https://javaconceptoftheday.com/remove-white-spaces-from-string-in-java/
                .replace("[", "")
                .replace("]", "")
                .replace(",", ",");

        assertEquals("Italy, Spain, Croatia, Vietnam, Japan", summerList1);

        ArrayList<Destination> winterList = testDestination.chooseWinterOrSummer("winter");
        String winterList1 = winterList.toString()
                //REFERENCES: code taken from URL:
                //           https://stackoverflw.com/questions/4389480/print-array-without-brackets-and-commas
                //           https://javaconceptoftheday.com/remove-white-spaces-from-string-in-java/
                .replace("[", "")
                .replace("]", "");


        assertEquals("Mexico, Iceland, USA, France, Switzerland", winterList1);

    }
*/

    @Test
    void testGetCityFromItaly() {
        try {
            testDestination.getCityFromSummerDestinations(italy);
            assertEquals("Florence, Venice, Rome", testDestination.getCityFromSummerDestinations(italy));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have been thrown!");
        }
    }

    @Test
    void testGetCityFromCroatia() {
        try {
            testDestination.getCityFromSummerDestinations(croatia);
            assertEquals("Dubrovnik, Zagreb, Split", testDestination.getCityFromSummerDestinations(croatia));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have been thrown!");
        }
    }

    @Test
    void testGetCityFromSpain() {
        try {
            testDestination.getCityFromSummerDestinations(spain);
            assertEquals("Barcelona, Madrid, Seville", testDestination.getCityFromSummerDestinations(spain));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have been thrown!");
        }
    }

    @Test
    void testGetCityFromJapan() {
        try {
            testDestination.getCityFromSummerDestinations(japan);
            assertEquals("Kyoto, Tokyo, Osaka", testDestination.getCityFromSummerDestinations(japan));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have been thrown!");
        }
    }

    @Test
    void testGetCityFromVietnam() {
        try {
            testDestination.getCityFromSummerDestinations(vietnam);
            assertEquals("Ho Chih Minh, Da Nang, Hanoi",
                    testDestination.getCityFromSummerDestinations(vietnam));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have been thrown!");
        }
    }

    @Test
    void testGetCityFromMexico() {
        try {
            testDestination.getCityFromWinterDestinations(mexico);
            assertEquals("Cozumel, Tulum, Cancun, Mexico City",
                    testDestination.getCityFromWinterDestinations(mexico));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have been thrown!");
        }
    }

    @Test
    void testGetCityFromFrance() {
        try {
            testDestination.getCityFromWinterDestinations(france);
            assertEquals("Paris, Strasbourg",
                    testDestination.getCityFromWinterDestinations(france));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have been thrown!");
        }
    }

    @Test
    void testGetCityFromSwitzerland() {
        try {
            testDestination.getCityFromWinterDestinations(switzerland);
            assertEquals("Zurich, Lucerne, Geneva, Bern",
                    testDestination.getCityFromWinterDestinations(switzerland));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have been thrown!");
        }
    }


    @Test
    void testGetCityFromIceland() {
        try {
            testDestination.getCityFromWinterDestinations(iceland);
            assertEquals("Reykjavik, Selfoss",
                    testDestination.getCityFromWinterDestinations(iceland));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have been thrown!");
        }
    }

    @Test
    void testGetCityFromUsa() {
        try {
            testDestination.getCityFromWinterDestinations(usa);
            assertEquals("Miami, Los Angeles, New York",
                    testDestination.getCityFromWinterDestinations(usa));
        } catch (IllegalCityException e) {
            fail("IllegalCityException shouldn't have thrown!");
        }
    }

    @Test
    void testGetCityCatchExceptionSummer() {
        try {
            testDestination.getCityFromSummerDestinations(korea);
            fail("IllegalCityException should have been thrown!");
        } catch (IllegalCityException e) {
            System.out.println("expected to catch IllegalCityException!");
        }
    }

    @Test
    void testGetCityCatchExceptionWinter() {
        try {
            testDestination.getCityFromWinterDestinations(korea);
            fail("IllegalCityException should have been thrown!");
        } catch (IllegalCityException e) {
            System.out.println("expected to catch IllegalCityException!");

        }
    }

}




