package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
//Represents the Toolbar of the program
public class Toolbar extends JPanel implements ActionListener {
    private JButton startButton;
    private JButton goodbyeButton;
    private StringListener listenerForString;

//EFFECTS: constructs the toolbar of the program
    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());
        startButton = new JButton("START");
        goodbyeButton = new JButton("GOODBYE");
        startButton.addActionListener(this);
        goodbyeButton.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(startButton);
        add(goodbyeButton);
    }

    //MODIFIES: this
    //EFFECTS: sets the StringListener with input listener

    public void toolBarOnHello(StringListener listener) {
        this.listenerForString = listener;
    }


    //EFFECTS: provides the text on the text panel upon clicking either the START or GOODBYE buttons
    // according to each button's intended specification
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == startButton) {

            if (listenerForString != null) {
                intro();

            }
        } else if (clicked == goodbyeButton) {
            if (listenerForString != null) {
                listenerForString.stringInText("Click Data menu on top left of the screen, then click exit!\n");
            }
        }

    }

    //EFFECTS: provides the text of the introduction of the program when START button is clicked

    private void intro() {
        listenerForString.stringInText("Hello there! Choose the season for your next vacation: "
                + "\n"
                + "\nSummer (type Summer in the Season text field and click SUBMIT)"
                + "\n"
                + "\nWinter (type Winter in the Season text field and click SUBMIT)"
                + "\n"
                + "\nLoad (to view your previous Dream Vacation list, click LOAD LIST button on the bottom left)"
                + "\n"
                + "\nExit (click Data menu on top left of the screen, select exit)"
                + "\n");

    }

}


