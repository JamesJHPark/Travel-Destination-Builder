package model;

import java.util.Objects;

//Represents the Destination class with a constructor to create a destination object with the name of a country

public class Destination {
    private String countryName;

    //EFFECTS: constructs the Destination object with a string parameter of the country name
    public Destination(String countryName) {
        this.countryName = countryName;

    }

    //EFFECTS: getter for country name of the destination object
    public String getDestinationCountryName() {
        return this.countryName;
    }



    //EFFECTS: returns this.countryName of the destination object
    @Override
    public String toString() {
        return this.countryName;
    }



    //EFFECTS: hashcode and equals set to the class field, countryName
    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Destination)) {
            return false;
        }
        Destination that = (Destination) o;
        return countryName.equals(that.countryName);
    }

    //EFFECTS: hashcode and equals set to the class field, countryName
    @Override
    public int hashCode() {
        return Objects.hash(countryName);
    }

}




