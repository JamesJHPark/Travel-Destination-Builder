package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {
    private JButton startButton;
    private JButton goodbyeButton;
    private JButton anotherButton;
    private JButton fourthButton;
    private TextPanel textPanel;
    private StringListener textListener;

    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());
        startButton = new JButton("START");
        goodbyeButton = new JButton("See you next time!");
        anotherButton = new JButton("anotherButton");
        fourthButton = new JButton("fourthButton");

        startButton.addActionListener(this);
        goodbyeButton.addActionListener(this);
        anotherButton.addActionListener(this);
        fourthButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(startButton);
        add(goodbyeButton);
        add(anotherButton);
        add(fourthButton);

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
                textListener.textEmitted("goodbye\n");
            }
        } else if (clicked == anotherButton) {
            if (textListener != null) {
                textListener.textEmitted("anotherbutton\n");
            }
        } else {
            if (textListener != null) {
                textListener.textEmitted("fourth button\n");
            }
        }

    }

    private void intro() {
        textListener.textEmitted("");
        textListener.textEmitted("Hello there! Choose the season for your next vacation: "
                + "\n"
                + "\n Summer (type Summer in the Season panel)"
                + "\n"
                + "\n Winter (type Winter in the Season panel)"
                + "\n"
                + "\n Load (to view your previous Dream Vacation list, click Load button on the bottom left)"
                + "\n"
                + "\n Exit (click Data menu on top left of the screen, then click exit)"
                + "\n");

    }
}


