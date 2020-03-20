package model;

import java.util.Objects;

public class Destination {
    private String countryName;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Destination)) return false;
        Destination that = (Destination) o;
        return countryName.equals(that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName);
    }

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




