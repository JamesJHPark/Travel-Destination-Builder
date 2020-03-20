package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {
    private JButton startButton;
    private JButton goodbyeButton;
    private StringListener textListener;

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

    public void setStringListener(StringListener listener) {
        this.textListener = listener;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == startButton) {
            if (textListener != null) {
                intro();

            }
        } else if (clicked == goodbyeButton) {
            if (textListener != null) {
                textListener.textEmitted("Click Data menu on top left of the screen, then click exit!\n");
            }
        }

    }

    private void intro() {
        textListener.textEmitted("");
        textListener.textEmitted("Hello there! Choose the season for your next vacation: "
                + "\n"
                + "\nSummer (type Summer in the Season panel)"
                + "\n"
                + "\nWinter (type Winter in the Season panel)"
                + "\n"
                + "\nLoad (to view your previous Dream Vacation list, click Load button on the bottom left)"
                + "\n"
                + "\nExit (click Data menu on top left of the screen, then click exit)"
                + "\n");

    }
}


