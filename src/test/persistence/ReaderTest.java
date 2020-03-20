
package persistence;


import model.DreamVacation;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ReaderTest {
    private DreamVacation createDreamDestinations;
    ArrayList<String> fixedList = new ArrayList<>();
    @Test
    void testParseDreamVacationFile2() {

        try {

            List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File("./data/testDreamVacationFile2.txt"));
            createDreamDestinations = dreamVacations.get(0);
            fixedList = createDreamDestinations.getDestinationObject();

        } catch (IOException | IndexOutOfBoundsException e) {
            fail("IOException should not have been thrown");
            assertEquals("Iceland, USA", fixedList);

        }
    }

    @Test
    void testParseDreamVacationFile3() {
        try {

            List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File("./data/testDreamVacationFile3.txt"));
            createDreamDestinations = dreamVacations.get(0);
            fixedList = createDreamDestinations.getDestinationObject();

        } catch (IOException | IndexOutOfBoundsException e) {
            fail("IOException should not have been thrown");
            assertEquals("Iceland, USA", fixedList);

        }
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    @Test
    void testIOExceptionHere() {
        try {
            Reader.readDreamVacations(new File("./path/does/not/exist/testDreamVacation.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}


