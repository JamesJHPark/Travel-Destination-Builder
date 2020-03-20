package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
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
    void testGetCity() {
        assertEquals("Wrong country selected! It was not from the provided list",
                testDestination.getCityFromWinterDestinations(korea));
    }

    @Test
    void testGetCityAgain() {
        assertEquals("Wrong country selected! It was not from the provided list",
                testDestination.getCityFromSummerDestinations(korea));
    }

}




