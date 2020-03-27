package model;

import exceptions.IllegalCityException;

import java.util.ArrayList;

// Represents specific summer and winter destinations for future vacation
public class Destinations {

    private ArrayList<Destination> summerDestinations;
    private ArrayList<Destination> winterDestinations;

    //EFFECTS: constructs specified summer and winter destinations in separate ArrayLists
    public Destinations() {
        summerDestinations = new ArrayList<>();
        winterDestinations = new ArrayList<>();
    }


    //EFFECTS: to return the length of the list of summer destinations
    public int getNumSummerDestinations() {
        return summerDestinations.size();
    }

    //EFFECTS: to return the length of the list of winter destinations
    public int getNumWinterDestinations() {
        return winterDestinations.size();
    }


    //EFFECTS: to return the list of all summer destinations
    public ArrayList<Destination> getSummerDestinations() {
        return summerDestinations;
    }

    //EFFECTS: to return the list of all winter destinations
    public ArrayList<Destination> getWinterDestinations() {
        return winterDestinations;
    }

    //MODIFIES: this
    //EFFECTS: to add summer Destinations into summerDestinations list
    public void addedSummerDestinations() {
        Destination italy = new Destination("Italy");
        Destination spain = new Destination("Spain");
        Destination croatia = new Destination("Croatia");
        Destination vietnam = new Destination("Vietnam");
        Destination japan = new Destination("Japan");

        summerDestinations.add(italy);
        summerDestinations.add(spain);
        summerDestinations.add(croatia);
        summerDestinations.add(vietnam);
        summerDestinations.add(japan);
    }


    //MODIFIES: this
    //EFFECTS: to add winter Destinations into winterDestinations list
    public void addedWinterDestinations() {
        Destination mexico = new Destination("Mexico");
        Destination iceland = new Destination("Iceland");
        Destination usa = new Destination("USA");
        Destination france = new Destination("France");
        Destination switzerland = new Destination("Switzerland");
        winterDestinations.add(mexico);
        winterDestinations.add(iceland);
        winterDestinations.add(usa);
        winterDestinations.add(france);
        winterDestinations.add(switzerland);
    }

    //EFFECTS: if the input parameter is not a valid summer Destination, throws IllegalCityException,
    // otherwise, retrieves the list of popular cities for the entered Destination from the summerDestinations list

    public String getCityFromSummerDestinations(Destination d) throws IllegalCityException {
/*
        String city = "Wrong country selected! It was not from the provided list";
*/
        String city;
        if (d.getDestinationCountryName().equalsIgnoreCase("Italy")) {
            city = "Florence, Venice, Rome";
        } else if (d.getDestinationCountryName().equalsIgnoreCase("Spain")) {
            city = "Barcelona, Madrid, Seville";
        } else if (d.getDestinationCountryName().equalsIgnoreCase("Croatia")) {
            city = "Dubrovnik, Zagreb, Split";
        } else if (d.getDestinationCountryName().equalsIgnoreCase("Vietnam")) {
            city = "Ho Chih Minh, Da Nang, Hanoi";
        } else if (d.getDestinationCountryName().equalsIgnoreCase("Japan")) {
            city = "Kyoto, Tokyo, Osaka";
        } else {
            throw new IllegalCityException();
        }
        return city;
    }

    //EFFECTS: if the input parameter is not a valid winter Destination, throws IllegalCityException,
    // otherwise, retrieves the list of popular cities for the entered Destination from the winterDestinations list

    public String getCityFromWinterDestinations(Destination d) throws IllegalCityException {
        String city;
        if (d.getDestinationCountryName().equalsIgnoreCase("Mexico")) {
            city = "Cozumel, Tulum, Cancun, Mexico City";
        } else if (d.getDestinationCountryName().equalsIgnoreCase("Iceland")) {
            city = "Reykjavik, Selfoss";
        } else if (d.getDestinationCountryName().equalsIgnoreCase("USA")) {
            city = "Miami, Los Angeles, New York";
        } else if (d.getDestinationCountryName().equalsIgnoreCase("France")) {
            city = "Paris, Strasbourg";
        } else if (d.getDestinationCountryName().equalsIgnoreCase("Switzerland")) {
            city = "Zurich, Lucerne, Geneva, Bern";
        } else {
            throw new IllegalCityException();
        }
        return city;
    }


}
