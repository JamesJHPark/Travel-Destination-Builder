package model;

import java.io.Serializable;

public class Destination implements Serializable {
    private String countryName;
    private Destination destination;

    public Destination(String countryName) {
        this.countryName = countryName;

    }

    @Override
    public String toString() {
        return this.countryName;
    }


    public String getDestinationCountryName() {
        return this.countryName;
    }
}

   // public String getDestinationCapitalName(Destination destination) {
     //   return this.capitalName;



