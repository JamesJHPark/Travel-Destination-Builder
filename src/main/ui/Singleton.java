/*package ui;


// MY PROJECT NO LONGER REQUIRES THE USE OF SINGLETON! will remove this class.

import model.Destination;
import model.DreamVacation;

import java.util.ArrayList;
import java.util.Objects;

// REFERENCE: class codes taken/referenced from https://www.geeksforgeeks.org/singleton-class-java/

public class Singleton {

    private static Singleton single_instance = null;
    private ArrayList<Destination> masterList = new ArrayList<>();
    private DreamVacation createDreamVacation = new DreamVacation();

    //EFFECTS: constructs singleton object as private
    private Singleton() {
    }

    //EFFECTS: gets the single_instance of the Singleton object
    public static Singleton getInstance() {
        if (single_instance == null) {
            single_instance = new Singleton();
        }
        return single_instance;
    }

    //EFFECTS: hashcode and equals set to the class field, masterList
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Singleton)) {
            return false;
        }
        Singleton singleton = (Singleton) o;
        return masterList.equals(singleton.masterList);
    }

    //EFFECTS: returns the integer value of the hashcode

    @Override
    public int hashCode() {
        return Objects.hash(masterList);
    }

    //EFFECTS: gets the ArrayList<String> of Singleton
    public static ArrayList<Destination> getMasterList() {
        return single_instance.masterList;
    }

    //EFFECTS: gets the DreamVacation of Singleton
    public static DreamVacation getDreamVacation() {
        return single_instance.createDreamVacation;
    }
}*/