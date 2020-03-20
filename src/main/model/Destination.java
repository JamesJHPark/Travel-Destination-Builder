package model;

public class Destination {
    private String countryName;

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



