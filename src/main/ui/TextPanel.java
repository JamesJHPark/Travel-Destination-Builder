package ui;

import model.Destination;
import model.DestinationsManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
//Represents TextPanel of the program
public class TextPanel extends JPanel {

    private JTextArea textArea;
    private DestinationsManager summerDestination;


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


    //EFFECTS: appends text to the textArea

    public void appendText(String text) {
        textArea.append(text);
    }


    //EFFECTS: sets the text according to the string s input

    public void setText(String s) {
        textArea.setText(s);
    }


    public void showCountriesWithSummerSeason(DestinationsManager summerDestinations) {
        if (FormPanel.getSeasonField().getText().equalsIgnoreCase("Summer")) {
            textArea.append("\nHere is the list of summer travel destinations!");
            textArea.append("\n" + summerDestinations.getSummerDestinations().toString() + "\n");
            textArea.append("\nPlease choose 1 country from this above list only."
                    + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT");
        }
    }

    public void showCountriesWithWinterSeason(DestinationsManager winterDestinations) {
        if (FormPanel.getSeasonField().getText().equalsIgnoreCase("Winter")) {
            textArea.append("\nHere is the list of winter travel destinations!");
            textArea.append("\n" + winterDestinations.getWinterDestinations().toString() + "\n");
            textArea.append("\nPlease choose 1 country from this above list only."
                    + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT");
        }
    }



    public void popularCitiesRetrieve(String countryName) {
        textArea.setText("You have chosen " + countryName);
        textArea.append("\nFor your travel to: " + countryName
                + ", here is the list of popular cities:\n");
    }

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

    public void textForDreamVacation() {
        textArea.append("\nNow, enter any country that you wish to include in your Dream Vacation list!\n"
                + "\nPlease type the country name into Dream Vacation panel on the left."
                + "\nThen, you can either:\n"
                + "Click Add button or press Alt+N on keyboard or select Add country under Data menu"
                + " to add to Dream Vacation List.");
    }

    public void saveToFileText() {
        textArea.append("\nYour customized dream vacation list has been saved. "
                + "\nTo exit the program, please click on data menu and then click exit. Thank you!");
    }

    public void loadedText(ArrayList<Destination> dreamDestinationList) {
        textArea.append("Your customized Dream Vacation List has been loaded:\n"
                + dreamDestinationList
                + "\n\nOptions:");
    }




    //EFFECTS: sets the loading text panel with options for the program to run or exit

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

    public void textForChoosingRightSummer(DestinationsManager summerDestinations) {
        textArea.setText("");
        textArea.append(FormPanel.getVacationField().getText()
                + " is an invalid selection! Please select 1 country from the list."
                + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT.\n");
        textArea.append(summerDestinations.getSummerDestinations().toString());
    }

    public void textForChoosingRightWinter(DestinationsManager winterDestinations) {
        textArea.setText("");
        textArea.append(FormPanel.getVacationField().getText()
                + " is an invalid selection! Please select 1 country from the list."
                + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT.\n");
        textArea.append(winterDestinations.getWinterDestinations().toString());
    }


}


/*
        deleteBySelectMethod();
*/
/*
    public void replaceSelection() {
        JTextComponent c = textArea;
        c.replaceSelection("");
    }*/

 /*   //MODIFIES: this
    //EFFECTS: removes the destination from the masterList and sets the text panel for next step of the application

    public void removeText(String dreamVacation) {
        Destination dreamDestination = new Destination(dreamVacation);
        while (masterList.contains(dreamDestination)) {
            System.out.println(masterList);
            masterList.remove(dreamDestination);
            textArea.setText("Build your Dream Vacation List!"
                    + "\nType country name "
                    + "into Dream Vacation Panel"
                    + "\n" + masterList
                    + "\n"
                    + "\nPlease click on SAVE LIST button when you are finished."
                    + "\n"
                    + "\n***Please note: You can only enter a country once***");
        }
    }
*/


//MODIFIES: this
//EFFECTS: deletes a selected country name from the list with a mouse event
/*
    public void deleteBySelectMethod() {
        JMenuItem removeItem = new JMenuItem("Remove this country from the list");
        popup = new JPopupMenu();
        popup.add(removeItem);
        textArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                textArea.getSelectedText();
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(textArea, e.getX(), e.getY());
                }
            }
        });
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String string = textArea.getSelectedText();
*//*
                removeText(string);
*//*
                Destination removeThisCountry = new Destination(string);
                thisIsDreamVacation.removeDreamDestinations(removeThisCountry);
            }
        });

    }*/

//EFFECTS: adds the country name in text to the masterList and sets the text for next step of the application

    /*public void addOnText(String text) {
        Destination addThisCountry = new Destination(text);
        if (!masterList.contains(addThisCountry)) {
            masterList.add(addThisCountry);
        }
        textArea.setText("Build your Dream Vacation List!"
                + "\nType country name "
                + "into Dream Vacation Panel"
                + "\n" + masterList
                + "\n"
                + "\nPlease click on SAVE LIST button when you are finished."
                + "\n"
                + "\n***Please note: You can only enter a country once***");
    }
*/

    /*
    private JPopupMenu popup;
    public JTextArea getTextArea() {
        return textArea;
    }
*/
