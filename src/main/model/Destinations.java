package model;

import java.util.ArrayList;

// Represents specific summer and winter destinations for future vacation
public class Destinations {

    private ArrayList<Destination> summerDestinations;
    private ArrayList<Destination> winterDestinations;

    //EFFECTS: Destinations has specified summer and winter destinations in separate ArrayLists

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

    //REQUIRES: the season has to be either summer or winter
    //EFFECTS: to return the list of destinations based on the chosen season

    public ArrayList<Destination> chooseWinterOrSummer(String season) {
        ArrayList<Destination> chosenSeason = new ArrayList<>();
        if (season.equals("summer")) {
            chosenSeason = this.summerDestinations;
        }
        if (season.equals("winter")) {
            chosenSeason = this.winterDestinations;
        }
        return chosenSeason;
    }

    //EFFECTS: to return the list of all summer destinations
    public ArrayList<Destination> getSummerDestinations() {
        return summerDestinations;
    }

    public ArrayList<Destination> getWinterDestinations() {
        return winterDestinations;
    }

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

    //EFFECTS: to return the list of all winter destinations
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

    //REQUIRES: the country to be chosen has to be from the provided list of summer destinations
    //EFFECTS: to choose the next travel summer vacation from the list of summer destinations

    public Destination chooseSummerDestination(Destination destination) {
        if (summerDestinations.contains(destination)) {
            return destination;
        } else {
            System.out.println("This is not in our list of Summer destinations");
        }
        return destination;
    }

    //REQUIRES: the country to be chosen has to be from the provided list of winter destinations
    //EFFECTS: to choose the next travel winter vacation from the list of winter destinations

    public Destination chooseWinterDestination(Destination destination) {
        if (winterDestinations.contains(destination)) {
            return destination;
        } else {
            System.out.println("This is not in our list of Winter destinations");
        }
        return destination;
    }

    //REQUIRES: the country to be typed in has to be from the specific list of countries provided
    //EFFECTS: to retrieve the list of popular cities for the chosen country from the summer destinations list

    public String getCityFromSummerDestinations(Destination d) {
        String city = null;
        if (d.getDestinationCountryName().equals("Italy")) {
            city = "Florence, Venice, Rome";
        } else if (d.getDestinationCountryName().equals("Spain")) {
            city = "Barcelona, Madrid, Seville";
        } else if (d.getDestinationCountryName().equals("Croatia")) {
            city = "Dubrovnik, Zagreb, Split";
        } else if (d.getDestinationCountryName().equals("Vietnam")) {
            city = "Ho Chih Minh, Da Nang, Hanoi";
        } else if (d.getDestinationCountryName().equals("Japan")) {
            city = "Kyoto, Tokyo, Osaka";
        }
        return city;
    }


    //REQUIRES: the country to be typed in has to be from the specific list of countries provided
    //EFFECTS: to retrieve the list of popular cities for the chosen country from the winter destinations list

    public String getCityFromWinterDestinations(Destination d) {
        String city = null;
        if (d.getDestinationCountryName().equals("Mexico")) {
            city = "Cozumel, Tulum, Cancun, Mexico City";
        } else if (d.getDestinationCountryName().equals("Iceland")) {
            city = "Reykjavik, Selfoss";
        } else if (d.getDestinationCountryName().equals("USA")) {
            city = "Miami, Los Angeles, New York";
        } else if (d.getDestinationCountryName().equals("France")) {
            city = "Paris, Strasbourg";
        } else if (d.getDestinationCountryName().equals("Switzerland")) {
            city = "Zurich, Lucerne, Geneva, Bern";
        }
        return city;
    }

}
