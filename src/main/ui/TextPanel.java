package ui;

import model.Destination;
import model.DreamVacation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
//Represents TextPanel of the program
public class TextPanel extends JPanel {

    private JTextArea textArea;
    private JPopupMenu popup;
    DreamVacation thisIsDreamVacation = new DreamVacation();

    public JTextArea getTextArea() {
        return textArea;
    }

    //REFERENCE: setFont code taken from https://stackoverflow.com/questions/31388790/jframe-text-size
    //EFFECTS: constructs the TextPanel of the program with textArea
    public TextPanel() {
        textArea = new JTextArea();
        Font changeFont = new Font("sanserif", Font.PLAIN, 15);
        textArea.setFont(changeFont);
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.append("Welcome! To begin, please press the START button on top left of the screen");
        deleteBySelectMethod();

    }

    //MODIFIES: this
    //EFFECTS: deletes a selected country name from the list with a mouse event

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
/*
                removeText(string);
*/
                Destination removeThisCountry = new Destination(string);
                thisIsDreamVacation.removeDreamDestinations(removeThisCountry);
            }
        });

    }


    //EFFECTS: appends text to the textArea

    public void appendText(String text) {
        textArea.append(text);
    }



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
    //EFFECTS: sets the text according to the string s input

    public void setText(String s) {
        textArea.setText(s);
    }
/*
    public void replaceSelection() {
        JTextComponent c = textArea;
        c.replaceSelection("");
    }*/

    //MODIFIES: this
    //EFFECTS: removes the destination from the masterList and sets the text panel for next step of the application

   /* public void removeText(String dreamVacation) {
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
}
