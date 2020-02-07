package model;

import java.util.ArrayList;

// Represents specific summer and winter destinations for future vacation

public class Destinations {

    private ArrayList<String> summerDestinations;
    private ArrayList<String> winterDestinations;
    private String country1 = "Italy";
    private String country2 = "Spain";
    private String country3 = "Croatia";
    private String country4 = "Vietnam";
    private String country5 = "Japan";
    private String countryw1 = "Mexico";
    private String countryw2 = "Iceland";
    private String countryw3 = "USA";
    private String countryw4 = "Austria";
    private String countryw5 = "Switzerland";

    public Destinations() {
        summerDestinations = new ArrayList<>();
        summerDestinations.add(country1);
        summerDestinations.add(country2);
        summerDestinations.add(country3);
        summerDestinations.add(country4);
        summerDestinations.add(country5);

        winterDestinations = new ArrayList<>();
        winterDestinations.add(countryw1);
        winterDestinations.add(countryw2);
        winterDestinations.add(countryw3);
        winterDestinations.add(countryw4);
        winterDestinations.add(countryw5);
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

    public ArrayList<String> chooseWinterOrSummer(String season) {
        ArrayList<String> chosenSeason = new ArrayList<>();
        if (season.equals("summer")) {
            chosenSeason = viewSummerDestinations();
        }
        if (season.equals("winter")) {
            chosenSeason = viewWinterDestinations();
        }
        return chosenSeason;
    }

    //EFFECTS: to return the list of all summer destinations

    public ArrayList<String> viewSummerDestinations() {
        return summerDestinations;
    }

    //EFFECTS: to return the list of all winter destinations
    public ArrayList<String> viewWinterDestinations() {
        return winterDestinations;
    }

    //REQUIRES: the country to be chosen has to be from the provided list of summer destinations
    //EFFECTS: to choose the next travel summer vacation from the list of summer destinations

    public String chooseSummerDestination(String country) {
        String selectedSummerDestination;
        if (summerDestinations.contains(country)) {
            selectedSummerDestination = country;
        } else {
            selectedSummerDestination = "Sorry, that's wrong input";
        }
        return selectedSummerDestination;
    }

    //REQUIRES: the country to be chosen has to be from the provided list of winter destinations
    //EFFECTS: to choose the next travel winter vacation from the list of winter destinations

    public String chooseWinterDestination(String country) {

        String selectedWinterDestination;
        if (winterDestinations.contains(country)) {
            selectedWinterDestination = country;
        } else {
            selectedWinterDestination = "Sorry, that's wrong input";
        }
        return selectedWinterDestination;
    }

    //REQUIRES: the country to be typed in has to be from the specific list of countries provided
    //EFFECTS: to retrieve the list of popular cities for the chosen country from the summer destinations list

    public String getCityFromSummerDestinations(String country) {
        String city = null;
        if (country.equals(country1)) {
            city = "Florence, Venice, Rome";
        } else if (country.equals(country2)) {
            city = "Barcelona, Madrid, Seville";
        } else if (country.equals(country3)) {
            city = "Dubrovnik, Zagreb, Split";
        } else if (country.equals(country4)) {
            city = "Ho Chih Minh, Da Nang, Hanoi";
        } else if (country.equals(country5)) {
            city = "Kyoto, Tokyo, Osaka";
        }
        return city;
    }

    //REQUIRES: the country to be typed in has to be from the specific list of countries provided
    //EFFECTS: to retrieve the list of popular cities for the chosen country from the winter destinations list

    public String getCityFromWinterDestinations(String country) {
        String city = null;
        if (country.equals(countryw1)) {
            city = "Cozumel, Tulum, Cancun, Mexico City";
        } else if (country.equals(countryw2)) {
            city = "Reykjavik, Selfoss";
        } else if (country.equals(countryw3)) {
            city = "Miami, Los Angeles, New York";
        } else if (country.equals(countryw4)) {
            city = "Hallstatt, Salzburg, Innsbruck";
        } else if (country.equals(countryw5)) {
            city = "Zurich, Lucerne, Geneva, Bern";
        }
        return city;
    }

}
