
package ui;

import exceptions.IllegalDestinationException;
import model.Destination;
import model.DestinationsManager;
import model.DreamVacation;
import persistence.Reader;
import persistence.Writer;


import java.io.*;
import java.util.*;

// Vacation resolver application
public class VacationResolver {
    private static final String DREAM_VACATION_TXT = "./data/DreamVacation.txt";
    private DestinationsManager chooseDestination;
    private DreamVacation createDreamDestinations;
    private Scanner input;
    private Destination nextDestination;

    ArrayList<String> fixedList = new ArrayList<>();


    // EFFECTS: runs the resolver application
    public VacationResolver() {
        runResolver();
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: processes the application according to the user inputs

    private void runResolver() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);
        loadDreamVacations();

        while (keepGoing) {
            startDisplay();
            command = input.next();

            if (command.equals("exit")) {
                keepGoing = false;
            } else {
                inputValue(command);
                return;
            }
        }
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS:loads dreamVacations from DREAM_VACATION_TXT, if such file exists;
    // if not, the method initializes application with default values for chooseDestination
    // as well as createDreamDestinations

    public void loadDreamVacations() {
        try {

            List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(DREAM_VACATION_TXT));
            createDreamDestinations = dreamVacations.get(0);
            fixedList = createDreamDestinations.getDestinationObject();

        } catch (IOException | IndexOutOfBoundsException e) {
            start();
        }
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: to save the customized list of createDreamDestinations to DREAM_VACATION_TXT file

    private void saveDreamVacations() {
        try {
            Writer writer = new Writer(new File(DREAM_VACATION_TXT));

            writer.write(createDreamDestinations);
            writer.close();
            System.out.println("Saved to DreamVacation file -> " + DREAM_VACATION_TXT);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save Dream Vacation to " + DREAM_VACATION_TXT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: initializes DestinationsManager and DreamVacation

    private void start() {
        chooseDestination = new DestinationsManager();
        createDreamDestinations = new DreamVacation();
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: displays the seasons (summer, winter, and view, exit options) to the user

    private void startDisplay() {
        System.out.println("Choose the season for your next vacation");
        System.out.println("\n Summer");
        System.out.println("\n Winter");
        System.out.println("\n View (to load previous customized Dream Vacation List)");
        System.out.println("\n exit");

    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: processes the input commands by the user
    private void inputValue(String command) {
        if (command.equals("Summer")) {
            showSummerDestination();
        } else if (command.equals("Winter")) {
            showWinterDestination();
        } else if (command.equals("View")) {
            System.out.println(fixedList);
            runResolver();
        } else {
            System.out.println("You can select from either Summer, Winter, or View. See you!");
        }
    }


    // EFFECTS: allows the user to choose a destination country and view the list of cities of that country

    private void showSummerDestination() {
        System.out.println("Here is the list of summer travel destinations!");
        chooseDestination = new DestinationsManager();
        chooseDestination.addedSummerDestinations();
        for (Destination d : chooseDestination.getSummerDestinations()) {
            System.out.println(d.getDestinationCountryName());
        }
        System.out.println("Choose a country of your choice for next vacation from the list ");
        String countryName = input.next();
        nextDestination = new Destination(countryName);
        System.out.println("Great! your next travel destination is: "
                + nextDestination.getDestinationCountryName());
        System.out.println("For your travel to: " + nextDestination.getDestinationCountryName()
                + ", here is the list of popular cities:");
        try {
            chooseDestination.getCityFromSummerDestinations(nextDestination);
            System.out.println(chooseDestination.getCityFromSummerDestinations(nextDestination));
            makeDreamDestinations();
        } catch (IllegalDestinationException e) {
            System.out.println("IllegalDestinationException caught!");
        }

    }


    // MODIFIES: this
    // EFFECTS: allows the user to add a country to the DreamVacation list
    private void makeDreamDestinations() {

        System.out.println("Let's choose a country to include in your Dream Vacation list");
        String country1 = input.next();
        Destination testCountry1 = new Destination(country1);
        chooseDestination = new DestinationsManager();
        createDreamDestinations.addDreamDestinations(testCountry1);
        selectAnother();

    }


    // MODIFIES: this
    // EFFECTS: allows the user to add a country to the DreamVacation list

    private void selectAnother() {
        System.out.println("Add another country to your Dream Vacation list!");
        String country2 = input.next();
        Destination testCountry2 = new Destination(country2);
        createDreamDestinations.addDreamDestinations(testCountry2);
        finalStep();
    }


    private void finalStep() {
        System.out.println("To view your Dream Vacation list, type YES");
        String answer = input.next();
        typeAnswer(answer);
        System.out.println("To save this customized list, type SAVE");
        String save = input.next();
        saveYes(save);
    }


    private void typeAnswer(String answer) {
        if (answer.equals("YES") || answer.equals("yes") || answer.equals("Yes")) {
            System.out.println(createDreamDestinations.getDestinationObject());
        } else {
            System.out.println("You chose not to view the list");
        }
    }


    //EFFECTS: allows the user to save the customized DreamVacation list to DREAM_VACATION_TXT file

    private void saveYes(String save) {
        if (save.equals("SAVE") || save.equals("save") || save.equals("Save")) {
            saveDreamVacations();
        } else {
            System.out.println("Not saved, See you next time!");
        }
    }


    // EFFECTS: verifies the user's input of the country is included in the list of destinations provided


    private void showWinterDestination() {
        System.out.println("Here is the list of summer travel destinations!");
        chooseDestination = new DestinationsManager();
        chooseDestination.addedWinterDestinations();
        for (Destination d : chooseDestination.getWinterDestinations()) {
            System.out.println(d.getDestinationCountryName());
        }
        System.out.println("Choose a country of your choice for next vacation from the list ");
        String countryName = input.next();
        nextDestination = new Destination(countryName);

        System.out.println("Great! your next travel destination is: "
                + nextDestination.getDestinationCountryName());
        System.out.println("For your travel to: " + nextDestination.getDestinationCountryName()
                + ", here is the list of popular cities:");
        try {
            chooseDestination.getCityFromWinterDestinations(nextDestination);
            System.out.println(chooseDestination.getCityFromWinterDestinations(nextDestination));
            makeDreamDestinationsWinter();
        } catch (IllegalDestinationException e) {
            System.out.println("IllegalDestinationException caught!");
        }

    }


    // MODIFIES: this
    // EFFECTS: allows the user to add a country to the DreamVacation list
    private void makeDreamDestinationsWinter() {

        System.out.println("Let's choose a country to include in your Dream Vacation list");
        String country1 = input.next();
        Destination testCountry1 = new Destination(country1);
        chooseDestination = new DestinationsManager();
        System.out.println(createDreamDestinations.getDestinationObject());
        fixedList = createDreamDestinations.getDestinationObject();
        createDreamDestinations.addDreamDestinations(testCountry1);
        selectAnotherWinter();
    }


    // MODIFIES: this
    // EFFECTS: allows the user to add a country to the DreamVacation list
    private void selectAnotherWinter() {
        System.out.println("Add another country to your Dream Vacation list!");
        String country2 = input.next();
        Destination testCountry2 = new Destination(country2);
        chooseDestination = new DestinationsManager();
        createDreamDestinations.addDreamDestinations(testCountry2);
        finalStep();
    }
}



