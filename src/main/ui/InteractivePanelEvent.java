package ui;

import java.util.EventObject;

//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s

//Represents the InteractivePanelEvent class with constructor to create an InteractivePanelEvent
public class InteractivePanelEvent extends EventObject {
    private String destination;
    private String dreamVacation;
    private String season;

// EFFECTS: to construct InteractivePanelEvent with Object source, destination, dreamVacation, and season
    public InteractivePanelEvent(Object source, String destination, String dreamVacation, String season) {
        super(source);
        this.destination = destination;
        this.dreamVacation = dreamVacation;
        this.season = season;
    }

    //EFFECTS: to return the string destination of the typed destination in the text field by user

    public String getDestination() {
        return destination;
    }

    //EFFECTS: to return the string season of the typed season in the text field by user
    public String getSeason() {
        return season;
    }

    //EFFECTS: to return the string dreamVacation of the typed dream destination in the text field by user

    public String getDreamVacation() {
        return dreamVacation;
    }
}
