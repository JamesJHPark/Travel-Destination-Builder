package ui;

import model.Destination;
import model.DreamVacation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
//Represents TextPanel of the program
public class TextPanel extends JPanel {


    protected JTextArea textArea;
    private JPopupMenu popup;
    DreamVacation createDreamVacation = new DreamVacation();
    private ArrayList<String> fixedList = createDreamVacation.getDestinationObject();
    ArrayList<String> masterList = Singleton.getMasterList();
    DreamVacation thisIsDreamVacation = Singleton.getDreamVacation();

    //REFERENCE: setFont code taken from https://stackoverflow.com/questions/31388790/jframe-text-size
    //EFFECTS: constructs the TextPanel of the program with textArea
    public TextPanel() {
        textArea = new JTextArea();
        Font changeFont = new Font("sanserif", Font.PLAIN, 15);
        textArea.setFont(changeFont);
        setLayout(new BorderLayout());
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        textArea.append("Welcome! To begin, please press the START button on top left of the screen");
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
                removeText(string);
                Destination removeThisCountry = new Destination(string);
                thisIsDreamVacation.removeDreamDestinations(removeThisCountry);
            }
        });

    }

    public void appendText(String text) {
        textArea.append(text);
    }

    public void addOnText(String text) {
        if (!masterList.contains(text)) {
            masterList.add(text);
        }
        textArea.setText("Build your Dream Vacation List!"
                + "\nType country name "
                + "into Dream Vacation Panel"
                + "\n" + masterList
                + "\n"
                + "\nPlease click on SAVE LIST button when you are finished."
                + "\n"
                + "\n***Please note: You can only enter a country once. There is no point in "
                + "adding multiple of the same country!***");
    }

    public void setText(String s) {
        textArea.setText(s);
    }
/*
    public void replaceSelection() {
        JTextComponent c = textArea;
        c.replaceSelection("");
    }*/

    public void removeText(String dreamVacation) {
        while (masterList.contains(dreamVacation)) {
            masterList.remove(dreamVacation);
            textArea.setText("Build your Dream Vacation List!"
                    + "\nType country name "
                    + "into Dream Vacation Panel"
                    + "\n" + masterList
                    + "\n"
                    + "\nPlease click on SAVE LIST button when you are finished."
                    + "\n"
                    + "\n***For multiple entries of the SAME country name, we will only save "
                    + "the particular country ONCE in the list***");
        }
    }

}
