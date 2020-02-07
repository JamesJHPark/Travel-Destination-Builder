package model;

import java.util.ArrayList;

// Represents the customized list of dream destinations that user has selected from the list of destinations

public class DreamVacation {
    private ArrayList<String> dreamDestinations;

    public DreamVacation() {
        dreamDestinations = new ArrayList<>();
    }

    //REQUIRES: the country to be added to the list of dream destinations has to be from the list of
    //          either summer or winter destinations provided in the field of the class, Destinations
    //MODIFIES: this
    //EFFECTS:  to add the dream destinations to the dreamDestinations list if the chosen vacation(s)
    //          are not already included in the list

    public Boolean addDreamDestinations(String country) {
        boolean canAdd = false;
        if (!dreamDestinations.contains(country)) {
            canAdd = true;
            dreamDestinations.add(country);
        }
        return canAdd;
    }

    //EFFECTS: to return the number of dream destinations in the list
    public int getNumDreamDestinations() {
        return dreamDestinations.size();
    }

    //EFFECTS: to return the list of dream destinations that the user has chosen
    public ArrayList<String> viewDreamDestinations() {
        return dreamDestinations;
    }
}


