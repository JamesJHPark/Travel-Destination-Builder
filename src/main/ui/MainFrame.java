package ui;

import model.Destination;
import model.Destinations;
import model.DreamVacation;
import persistence.Reader;
import persistence.Writer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s

public class MainFrame extends JFrame {

    public TextPanel textPanel;
    private Toolbar toolbar;
    protected FormPanel formPanel;
    private Destinations destinationsW;
    private Destinations destinations;
    private String d1;
    private String season;
    private String dreamVacation;
    private static final String DREAM_VACATION_TXT = "./data/DreamVacation.txt";
    Singleton singleton = Singleton.getInstance();
    DreamVacation thisIsDreamVacation = singleton.getDreamVacation();
    ArrayList<String> masterList = singleton.getMasterList();
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Data");
    JMenuItem exportDataItem = new JMenuItem("Save Data");
    JMenuItem importDataItem = new JMenuItem("Load Data");
    JMenuItem addCountry = new JMenuItem("Add country (typed in Dream Vacation panel) to list");
    JMenuItem exitItem = new JMenuItem("Exit");
    JMenu secondMenu = new JMenu("Window");
    JMenu showMenu = new JMenu("Launch");
    JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Vacation Form");
    JTextField component = new JTextField();
    JFrame frame = new JFrame();

// EFFECTS: constructs the Main Frame of the GUI
    public MainFrame() {
        super("Dream Vacation");
        setPanel();
        submitMethod();
        deleteMethod();
        removeKey();
        enterKey();
        saveMethod();
        loadMethod();

        toolbar.setStringListener(new StringListener() {
            public void textEmitted(String text) {
                textPanel.setText("");
                textPanel.appendText(text);
            }
        });
    }
    //MODIFIES: this
    //EFFECTS: submits a typed country or season in the text field panels according to the program specifications

