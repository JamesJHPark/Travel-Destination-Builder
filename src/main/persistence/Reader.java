package persistence;


import model.DreamVacation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data from a file
public class Reader {
    public static final String DELIMITER = ",";
    private static Reader reader = new Reader();

    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file

    public static List<DreamVacation> readDreamVacations(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }
    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file

    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }


    private static List<DreamVacation> parseContent(List<String> fileContent) {
        List<DreamVacation> dreamVacations = new ArrayList<>();
 
        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            dreamVacations.add(parseDreamVacations(lineComponents));
        }
        return dreamVacations;
    }

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    private static DreamVacation parseDreamVacations(List<String> components) {
        String countryName1 = components.get(0);
        String countryName2 = components.get(1);
        DreamVacation newDreamVacation = new DreamVacation();
        newDreamVacation.addDreamDestinations(countryName1);
        newDreamVacation.addDreamDestinations(countryName2);

        return newDreamVacation;
    }


}
