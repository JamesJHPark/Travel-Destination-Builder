package ui;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private String destination;
    private String dreamVacation;
    private String season;

    public FormEvent(Object source, String destination, String dreamVacation, String season) {
        super(source);
        this.destination = destination;
        this.dreamVacation = dreamVacation;
        this.season = season;
    }

    public String getDestination() {
        return destination;
    }

    public String getSeason() {
        return season;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDreamVacation() {
        return dreamVacation;
    }


}
