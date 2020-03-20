package ui;

import model.DreamVacation;

import java.util.ArrayList;

public class Singleton {

    private static Singleton single_instance = null;

    private ArrayList<String> masterList = new ArrayList<>();
    private DreamVacation createDreamVacation = new DreamVacation();

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (single_instance == null) {
            single_instance = new Singleton();
        }
        return single_instance;
    }

    public static ArrayList<String> getMasterList() {
        return single_instance.masterList;
    }

    public static DreamVacation getDreamVacation() {
        return single_instance.createDreamVacation;
    }
}