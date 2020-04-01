package ui;

import exceptions.IllegalDestinationException;
import model.Destination;
import model.DestinationsManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


//REFERENCE: the class name (TextPanel), codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
//Represents TextPanel of the program
public class TextPanel extends JPanel {

    private JTextArea textArea;
    private static final String DREAM_VACATION_TXT = "./data/DreamVacation.txt";

    //REFERENCE: setFont code taken from https://stackoverflow.com/questions/31388790/jframe-text-size
    //EFFECTS: constructs the TextPanel of the program with textArea
    public TextPanel() {
        textArea = new JTextArea();
        Font changeFont = new Font("sanserif", Font.PLAIN, 15);
        textArea.setFont(changeFont);
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.append("Welcome! To begin, please press the START button on top left of the screen");
    }

    //MODIFIES: this
    //EFFECTS: appends text to the textArea

    public void appendText(String text) {
        textArea.append(text);
    }

    //MODIFIES: this
    //EFFECTS: sets the text according to the string s input

    public void setText(String s) {
        textArea.setText(s);
    }

    //MODIFIES: this
    //EFFECTS: sets the season with user's response of Summer and provides the user with
    // list of summer Destinations to choose from and shows the list of the corresponding cities of
    // summer Destination that the user has chosen

    public void summerCall(String countryName, Destination destination,
                           DestinationsManager destinationsManager) {
        showCountriesWithSummerSeason(destinationsManager);
        if (countryName.length() > 0) {
            try {
                destinationsManager.getCityFromSummerDestinations(destination);
                popularCitiesRetrieve(countryName);
                textArea.append(destinationsManager.getCityFromSummerDestinations(destination));
                textForDreamVacation();
            } catch (IllegalDestinationException e) {
                textForChoosingRightSummer(destinationsManager);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: sets the season with user's response of Winter and provides the user with
    // list of winter Destinations to choose from and shows the list of the corresponding cities of
    // winter Destination that the user has chosen on textArea.

    public void winterCall(String countryName, Destination destination,
                           DestinationsManager destinationsManager) {
        showCountriesWithWinterSeason(destinationsManager);
        if (countryName.length() > 0) {
            try {
                destinationsManager.getCityFromWinterDestinations(destination);
                popularCitiesRetrieve(countryName);
                textArea.append(destinationsManager.getCityFromWinterDestinations(destination));
                textForDreamVacation();
            } catch (IllegalDestinationException e) {
                textForChoosingRightWinter(destinationsManager);
            }
        }
    }


    //MODIFIES: this
    //EFFECTS: based on the user's response of Summer as the season chosen, provides the user with the
    // list of summer Destinations to choose from on textArea.

    public void showCountriesWithSummerSeason(DestinationsManager destinationsManager) {
        if (InteractivePanel.getSeasonField().getText().equalsIgnoreCase("Summer")) {
            textArea.append("\nHere is the list of summer travel destinations!");
            textArea.append("\n" + destinationsManager.getSummerDestinations().toString() + "\n");
            textArea.append("\nPlease choose 1 country from this above list only."
                    + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT");
        }
    }


    //MODIFIES: this
    //EFFECTS: based on the user's response of Winter as the season chosen, provides the user with the
    // list of winter Destinations to choose from on textArea.

    public void showCountriesWithWinterSeason(DestinationsManager destinationsManager) {
        if (InteractivePanel.getSeasonField().getText().equalsIgnoreCase("Winter")) {
            textArea.append("\nHere is the list of winter travel destinations!");
            textArea.append("\n" + destinationsManager.getWinterDestinations().toString() + "\n");
            textArea.append("\nPlease choose 1 country from this above list only."
                    + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT");
        }
    }


    //MODIFIES: this
    //EFFECTS: shows the list of the corresponding cities of summer or winter Destination that the user has chosen
    public void popularCitiesRetrieve(String countryName) {
        textArea.setText("You have chosen " + countryName);
        textArea.append("\nFor your travel to: " + countryName
                + ", here is the list of popular cities:\n");
    }

    //MODIFIES: this
    //EFFECTS: emits the text instructing the user to add a destination to the customized user DreamVacation list
    // with an additional instruction to save the customized list for future viewing

    public void buildingDreamVacation(ArrayList<Destination> dreamDestinationList) {
        textArea.setText("Build your Dream Vacation List!"
                + "\nType country name "
                + "into Dream Vacation Panel"
                + "\n" + dreamDestinationList
                + "\n"
                + "\nPlease click on SAVE LIST button when you are finished."
                + "\n"
                + "\n***Please note: You can only enter a country once***");
    }



    //MODIFIES: this
    //EFFECTS: emits the text instructing the user to add a destination to the customized user DreamVacation list
    public void textForDreamVacation() {
        textArea.append("\nNow, enter any country that you wish to include in your Dream Vacation list!\n"
                + "\nPlease type the country name into Dream Vacation panel on the left."
                + "\nThen, you can either:\n"
                + "Click Add button or press Alt+N on keyboard or select Add country under Data menu"
                + " to add to Dream Vacation List.");
    }



    //MODIFIES: this
    //EFFECTS: notifies the user that the customized dream vacation list has been saved properly with the
    // instruction to exit out of the program on textArea
    public void saveToFileText() {
        textArea.append("\nYour customized dream vacation list has been saved. "
                + "\nTo exit the program, please click on data menu and then click exit. Thank you!");
    }

    //MODIFIES: this
    //EFFECTS: loads the user's dream vacation list that the user has created on textArea
    public void loadedText(ArrayList<Destination> dreamDestinationList) {
        textArea.append("Your customized Dream Vacation List has been loaded:\n"
                + dreamDestinationList
                + "\n\nOptions:");
    }



    //MODIFIES: this
    //EFFECTS: sets the loading text panel with options for the program to run or exit on textArea, or to
    // continue adding/removing destinations from the customized Dream Vacation list
    public void setLoading() {
        textArea.append("\n\n 1.To start the App again and create a fresh, new Dream Vacation List,"
                + " please click START button and go through the program again.");
        textArea.append("\n\n 2.To continue adding to the current list, please type country name into "
                + "Dream Vacation panel \n and press Alt+N on keyboard, click Add button, "
                + "or click Data menu and select Add country to add."
                + "\n If you wish to remove the country destination from list, please type the country name "
                + "into Dream Vacation Panel and press Alt+R on keyboard.");
        textArea.append("\n\n 3.To simply exit the program, please click Data menu and click Exit.");

    }

    //MODIFIES: this
    //EFFECTS: If the entry to the Destination for choosing the destination from the list of summer destinations
    // provided is invalid, emits the text and instructs the user to choose a destination from the provided list
    public void textForChoosingRightSummer(DestinationsManager destinationsManager) {
        textArea.setText("");
        textArea.append(InteractivePanel.getVacationField().getText()
                + " is an invalid selection! Please select 1 country from the list."
                + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT.\n");
        textArea.append(destinationsManager.getSummerDestinations().toString());
    }

    //MODIFIES: this
    //EFFECTS: If the entry to the Destination for choosing the destination from the list of winter destinations
    // provided is invalid, emits the text and instructs the user to choose a destination from the provided list
    public void textForChoosingRightWinter(DestinationsManager destinationsManager) {
        textArea.setText("");
        textArea.append(InteractivePanel.getVacationField().getText()
                + " is an invalid selection! Please select 1 country from the list."
                + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT.\n");
        textArea.append(destinationsManager.getWinterDestinations().toString());
    }

    //MODIFIES: this
    //EFFECTS: handles the text for the StartButton of the toolbar
    public void getterForHandleText(String text) {
        textArea.setText("");
        textArea.append(text);
    }

    //MODIFIES: this
    //EFFECTS: appends the following statement when FileNotFoundException is thrown/caught

    public void unableToSaveFile() {
        textArea.append("Unable to save Dream Vacation to " + DREAM_VACATION_TXT);
    }


}



