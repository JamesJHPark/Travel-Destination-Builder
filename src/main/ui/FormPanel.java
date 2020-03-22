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

    protected JLabel destinationLabel;
    protected JLabel dreamVacationLabel;
    protected JLabel seasonLabel;
    public static JTextField destinationField;
    public static JTextField dreamVacationField;
    public static JTextField seasonField;
    private JButton okBtn;
    private FormListener formListener;
    private FormListenerAdd formListenerAdd;
    private ListenerEnterKey listenerEnterKey;
    private ListenerRemove listenerRemove;
    private FormListenerSave formListenerSave;
    private FormListenerLoad formListenerLoad;
    private JButton deleteBtn;
    private JButton enterBtn;
    private JButton removeBtn;
    private JButton saveBtn;
    private JButton loadBtn;
    private BufferedImage image;

    //REFERENCE: codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
    //EFFECTS: to construct the FormPanel of the program

    public FormPanel() {
        setPanel();
        okBtnCall();
        deleteBtnCall();
        enterBtn();
        setRemoveBtn();
        setSaveBtn();
        setLoadBtn();
        Border innerBorder = BorderFactory.createTitledBorder("Add Vacation");
        Border outerBorder = BorderFactory.createEmptyBorder(2, 3,  2, 3);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        setLayout(new GridBagLayout());
        callAllButtonMethods();



        // REFERENCE: CODE TAKEN FROM https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
        // REFERENCE: IMAGE(.JPG) FROM: http://clipart-library.com/clipart/1705193.htm
        // EFFECTS: to read the image file from Resources package

        try {
            image = ImageIO.read(
                    new File("C:\\Users\\James\\Desktop\\project_y3o8\\src\\main\\Resources\\picture.jpg"));
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }

    // REFERENCE: CODE TAKEN FROM https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    // REFERENCE: IMAGE(.JPG) FROM: http://clipart-library.com/clipart/1705193.htm
    // EFFECTS: to paint the image on the form panel

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }


    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the button is clicked, and in this
    // case, the purpose of the button is to submit the typed answer to the program

    public void okBtnCall() {
        okBtn.addActionListener(new ActionListener() {
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
                FormEvent ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListenerLoad != null) {
                    formListenerLoad.formEventLoad(ev);
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
                FormEvent ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListenerSave != null) {
                    formListenerSave.formEventSave(ev);
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
                FormEvent ev = new FormEvent(this, destination, dreamVacation, season);
                if (listenerEnterKey != null) {
                    listenerEnterKey.formEventOccurredEnter(ev);

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
                FormEvent ev = new FormEvent(this, destination, dreamVacation, season);
                if (listenerRemove != null) {
                    listenerRemove.formEventOccurredRemove(ev);

                }
            }
        });

    }


    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the button is clicked, and in this
    // case, the purpose of the button is to delete a typed country from the Dream Vacation list

    public void deleteBtnCall() {
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                FormEvent ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListenerAdd != null) {
                    formListenerAdd.formEventAdd(ev);
                }
            }
        });
    }

    //EFFECTS: calls all the methods that set up the buttons and labels/panels
    // on the screen of the form panel of the program

    public void callAllButtonMethods() {
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

    //EFFECTS: sets up the JButtons for submit and delete on the screen of the form panel of the program

    public void setTwoButtons(GridBagConstraints gc) {

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridy = 3;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(okBtn, gc);

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridy = 3;
        gc.gridx = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(deleteBtn, gc);

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
    //EFFECTS: FormListener for the submit JButton

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    //MODIFIES: this
    //EFFECTS: FormListener for the delete JButton

    public void setFormListenerAdd(FormListenerAdd listener) {

        this.formListenerAdd = listener;
    }

    //EFFECTS: this
    //EFFECTS: FormListener for the Alt+E key pressed

    public void setFormListenerEnter(ListenerEnterKey listener) {
        this.listenerEnterKey = listener;
    }


    //MODIFIES: this
    //EFFECTS: FormListener for the Alt+R key pressed

    public void setFormListenerRemove(ListenerRemove listener) {
        this.listenerRemove = listener;
    }

    //MODIFIES: this
    //EFFECTS: FormListener for the JButton for save

    public void setFormListenerSave(FormListenerSave listener) {
        this.formListenerSave = listener;
    }

    //MODIFIES: this
    //EFFECTS: FormListener for the JButton for load

    public void setFormListenerLoad(FormListenerLoad listener) {
        this.formListenerLoad = listener;
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
        okBtn = new JButton("SUBMIT");
        deleteBtn = new JButton("ADD button");
        enterBtn = new JButton("Alt+N to Add");
        removeBtn = new JButton("Alt+R to Remove");
        saveBtn = new JButton("SAVE LIST");
        loadBtn = new JButton("LOAD LIST");
        enterBtn.setMnemonic(KeyEvent.VK_N);
        removeBtn.setMnemonic(KeyEvent.VK_R);
    }


    //EFFECTS: gets the JTextField of the dreamVacationField panel

    public static JTextField getDreamVacationField() {
        return dreamVacationField;
    }

}
