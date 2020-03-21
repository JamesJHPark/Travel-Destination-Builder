package ui;

import model.DreamVacation;

import java.util.ArrayList;

// REFERENCE: class codes taken/referenced from https://www.geeksforgeeks.org/singleton-class-java/

public class Singleton {

    private static Singleton single_instance = null;
    private ArrayList<String> masterList = new ArrayList<>();
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

    //EFFECTS: gets the ArrayList<String> of Singleton
    public static ArrayList<String> getMasterList() {
        return single_instance.masterList;
    }

    //EFFECTS: gets the DreamVacation of Singleton
    public static DreamVacation getDreamVacation() {
        return single_instance.createDreamVacation;
    }
}