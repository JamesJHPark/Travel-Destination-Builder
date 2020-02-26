package persistence;


import model.DreamVacation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
// A reader that can read DreamVacation data from a file
public class Reader {
    public static final String DELIMITER = ",";
    private static Reader reader = new Reader();


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: returns a list of DreamVacation countries parsed from file; throws
    // IOException if an exception is raised when opening / reading from file

    public static List<DreamVacation> readDreamVacations(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file

    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: returns a list of dream vacation countries parsed from a single list of strings
    // where each string represents the country name of dream vacation

    private static List<DreamVacation> parseContent(List<String> fileContent) {
        List<DreamVacation> dreamVacations = new ArrayList<>();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            dreamVacations.add(parseDreamVacations(lineComponents));
        }
        return dreamVacations;
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER

    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // REQUIRES: components has size 2 where element 0 represents the
    // first country of the dream vacation list, and element 1 represents the
    // second country of the dream vacation list
    // EFFECTS: returns a new DreamVacation constructed from these components
    private static DreamVacation parseDreamVacations(List<String> components) {
        String countryName1 = components.get(0);
        String countryName2 = components.get(1);
        DreamVacation newDreamVacation = new DreamVacation();
        newDreamVacation.addDreamDestinations(countryName1);
        newDreamVacation.addDreamDestinations(countryName2);

        return newDreamVacation;
    }


}
