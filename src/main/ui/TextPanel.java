package ui;

import model.DreamVacation;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TextPanel extends JPanel {

    protected JTextArea textArea;
    private JPopupMenu popup;
    DreamVacation createDreamVacation = new DreamVacation();
    protected ArrayList<String> fixedList = createDreamVacation.getDestinationObject();

    public TextPanel() {
        textArea = new JTextArea();
        Font bigFont = new Font("sanserif", Font.PLAIN, 14);
        textArea.setFont(bigFont);
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
            }
        });

    }

    public void appendText(String text) {
        textArea.append(text);
    }

    public void addOnText(String text) {
        fixedList.add(text);
        textArea.setText("Now, let's build your own Dream Vacation List."
                + "\nEnter as many countries as you wish "
                + "into Dream Vacation Panel on the left!"
                + "\n" + fixedList.toString());
    }

    public void setText(String s) {
        textArea.setText(s);
    }

    public void replaceSelection() {
        JTextComponent c = textArea;
        c.replaceSelection("");
    }

    public void removeText(String dreamVacation) {
        if (fixedList.contains(dreamVacation)) {
            fixedList.remove(dreamVacation);
            textArea.setText("Now, let's build your own Dream Vacation List."
                    + "\nEnter as many countries as you wish "
                    + "into Dream Vacation Panel on the left!");
            textArea.append("\n" + fixedList.toString());
        }
    }

}
