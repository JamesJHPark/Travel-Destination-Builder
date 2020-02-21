package persistence;


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
    private DreamVacation createDreamDestinations;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        createDreamDestinations = new DreamVacation();
        createDreamDestinations.addDreamDestinations("Italy");
        createDreamDestinations.addDreamDestinations("Spain");

    }

    @Test
    void testWriteDreamVacation() {
        // createDreamDestinations to file
        testWriter.write(createDreamDestinations);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(TEST_FILE));
            DreamVacation createDreamDestinations = dreamVacations.get(0);
            ArrayList<String> list1 = createDreamDestinations.viewDreamDestinations();
            String formattedString = list1.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "")
                    .replaceAll("\\s+", ", ")
                    .trim();
            assertEquals("Italy, Spain", formattedString);

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}
