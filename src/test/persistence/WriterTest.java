

package persistence;


import model.Destination;
import model.DreamVacation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {
    private static final String TEST_FILE = "./data/testDreamVacationFile1.txt";
    private Writer testWriter;
    DreamVacation createDreamDestinations = new DreamVacation();
    Destination italy = new Destination("Italy");
    Destination spain = new Destination("Spain");

    ArrayList<String> fixedList = new ArrayList<>();

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        createDreamDestinations.addDreamDestinations(italy);
        createDreamDestinations.addDreamDestinations(spain);
    }



    @Test
    void testWriteDreamVacation() {
        // save createDreamDestinations to file
        testWriter.write(createDreamDestinations);
        testWriter.close();

        // now read them back in and verify that the DreamVacation have the expected values
        try {
            List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(TEST_FILE));
            createDreamDestinations = dreamVacations.get(0);
            fixedList = createDreamDestinations.getDestinationObject();
            String fixedToString = fixedList.toString()
                    //REFERENCES: code taken from URL:
                    //           https://stackoverflw.com/questions/4389480/print-array-without-brackets-and-commas
                    //           https://javaconceptoftheday.com/remove-white-spaces-from-string-in-java/
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", " ")

                    .replaceAll("\\s+", ", ");



            assertEquals("Italy, Spain", fixedToString);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}


