package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
//represents the FormPanel of the program

public class FormPanel extends JPanel {
    private JLabel destinationLabel;
    private JLabel dreamVacationLabel;
    private JLabel seasonLabel;
    public static JTextField destinationField;
    public static JTextField dreamVacationField;
    public static JTextField seasonField;

    private FormListener formListener;
    private FormListener formListenerAdd;
    private FormListener formListenerSave;
    private FormListener formListenerLoad;
    private FormListener formListenerEnter;
    private FormListener formListenerRemove;

    private JButton submitBtn;
    private JButton addBtn;
    private JButton enterBtn;
    private JButton removeBtn;
    private JButton saveBtn;
    private JButton loadBtn;

    private FormEvent ev;
    private BufferedImage image;

    //REFERENCE: codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
    //EFFECTS: to construct the FormPanel of the program

    public FormPanel() {
        setPanel();
        Border innerBorder = BorderFactory.createTitledBorder("Add Vacation");
        Border outerBorder = BorderFactory.createEmptyBorder(2, 3,  2, 3);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new GridBagLayout());
        callAllButtonsMade();
        setBackgroundImage();

        submitButton();
        addBtnCall();
        enterBtn();
        setRemoveBtn();
        setSaveBtn();
        setLoadBtn();
    }

    //EFFECTS: sets up the JLabels, text fields, and buttons for the form panel of the program
    public void setPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 310;
        setPreferredSize(dim);
        destinationLabel = new JLabel("Destination: ");
        dreamVacationLabel = new JLabel("Dream Vacation: ");
        seasonLabel = new JLabel("Season: ");
        destinationField = new JTextField(10);
        dreamVacationField = new JTextField(10);
        seasonField = new JTextField(10);
        submitBtn = new JButton("SUBMIT");
        addBtn = new JButton("ADD button");
        enterBtn = new JButton("Alt+N to Add");
        removeBtn = new JButton("Alt+R to Remove");
        saveBtn = new JButton("SAVE LIST");
        loadBtn = new JButton("LOAD LIST");
        enterBtn.setMnemonic(KeyEvent.VK_N);
        removeBtn.setMnemonic(KeyEvent.VK_R);
    }






    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the button is clicked, and in this
    // case, the purpose of the button is to submit the typed answer to the program

    public void submitButton() {
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                FormEvent ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });
    }



    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the button is clicked, and
    // in this case, the purpose of the button is to load the saved data

    public void setLoadBtn() {
        loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListenerLoad != null) {
                    formListenerLoad.formEventOccurred(ev);
                }
            }
        });

    }

    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the button is clicked, and
    // in this case, the purpose of the button is to save the Dream Vacation list

    public void setSaveBtn() {
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListenerSave != null) {
                    formListenerSave.formEventOccurred(ev);
                }
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the alt+N key is pressed

    private void enterBtn() {
        enterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListenerEnter != null) {
                    formListenerEnter.formEventOccurred(ev);

                }
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when alt+R key is pressed

    private void setRemoveBtn() {
        removeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListenerRemove != null) {
                    formListenerRemove.formEventOccurred(ev);

                }
            }
        });

    }


    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the button is clicked, and in this
    // case, the purpose of the button is to delete a typed country from the Dream Vacation list

    public void addBtnCall() {
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListenerAdd != null) {
                    formListenerAdd.formEventOccurred(ev);
                }
            }
        });
    }



    //EFFECTS: gets the JTextField of the dreamVacationField panel

    public static JTextField getDreamVacationField() {
        return dreamVacationField;
    }

    //EFFECTS: gets the JTextField of the VacationField panel

    public static JTextField getVacationField() {
        return destinationField;
    }

    //EFFECTS: gets the JTextField of the Season panel

    public static JTextField getSeasonField() {
        return seasonField;
    }


    //EFFECTS: calls all the methods that set up the buttons and labels/panels
    // on the screen of the form panel of the program

    public void callAllButtonsMade() {
        GridBagConstraints gc = new GridBagConstraints();
        setButtonForDestination(gc);
        setButtonForDreamVacation(gc);
        setButtonForSeason(gc);
        setTwoButtons(gc);
        setEnterBtn(gc);
        removeBtn(gc);
        saveBtn(gc);
        loadBtn(gc);
    }

    //EFFECTS: sets up the label and text field for season on the screen of the form panel of the program

    public void setButtonForDestination(GridBagConstraints gc) {
        ///// FIRST ROW /////
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(seasonLabel, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(seasonField, gc);

    }

    //EFFECTS: sets up the label and text field for Dream Vacation on the screen of the form panel of the program

    public void setButtonForDreamVacation(GridBagConstraints gc) {
        ///// SECOND ROW /////
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(dreamVacationLabel, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(dreamVacationField, gc);

    }

    //EFFECTS: sets up the label and text field for Destination on the screen of the form panel of the program

    public void setButtonForSeason(GridBagConstraints gc) {
        ///// THIRD ROW /////
        gc.weightx = 1;
        gc.weighty = 0.2;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(destinationLabel, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(destinationField, gc);
    }

    //EFFECTS: sets up the FormPanelActionManager for submit and delete on the screen of the form panel of the program

    public void setTwoButtons(GridBagConstraints gc) {

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridy = 3;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(submitBtn, gc);

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridy = 3;
        gc.gridx = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(addBtn, gc);

    }

    //EFFECTS: sets up the JButton for Alt+N key on of the form panel of the program

    public void setEnterBtn(GridBagConstraints gc) {
        gc.weightx = 10;
        gc.weighty = 10;
        gc.gridy = 4;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(enterBtn, gc);
    }

    //EFFECTS: sets up the JButton for Alt+R key on of the form panel of the program

    public void removeBtn(GridBagConstraints gc) {
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridy = 4;
        gc.gridx = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(removeBtn, gc);
    }


    //EFFECTS: sets up the JButton for save on of the form panel of the program

    public void saveBtn(GridBagConstraints gc) {
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridy = 5;
        gc.gridx = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(saveBtn, gc);
    }

    //EFFECTS: sets up the JButton for load on of the form panel of the program

    public void loadBtn(GridBagConstraints gc) {
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridy = 5;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(loadBtn, gc);
    }


    //MODIFIES: this
    //EFFECTS: sets the FormListener for the submit JButton

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    //MODIFIES: this
    //EFFECTS: sets the FormListener for the ADD JButton
    public void setFormListenerAdd(FormListener listener) {
        this.formListenerAdd = listener;
    }

    //EFFECTS: this
    //EFFECTS: sets the FormListener for the Alt+E key pressed

    public void setFormListenerEnter(FormListener listener) {
        this.formListenerEnter = listener;
    }


    //MODIFIES: this
    //EFFECTS: sets the FormListener for the Alt+R key pressed

    public void setFormListenerRemove(FormListener listener) {
        this.formListenerRemove = listener;
    }

    //MODIFIES: this
    //EFFECTS: sets the FormListener for the JButton for save

    public void setFormListenerSave(FormListener listener) {
        this.formListenerSave = listener;
    }

    //MODIFIES: this
    //EFFECTS: sets the FormListener for the JButton for loading

    public void setFormListenerLoad(FormListener listener) {
        this.formListenerLoad = listener;
    }


    // REFERENCE: CODE TAKEN FROM https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    // REFERENCE: IMAGE(.JPG) FROM: http://clipart-library.com/clipart/1705193.htm
    // EFFECTS: to read the image file from Resources package

    public void setBackgroundImage() {
        try {
            image = ImageIO.read(
                    new File("data/picture.jpg"));
        } catch (IOException ex) {
            System.out.println("IOException was caught!");
        }
    }


    // REFERENCE: CODE TAKEN FROM https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    // REFERENCE: IMAGE(.JPG) FROM: http://clipart-library.com/clipart/1705193.htm
    // EFFECTS: to paint the image on the form panel

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
