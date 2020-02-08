package ui;

import model.DreamVacation;
import model.Destinations;

import java.util.ArrayList;
import java.util.Scanner;

//Vacation chooser application
public class VacationResolver {

    private Destinations chooseDestination;
    private DreamVacation createDreamDestinations;
    private Scanner input;
    private ArrayList<String> dreamList;
    private String nextDestination;
    private String dreamCountry;
    private String anotherDreamCountry;


    public VacationResolver() {
        runResolver();
    }
    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    private void runResolver() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            startDisplay();
            String command = input.next();

            if (command.equals("exit")) {
                keepGoing = false;
            } else {
                inputValue(command);
                return;
            }
        }
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    private void init() {
        input = new Scanner(System.in);
        chooseDestination = new Destinations();
        createDreamDestinations = new DreamVacation();
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

    private void startDisplay() {
        System.out.println("Choose the season for your next vacation");
        System.out.println("\n Summer");
        System.out.println("\n Winter");
        System.out.println("\n exit");

    }

    private void inputValue(String command) {
        if (command.equals("Summer")) {
            showSummerDestination();
        } else if (command.equals("Winter")) {
            showWinterDestinations();
        } else {
            System.out.println("You can select from either Summer or Winter");
        }
    }

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
            dreamList = createDreamDestinations.viewDreamDestinations();
            System.out.println("To view your Dream Vacation list, type YES");
            String answer = input.next();

            typeAnswer(answer);
        }
    }


    private Boolean checkForCountry(String nextDestination) {
        return !nextDestination.equals("Sorry, that's wrong input");
    }


    private void showWinterDestinations() {
        dreamList = new ArrayList<>();
        System.out.println("Here is the list of winter travel destinations!");
        ArrayList<String> winter = chooseDestination.viewWinterDestinations();
        System.out.println(winter);
        System.out.println("Choose a country of your choice for next vacation!");

        String country = input.next();
        String nextDestination = chooseDestination.chooseWinterDestination(country);
        System.out.println("Great! your next travel destination is: " + nextDestination);
        System.out.println("For your travel to: " + nextDestination + ", here is the list of popular cities to visit:");
        System.out.println(chooseDestination.getCityFromWinterDestinations(country));


        System.out.println("Now, choose a country from our selection to include in your Dream Vacation list");
        String dreamCountry = input.next();
        createDreamDestinations.addDreamDestinations(dreamCountry);
        dreamList = createDreamDestinations.viewDreamDestinations();
        System.out.println(dreamList);

        System.out.println("Now, add 1 more country to your Dream Vacation list!");
        String anotherDreamCountry = input.next();
        createDreamDestinations.addDreamDestinations(anotherDreamCountry);

        System.out.println("To view your Dream Vacation list, type YES");
        String answer = input.next();
        typeAnswer(answer);
    }

    public void typeAnswer(String answer) {
        if (answer.equals("YES") || answer.equals("yes") || answer.equals("Yes")) {
            System.out.println(dreamList);
            System.out.println("See you next time!");
        } else {
            System.out.println("good bye!");
        }
    }
}