    public void submitMethod() {

        formPanel.setFormListener(new FormListener() {
            public void formEventOccurred(FormEvent e) {
                d1 = e.getDestination();
                dreamVacation = e.getDreamVacation();
                season = e.getSeason();
                helperMethod(d1);
                Destination destination = new Destination(dreamVacation);
                thisIsDreamVacation.addDreamDestinations(destination);
                playAddSound();
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: deletes a typed country in the text field panel from Dream Vacation List
    // according to the program specifications

    public void deleteMethod() {

        formPanel.setFormListenerDelete(new FormListenerDelete() {
            public void formEventDelete(FormEvent e) {
                dreamVacation = e.getDreamVacation();
                Destination removeVacacy = new Destination(dreamVacation);
                thisIsDreamVacation.removeDreamDestinations(removeVacacy);
                textPanel.removeText(dreamVacation);
                playDeleteSound();
            }
        });

    }


    //MODIFIES: this
    //EFFECTS: removes a typed country with Alt+R in the text field panel from Dream Vacation List
    // according to the program specifications

    public void removeKey() {
        formPanel.setFormListenerRemove(new ListenerRemove() {
            public void formEventOccurredRemove(FormEvent e) {
                dreamVacation = e.getDreamVacation();
                Destination removeVacacy = new Destination(dreamVacation);
                thisIsDreamVacation.removeDreamDestinations(removeVacacy);
                textPanel.removeText(dreamVacation);
                playDeleteSound();
/*
                textPanel.removeText(dreamVacation);
*/
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: adds a typed country with Alt+E in the text field panel to Dream Vacation List
    // according to the program specifications


    public void enterKey() {
        formPanel.setFormListenerEnter(new ListenerEnterKey() {
            public void formEventOccurredEnter(FormEvent e) {
                d1 = e.getDestination();
                dreamVacation = e.getDreamVacation();
                season = e.getSeason();
                helperMethod(d1);
                Destination destination = new Destination(dreamVacation);
                thisIsDreamVacation.addDreamDestinations(destination);

            }
        });
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: to save the customized list of Dream Vacation to DREAM_VACATION_TXT file

    public void saveMethod() {
        formPanel.setFormListenerSave(new FormListenerSave() {
            public void formEventSave(FormEvent ev) {
                saveFunction();
            }
        });
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: loads the Dream Vacation list from DREAM_VACATION_TXT

    public void loadMethod() {
        formPanel.setFormListenerLoad(new FormListenerLoad() {
            public void formEventLoad(FormEvent ev) {
                try {
                    textPanel.setText("");
                    List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(DREAM_VACATION_TXT));
                    thisIsDreamVacation = dreamVacations.get(0);
                    masterList = thisIsDreamVacation.getDestinationObject();
                    ArrayList<String> tryList = new ArrayList<>();
                    tryList.addAll(masterList);
                    String fixedToString = tryList.toString()
                            //REFERENCES: code taken from URL:
                            //           https://stackoverflow.com/questions/4389480/print-array-without-brackets-and-commas
                            //           https://javaconceptoftheday.com/remove-white-spaces -from-string-in-java/
                            .replace("[", "")
                            .replace("]", "")
                            .replaceAll("\\s+", ", ");

                    textPanel.appendText("The loaded Dream Vacation List includes" + fixedToString + "\n\nOptions:");
                    setLoading();
                } catch (IOException | IndexOutOfBoundsException e) {
                    //caught exception!
                }
            }
        });
    }


    //EFFECTS: sets the loading text panel with options for the program to run or exit

    public void setLoading() {
        textPanel.appendText("\n\n 1.To start the App again and create a fresh, new Dream Vacation List,"
                + " please click START button.");
        textPanel.appendText("\n\n 2.To continue adding to the current list, please type country name into "
                + "Dream Vacation panel \nand press Submit or Alt+N to add, or click Data menu and select Add country");
        textPanel.appendText("\n\n 3.To simply exit the program, please click Data menu and click Exit.");

    }


    //REFERENCE: code taken from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //REFERENCE: code taken from https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
    //REFERENCE: music from https://www.pacdv.com/sounds/interface_sound_effects/sound108.wav
    //EFFECTS: to play sound

    public void playDeleteSound() {
        try {
            File soundFile = new File("src/main/ui/music/javadeletesound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    //REFERENCE: code taken from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //REFERENCE: code taken from https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
    //REFERENCE: music from https://www.pacdv.com/sounds/interface_sound_effects/sound106.wav
    //EFFECTS: to play sound

    public void playAddSound() {
        try {
            File soundFile = new File("src/main/ui/music/addSound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: to save the customized list of Dream Vacation to DREAM_VACATION_TXT file

    public void saveFunction() {
        try {
            Writer writer = new Writer(new File(DREAM_VACATION_TXT));
            writer.write(thisIsDreamVacation);
            writer.close();
            textPanel.appendText("\nYour customized dream vacation list has been saved. "
                    + "\n1.To exit the program, please click on data menu and then click exit. Thank you!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save Dream Vacation to " + DREAM_VACATION_TXT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: helper method to create destinations and destination objects and matches these according to the
    // hard-coded answers in running the application


    public void helperMethod(String d1) {
        Destination destination = new Destination(d1);
        destinations = new Destinations();
        destinations.addedSummerDestinations();
        destinationsW = new Destinations();
        destinationsW.addedWinterDestinations();
        if (season.equalsIgnoreCase("exit")) {
            textPanel.setText("Goodbye!");
        } else if (season.equalsIgnoreCase("Summer")) {
            summerCall(d1, destination);
        } else if (season.equalsIgnoreCase("Winter")) {
            winterCall(d1, destination);
        }

        selectVacation(dreamVacation);
    }


    //EFFECTS: helper method by setting the summer season and provides provides
    // hard-coded answers in running the application


    public void summerCall(String d1, Destination destination) {
        if (season.equalsIgnoreCase("Summer")) {
            textPanel.appendText("\nHere is the list of summer travel destinations!");
            textPanel.appendText("\n" + destinations.getSummerDestinations().toString());
            textPanel.appendText("\n");
            textPanel.appendText("\nPlease choose 1 country from this above list only."
                    + "\nThen, enter the country of your choice into Destination panel on the left"
                    + " for your next vacation.");
        }
        if (d1.length() > 0) {
            textPanel.setText("You have chosen " + d1);
            textPanel.appendText("\nFor your travel to: " + d1
                    + ", here is the list of popular cities:"
                    + "\n"
                    + destinations.getCityFromSummerDestinations(destination));
            textPanel.appendText("\nNow, enter any country that you wish to include in your Dream Vacation list!\n"
                    + "\nPlease type the country name into Dream Vacation panel on the left.");
        }

    }



    //EFFECTS: helper method by setting the winter season and provides provides
    // hard-coded answers in running the application

    public void winterCall(String d1, Destination destination) {
        if (season.equalsIgnoreCase("Winter")) {
            textPanel.appendText("\nHere is the list of winter travel destinations!");
            textPanel.appendText("\n" + destinationsW.getWinterDestinations().toString());
            textPanel.appendText("\n");
            textPanel.appendText("\nPlease choose 1 country from this above list only."
                    + "\nThen, enter the country of your choice into Destination panel on the left"
                    + " for your next vacation.");
        }
        if (d1.length() > 0) {
            textPanel.setText("You have chosen " + d1);
            textPanel.appendText("\nFor your travel to: " + d1
                    + ", here is the list of popular cities:"
                    + "\n"
                    + destinationsW.getCityFromWinterDestinations(destination));
            textPanel.appendText("\nNow, enter any country that you wish to include in your Dream Vacation list!\n"
                    + "\nPlease type the country name into Dream Vacation panel on the left.");
        }
    }


    //MODIFIES: this
    //EFFECTS: selects and adds the dream vacation country to be added to the Dream Vacation list

    public void selectVacation(String dreamVacation) {
        if (dreamVacation.length() > 0) {
            textPanel.setText("");
            textPanel.appendText("Now, let's create your customized Dream Vacation List for your future travels!\n");
            textPanel.addOnText(dreamVacation);

        }
    }


    //MODIFIES: this
    //EFFECTS: sets up the frame, toolbar, textpanel, formpanel to run the application

    public void setPanel() {
        frame.add(component);
        frame.setLayout(new BorderLayout());
        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        frame.add(formPanel, BorderLayout.WEST);
        frame.add(toolbar, BorderLayout.NORTH);
        frame.add(textPanel, BorderLayout.CENTER);
        frame.setSize(1300, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setJMenuBar(createMenuBar());
    }

    //MODIFIES: this
    //EFFECTS: sets up the menu bar of the application with a method included to add country to Dream Vacation List

    private JMenuBar createMenuBar() {
        fileMenuMethods();
        exitMethod();
        importMethod();
        exportMethod();
        showItemMethod();

        addCountry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Destination addThisCountryToo = new Destination(FormPanel.getDreamVacationField().getText());
                textPanel.addOnText(FormPanel.getDreamVacationField().getText());
                thisIsDreamVacation.addDreamDestinations(addThisCountryToo);
                playAddSound();
            }
        });

        return menuBar;
    }

    //EFFECTS: sets up the menu bar of the application

    public void fileMenuMethods() {
        fileMenu.add(addCountry);
        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        showFormItem.setSelected(true);
        showMenu.add(showFormItem);
        secondMenu.add(showMenu);
        menuBar.add(fileMenu);
        menuBar.add(secondMenu);
        fileMenu.setMnemonic(KeyEvent.VK_D);
        exitItem.setMnemonic(KeyEvent.VK_E);
    }

    //EFFECTS: menu method to close the program

    public void exitMethod() {
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Would you like to close the Vacation App?",
                        "YES", JOptionPane.OK_CANCEL_OPTION);

                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: loads the Dream Vacation list from DREAM_VACATION_TXT

    public void importMethod() {
        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    textPanel.setText("");
                    List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(DREAM_VACATION_TXT));
                    thisIsDreamVacation = dreamVacations.get(0);
                    masterList = thisIsDreamVacation.getDestinationObject();
                    ArrayList<String> tryList = new ArrayList<>();
                    tryList.addAll(masterList);

                    String fixedToString = tryList.toString()
                            //REFERENCES: code taken from URL:
                            //           https://stackoverflow.com/questions/4389480/print-array-without-brackets-and-commas
                            //           https://javaconceptoftheday.com/remove-white-spaces -from-string-in-java/
                            .replace("[", "")
                            .replace("]", "")
                            .replaceAll("\\s+", " ");

                    textPanel.appendText("Dream Vacation List Loaded:" + fixedToString + "\n\nOptions:");
                    setLoading();
                } catch (IOException | IndexOutOfBoundsException e) {
                    destinations = new Destinations();
                    thisIsDreamVacation = new DreamVacation();
                }
            }
        });
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: to save the customized list of Dream Vacation to DREAM_VACATION_TXT file

    public void exportMethod() {
        exportDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFunction();
            }
        });
    }

    //EFFECTS: makes the form panel visible if selected, otherwise the form panel is not visible to the user
    public void showItemMethod() {
        showFormItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });
        showFormItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}

/*
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            Destination addCountryWithEnter = new Destination(FormPanel.getDreamVacationField().getText());
            thisIsDreamVacation.addDreamDestinations(addCountryWithEnter);
            System.out.println(thisIsDreamVacation.getDestinationObject());
            masterList.add(FormPanel.getDreamVacationField().getText());
            textPanel.setText(FormPanel.getDreamVacationField().getText());
            repaint();

        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }


    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == e.VK_ENTER) {
                Destination addCountryWithEnter = new Destination(FormPanel.getDreamVacationField().getText());
                thisIsDreamVacation.addDreamDestinations(addCountryWithEnter);
                System.out.println(thisIsDreamVacation.getDestinationObject());
                masterList.add(FormPanel.getDreamVacationField().getText());
                textPanel.setText(FormPanel.getDreamVacationField().getText());

            }

        }
    }*/




