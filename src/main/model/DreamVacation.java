package model;

import persistence.Saveable;

import java.io.PrintWriter;
import java.util.ArrayList;


// Represents the customized list of dream destinations that user has selected from the list of destinations

public class DreamVacation implements Saveable {
    private ArrayList<Destination> dreamDestinations;

    public DreamVacation() {
        dreamDestinations = new ArrayList<>();
    }

    //REQUIRES: the country to be added to the list of dream destinations has to be from the list of
    //          either summer or winter destinations provided in the the class, Destinations
    //MODIFIES: this
    //EFFECTS:  to add the dream destinations to the dreamDestinations list if the chosen vacation(s)
    //          are not already included in the list


    public ArrayList<Destination> addDreamDestinations(Destination destination) {

        if (!dreamDestinations.contains(destination)) {
            dreamDestinations.add(destination);
        }
        return dreamDestinations;
    }

    //MODIFIES: this
    //EFFECTS: to return a boolean value if the destination is removed from the dreamDestinations array list,
    // false otherwise.
    public boolean removeDreamDestinations(Destination destination) {
        return dreamDestinations.remove(destination);
    }


    //EFFECTS: to return a boolean value if the typed country is already in the dreamDestinations list, and
    //         false otherwise.

    public Boolean alreadyInDreamDestinations(Destination destination) {
        return dreamDestinations.contains(destination);
    }

    //EFFECTS: to return the number of dream destinations in the list
    public int getNumDreamDestinations() {
        return dreamDestinations.size();
    }

    //EFFECTS: to return the list of dream destinations that the user has chosen
    public ArrayList<Destination> viewDreamDestinations() {
        return dreamDestinations;
    }

    //EFFECTS: to return the list country names of dream destinations

    public ArrayList<String> getDestinationObject() {
        ArrayList<String> dreamDestinationNames = new ArrayList<>();
        for (Destination d : dreamDestinations) {
            dreamDestinationNames.add(d.getDestinationCountryName());
        }
        return dreamDestinationNames;
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    public void save(PrintWriter printWriter) {
        ArrayList<Destination> fixedList = dreamDestinations;
        String fixedToString = fixedList.toString()
                //REFERENCES: code taken from URL:
                //           https://stackoverflw.com/questions/4389480/print-array-without-brackets-and-commas
                //           https://javaconceptoftheday.com/remove-white-spaces -from-string-in-java/
                .replace("[", "")
                .replace("]", "")
                .replace(",", " ")

                .replaceAll("\\s+", "  ");

        printWriter.print(fixedToString);

    }
}



