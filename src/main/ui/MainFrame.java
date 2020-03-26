package ui;

import exceptions.IllegalCityException;
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
//represents the MainFrame of the App and extends JFrame
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
    ArrayList<Destination> masterList = singleton.getMasterList();
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
        addMethod();
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
    //EFFECTS: submits a typed country into Destination panel, Dream Vacation panel,
    // or season in the text field panels according to the program specifications

    public void submitMethod() {
        formPanel.setFormListener(new FormListener() {
            public void formEventOccurred(FormEvent e) {
                d1 = e.getDestination();
                dreamVacation = e.getDreamVacation();
                season = e.getSeason();
                helperMethod(d1);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds Destination typed in the text field panel to the Dream Vacation List
    // according to the program specifications

    public void addMethod() {
        formPanel.setFormListenerAdd(new FormListenerAdd() {
            public void formEventAdd(FormEvent e) {
                playAddSound();
                Destination dreamDest = new Destination(FormPanel.getDreamVacationField().getText());
                if (dreamDest.getDestinationCountryName().length() > 1) {
                    thisIsDreamVacation.addDreamDestinations(dreamDest);
                }
                if (!masterList.contains(dreamDest) && dreamDest.getDestinationCountryName().length() >= 1) {
                    masterList.add(dreamDest);
                }
                textPanel.setText("Build your Dream Vacation List!"
                        + "\nType country name "
                        + "into Dream Vacation Panel"
                        + "\n" + masterList
                        + "\n"
                        + "\nPlease click on SAVE LIST button when you are finished."
                        + "\n"
                        + "\n***Please note: You can only enter a country once***");
            }
        });

    }


    //MODIFIES: this
    //EFFECTS: removes a typed country with Alt+R in the text field panel from Dream Vacation List
    // according to the program specifications

    public void removeKey() {
        formPanel.setFormListenerRemove(new ListenerRemove() {
            public void formEventOccurredRemove(FormEvent e) {
                playDeleteSound();
                Destination dreamDest = new Destination(FormPanel.getDreamVacationField().getText());
                if (masterList.contains(dreamDest)) {
                    masterList.remove(dreamDest);
                    thisIsDreamVacation.removeDreamDestinations(dreamDest);
                }
                textPanel.setText("Build your Dream Vacation List!"
                        + "\nType country name "
                        + "into Dream Vacation Panel"
                        + "\n" + masterList
                        + "\n"
                        + "\nPlease click on SAVE LIST button when you are finished."
                        + "\n"
                        + "\n***Please note: You can only enter a country once***");
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: adds a typed country in the text panel of Dream Vacation with Alt+E or by user clicking on this button
    // to Dream Vacation List

    public void enterKey() {
        formPanel.setFormListenerEnter(new ListenerEnterKey() {
            public void formEventOccurredEnter(FormEvent e) {
                playAddSound();
                Destination dreamDest = new Destination(FormPanel.getDreamVacationField().getText());
                if (dreamDest.getDestinationCountryName().length() > 1) {
                    thisIsDreamVacation.addDreamDestinations(dreamDest);
                }
                if (!masterList.contains(dreamDest) && dreamDest.getDestinationCountryName().length() >= 1) {
                    masterList.add(dreamDest);
                }
                textPanel.setText("Build your Dream Vacation List!"
                        + "\nType country name "
                        + "into Dream Vacation Panel"
                        + "\n" + masterList
                        + "\n"
                        + "\nPlease click on SAVE LIST button when you are finished."
                        + "\n"
                        + "\n***Please note: You can only enter a country once***");
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
                    masterList = thisIsDreamVacation.viewDreamDestinations();
                    textPanel.appendText("Your customized Dream Vacation List has been loaded:\n"
                            + masterList
                            + "\n\nOptions:");
                    setLoading();
                } catch (IOException | IndexOutOfBoundsException e) {
                    masterList = new ArrayList<>();
                    thisIsDreamVacation = new DreamVacation();
                }

            }
        });
    }


    //EFFECTS: sets the loading text panel with options for the program to run or exit

    public void setLoading() {
        textPanel.appendText("\n\n 1.To start the App again and create a fresh, new Dream Vacation List,"
                + " please click START button and go through the program again.");
        textPanel.appendText("\n\n 2.To continue adding to the current list, please type country name into "
                + "Dream Vacation panel \n and press Alt+N on keyboard, click Add button, "
                + "or click Data menu and select Add country to add."
                + "\n If you wish to remove the country destination from list, please type the country name "
                + "into Dream Vacation Panel and press Alt+R on keyboard.");
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
                    + "\nTo exit the program, please click on data menu and then click exit. Thank you!");
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
            thisIsDreamVacation = new DreamVacation();
            masterList = new ArrayList<>();
        } else if (season.equalsIgnoreCase("Winter")) {
            winterCall(d1, destination);
            thisIsDreamVacation = new DreamVacation();
            masterList = new ArrayList<>();
        }

    }


    //EFFECTS: sets the season with user's response of Summer and provides the user with
    // list of summer Destinations to choose from and shows the list of the corresponding cities of summer Destination
    // that the user has chosen

    public void summerCall(String d1, Destination destination) {
        if (season.equalsIgnoreCase("Summer")) {
            textPanel.appendText("\nHere is the list of summer travel destinations!");
            textPanel.appendText("\n" + destinations.getSummerDestinations().toString() + "\n");
            textPanel.appendText("\nPlease choose 1 country from this above list only."
                    + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT"
                    + " for your next vacation.");
        }
        if (d1.length() > 0) {
            try {
                destinations.getCityFromSummerDestinations(destination);
                textPanel.setText("You have chosen " + d1);
                textPanel.appendText("\nFor your travel to: " + d1
                        + ", here is the list of popular cities:"
                        + "\n"
                        + destinations.getCityFromSummerDestinations(destination));
                textPanel.appendText("\nNow, enter any country that you wish to include in your Dream Vacation list!\n"
                        + "\nPlease type the country name into Dream Vacation panel on the left."
                        + "\nThen, you can either:\n"
                        + "Click Add button or press Alt+N on keyboard or select Add country under Data menu"
                        + " to add to Dream Vacation List.");
            } catch (IllegalCityException e) {
                //IllegalCityException caught
            }
        }

    }





    //EFFECTS: sets the season with user's response of Summer and provides the user with
    // list of summer Destinations to choose from and shows the list of the corresponding cities of winter Destination
    // that the user has chosen

    public void winterCall(String d1, Destination destination) {
        if (season.equalsIgnoreCase("Winter")) {
            textPanel.appendText("\nHere is the list of winter travel destinations!");
            textPanel.appendText("\n" + destinationsW.getWinterDestinations().toString() + "\n");
            textPanel.appendText("\nPlease choose 1 country from this above list only."
                    + "\nThen, enter the country of your choice into Destination panel on the left and click SUBMIT"
                    + " for your next vacation.");
        }
        if (d1.length() > 0) {

            try {
                destinationsW.getCityFromWinterDestinations(destination);
                textPanel.setText("You have chosen " + d1);
                textPanel.appendText("\nFor your travel to: " + d1
                        + ", here is the list of popular cities:"
                        + "\n"
                        + destinationsW.getCityFromWinterDestinations(destination));
                textPanel.appendText("\nNow, enter any country that you wish to include in your Dream Vacation list!\n"
                        + "\nPlease type the country name into Dream Vacation panel on the left."
                        + "\nThen, you can either:\n"
                        + "Click Add button or press Alt+N on keyboard or select Add country under Data menu"
                        + " to add to Dream Vacation List.");
            } catch (IllegalCityException e) {
                //IllegalCityException caught
            }
        }
    }


    //MODIFIES: this
    //EFFECTS: selects and adds the dream vacation country to be added to the Dream Vacation list

   /* public void selectVacation(String dreamVacation) {
        if (dreamVacation.length() > 0) {
            textPanel.setText("");
            textPanel.appendText("Now, let's create your customized Dream Vacation List for your future travels!\n");
*//*
            textPanel.addOnText(dreamVacation);
*//*

        }
    }*/


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
        addCountryWithMenu();

        return menuBar;
    }

    //MODIFIES: this
    //EFFECTS: adds destination typed in the Dream Vacation text field to the Dream Vacation List

    public void addCountryWithMenu() {
        addCountry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playAddSound();
                Destination dreamDest = new Destination(FormPanel.getDreamVacationField().getText());
                if (dreamDest.getDestinationCountryName().length() > 1) {
                    thisIsDreamVacation.addDreamDestinations(dreamDest);
                }
                if (!masterList.contains(dreamDest) && dreamDest.getDestinationCountryName().length() >= 1) {
                    masterList.add(dreamDest);
                }
                textPanel.setText("Build your Dream Vacation List!"
                        + "\nType country name "
                        + "into Dream Vacation Panel"
                        + "\n" + masterList
                        + "\n"
                        + "\nPlease click on SAVE LIST button when you are finished."
                        + "\n"
                        + "\n***Please note: You can only enter a country once***");
            }

        });

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

    //EFFECTS: menu method exit to close the program
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
                    masterList = thisIsDreamVacation.viewDreamDestinations();

                    textPanel.appendText("Your customized Dream Vacation List has been loaded:\n"
                            + masterList
                            + "\n\nOptions:");
                    setLoading();
                } catch (IOException | IndexOutOfBoundsException e) {
                    masterList = new ArrayList<>();
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
