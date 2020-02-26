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
    @Test
    void testParseDreamVacationFile2() {
        try {
            List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File("./data/testDreamVacationFile2.txt"));
            DreamVacation createDreamDestinations = dreamVacations.get(0);
            ArrayList<String> list1 = createDreamDestinations.viewDreamDestinations();
            String fixedToString = list1.toString()
                    //REFERENCES: code taken from URL:
                    //           https://stackoverflw.com/questions/4389480/print-array-without-brackets-and-commas
                    //           https://javaconceptoftheday.com/remove-white-spaces-from-string-in-java/
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "")
                    .replaceAll("\\s+", ", ");


            assertEquals("Iceland, USA", fixedToString);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseDreamVacationFile3() {
        try {
            List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File("./data/testDreamVacationFile3.txt"));
            DreamVacation createDreamDestinations = dreamVacations.get(0);
            ArrayList<String> list1 = createDreamDestinations.viewDreamDestinations();
            String fixedToString = list1.toString()
                    //REFERENCES: code taken from URL:
                    //           https://stackoverflw.com/questions/4389480/print-array-without-brackets-and-commas
                    //           https://javaconceptoftheday.com/remove-white-spaces-from-string-in-java/
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "")
                    .replaceAll("\\s+", ", ");


            assertEquals("Vietnam, Croatia", fixedToString);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
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

