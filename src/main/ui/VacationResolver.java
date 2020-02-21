package ui;

import model.Destinations;
import model.DreamVacation;
import persistence.Reader;
import persistence.Writer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Vacation resolver application
public class VacationResolver {
    private static final String DREAM_VACATION_TXT = "./data/DreamVacation.txt";
    private Destinations chooseDestination;
    private DreamVacation createDreamDestinations;
    private Scanner input;
    private String nextDestination;
    private String dreamCountry;
    private String anotherDreamCountry;

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

        start();

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
    //REFERENCES: https://stackoverflw.com/questions/4389480/print-array-without-brackets-and-commas
    //          : https://javaconceptoftheday.com/remove-white-spaces-from-string-in-java/


    public void loadDreamVacations() {
        try {
            List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(DREAM_VACATION_TXT));
            createDreamDestinations = dreamVacations.get(0);
            ArrayList<String> fixedList = createDreamDestinations.viewDreamDestinations();
            String fixedToString = fixedList.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "")
                    .replaceAll("\\s+", ", ");
            System.out.println(fixedToString);

        } catch (IOException e) {
            start();
        }
    }

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
        }
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: initializes Destinations and DreamVacation

    private void start() {
        chooseDestination = new Destinations();
        createDreamDestinations = new DreamVacation();
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: displays the seasons (summer, winter, or exit options) to the user

    private void startDisplay() {
        System.out.println("Choose the season for your next vacation");
        System.out.println("\n Summer");
        System.out.println("\n Winter");
        System.out.println("\n View (to load previous Dream Vacation List)");
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
            loadDreamVacations();
            runResolver();
        } else {
            System.out.println("You can select from either Summer, Winter, or View. See you!");
        }
    }


    // EFFECTS: allows the user to choose a destination country and view the list of cities of that country

    private void showSummerDestination() {
        System.out.println("Here is the list of summer travel destinations!");
        ArrayList<String> summer = chooseDestination.viewSummerDestinations();
        System.out.println(summer);
        System.out.println("Choose a country of your choice for next vacation!");
        String country = input.next();
        nextDestination = chooseDestination.chooseSummerDestination(country);
        if (!checkForCountry(nextDestination)) {
            System.out.println("Sorry, that's wrong input. We will take you to main menu again.");
            runResolver();
        } else {
            System.out.println("Great! your next travel destination is: " + nextDestination);
            System.out.println("For your travel to: " + nextDestination + ", here is the list of popular cities:");
            System.out.println(chooseDestination.getCityFromSummerDestinations(country));
            makeDreamDestinations();

        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to add a country to the DreamVacation list
    private void makeDreamDestinations() {
        System.out.println("Choose another country from our selection to include in your Dream Vacation list");
        dreamCountry = input.next();
        if (!chooseDestination.viewSummerDestinations().contains(dreamCountry)) {
            System.out.println("Sorry, that's wrong input. Please select again from our list");
            makeDreamDestinations();
        } else if (dreamCountry.equals(nextDestination)) {
            System.out.println("Sorry, this was already chosen. Please select another country for Dream Vacation list");
            makeDreamDestinations();
        } else {
            selectAnother();
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to add a country to the DreamVacation list

    private void selectAnother() {
        createDreamDestinations.addDreamDestinations(dreamCountry);
        System.out.println("Add another country from the above destinations to your Dream Vacation list!");
        anotherDreamCountry = input.next();
        if (!chooseDestination.viewSummerDestinations().contains(anotherDreamCountry)) {
            System.out.println("Sorry, that's wrong input. Please select again from our list");
            selectAnother();
        } else if (createDreamDestinations.alreadyInDreamDestinations(anotherDreamCountry)) {
            System.out.println("Sorry, please select another country");
            selectAnother();
        } else if (anotherDreamCountry.equals(nextDestination)) {
            System.out.println("Sorry, please select another country");
            selectAnother();
        } else {
            createDreamDestinations.addDreamDestinations(anotherDreamCountry);
            System.out.println("To view and save your Dream Vacation list, type YES");
            String answer = input.next();
            typeAnswer(answer);


        }
    }

    // EFFECTS: verifies the user's input of the country is included in the list of destinations provided

    private Boolean checkForCountry(String nextDestination) {
        return !nextDestination.equals("Sorry, that's wrong input");
    }

    // EFFECTS: allows the user to choose a destination country and view the list of cities of that country

    private void showWinterDestination() {
        System.out.println("Here is the list of winter travel destinations!");
        ArrayList<String> winter = chooseDestination.viewWinterDestinations();
        System.out.println(winter);
        System.out.println("Choose a country of your choice for next vacation!");
        String country = input.next();
        nextDestination = chooseDestination.chooseWinterDestination(country);
        if (!checkForCountry(nextDestination)) {
            System.out.println("Sorry, that's wrong input. We will take you to main menu again.");
            runResolver();
        } else {
            System.out.println("Great! your next travel destination is: " + nextDestination);
            System.out.println("For your travel to: " + nextDestination + ", here is the list of popular cities:");
            System.out.println(chooseDestination.getCityFromWinterDestinations(country));
            makeWinterDestination();

        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to add a country to the DreamVacation list

    private void makeWinterDestination() {
        System.out.println("Choose another country from our selection to include in your Dream Vacation list");
        dreamCountry = input.next();
        if (!chooseDestination.viewWinterDestinations().contains(dreamCountry)) {
            System.out.println("Sorry, that's wrong input. Please select again from our list");
            makeWinterDestination();
        } else if (dreamCountry.equals(nextDestination)) {
            System.out.println("Sorry, this was already chosen. Please select another country for Dream Vacation list");
            makeWinterDestination();
        } else {
            selectAnotherForWinter();
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to add a country to the DreamVacation list

    private void selectAnotherForWinter() {
        createDreamDestinations.addDreamDestinations(dreamCountry);
        System.out.println("Add another country from the above destinations to your Dream Vacation list!");
        anotherDreamCountry = input.next();
        if (!chooseDestination.viewWinterDestinations().contains(anotherDreamCountry)) {
            System.out.println("Sorry, that's wrong input. Please select again from our list");
            selectAnotherForWinter();
        } else if (createDreamDestinations.alreadyInDreamDestinations(anotherDreamCountry)) {
            System.out.println("Sorry, please select another country");
            selectAnotherForWinter();
        } else if (anotherDreamCountry.equals(nextDestination)) {
            System.out.println("Sorry, please select another country");
            selectAnotherForWinter();
        } else {
            createDreamDestinations.addDreamDestinations(anotherDreamCountry);
            System.out.println("To save your Dream Vacation list, type YES");
            String answer = input.next();
            typeAnswer(answer);

        }
    }

    // EFFECTS: allows the user to view the DreamVacation list or to exit without viewing the list

    private void typeAnswer(String answer) {
        if (answer.equals("YES") || answer.equals("yes") || answer.equals("Yes")) {
            saveDreamVacations();
            System.out.println("See you next time!");
        } else {
            System.out.println("good bye!");
        }
    }
}



