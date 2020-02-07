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
    private ArrayList dreamList;
    private String command;


    public VacationResolver() {
        runResolver();
    }

    private void runResolver() {
        boolean keepGoing = true;

        init();

        if (keepGoing) {
            startDisplay();
            command = input.next();

            if (command.equals("exit")) {
                keepGoing = false;
            } else {
                inputValue(command);
                System.out.println("See you next time!");
            }
        }
    }

    private void init() {
        input = new Scanner(System.in);
        chooseDestination = new Destinations();
        createDreamDestinations = new DreamVacation();
    }

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
        dreamList = new ArrayList();
        System.out.println("Here is the list of summer travel destinations!");
        ArrayList<String> summer = chooseDestination.viewSummerDestinations();
        System.out.println(summer);
        System.out.println("Choose a country of your choice for next vacation!");
        String country = input.next();
        String nextDestination = chooseDestination.chooseSummerDestination(country);
        System.out.println("Great! your next travel destination is: " + nextDestination);

        System.out.println("For your travel to: " + nextDestination + ", here is the list of popular cities to visit:");
        System.out.println(chooseDestination.getCityFromSummerDestinations(country));
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


    private void showWinterDestinations() {
        dreamList = new ArrayList();
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
        if (answer.equals("YES")) {
            System.out.println(dreamList);
        } else {
            System.out.println("good bye!");
        }
    }

}

