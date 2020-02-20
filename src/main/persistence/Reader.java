package persistence;


import model.DreamVacation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read account data from a file
public class Reader {
    public static final String DELIMITER = ", ";


    // EFFECTS: returns a list of accounts parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static ArrayList readDreamVacations(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return (ArrayList) parseContent(fileContent);
    }
    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file

    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }


    private static List<String> parseContent(List<String> fileContent) {
        ArrayList<DreamVacation> dreamVacations = new ArrayList<>();
        return fileContent;
    }


}
