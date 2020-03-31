package ui;

import model.Destination;
import model.DestinationsManager;
import model.DreamVacation;
import persistence.Reader;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s

//represents the MainFrame of the App allowing interactive user application to build DreamVacation list
public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private JTextField component;
    private TopPanel topPanel;
    private JFrame frame;
    private InteractivePanel interactivePanel;
    private MenuBuilder menuBuilder;
    private DestinationsManager destinationsManager;
    private String season;
    private DreamVacation thisIsDreamVacation;
    private ArrayList<Destination> dreamDestinationList;
    private Music music;
    private static final String DREAM_VACATION_TXT = "./data/DreamVacation.txt";


    // EFFECTS: constructs the Main Frame of the GUI with text panel, form panel that interactively builds DreamVacation
    //list based on user inputs
    public MainFrame() {
        super("Dream Vacation");
        initializer();
        setPanels();
        buildingDreamVacation();
    }


    // EFFECTS: initializes and instantiates the new objects for DreamVacation, component of JTextField, frame for
    // JFrame, menuBuilder, music, and adds the summer and winter pre-specified destinations to the destinationsManager
    public void initializer() {
        thisIsDreamVacation = new DreamVacation();
        dreamDestinationList = thisIsDreamVacation.viewDreamDestinations();
        component = new JTextField();
        frame = new JFrame();
        menuBuilder = new MenuBuilder();
        destinationsManager = new DestinationsManager();
        destinationsManager.addedSummerDestinations();
        destinationsManager.addedWinterDestinations();
        music = new Music();
    }

    // MODIFIES: this
    // EFFECTS: sets up the frame, and initializes the topPanel, textPanel, interactivePanel and adds these structural
    // features including the JMenuBar to the frame of the program for overall application to run properly
    public void setPanels() {
        frame.add(component);
        frame.setLayout(new BorderLayout());
        topPanel = new TopPanel();
        textPanel = new TextPanel();
        interactivePanel = new InteractivePanel();
        frame.add(interactivePanel, BorderLayout.WEST);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(textPanel, BorderLayout.CENTER);
        frame.setSize(1300, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setJMenuBar(createMenuBar());
    }


    // EFFECTS: returns the JMenu bar of the application

    private JMenuBar createMenuBar() {
        menuBuilder.fileMenuMethods();
        handleExit();
        handleImport();
        handleExport();
        handleShowItem();
        handleAddCountryWithMenu();
        return menuBuilder.getMenuBar();
    }

    // MODIFIES: this
    // EFFECTS: methods that submit user entries to the InteractivePanel, add, remove Destinations from the
    // DreamVacation and dreamDestinationList, as well as saving/loading the DreamVacation List for the user to view.

    private void buildingDreamVacation() {
        submitMethod();
        addMethod();
        removeKey();
        enterKey();
        saveMethod();
        loadMethod();
        handleText();
    }

    // MODIFIES: this
    // EFFECTS: submits a typed country in the text field of the Season and Destination to allow the user to
    // interactively choose season and the destination for the next vacation in the application

    public void submitMethod() {
        interactivePanel.setPanelListener(new PanelListener() {
            public void createEvent(InteractivePanelEvent e) {
                String countryName = e.getDestination();
                season = e.getSeason();
                chooseSeasonMethod(e.getDestination());
            }
        });
    }


    // EFFECTS: helper method to create destination object with countryName and passes in the parameters of
    // String countryName, Destination destination, and DestinationsManager destinationsManager into the
    // testSummerCall, testWinterCall within TextPanel, and creates a new object of thisIsDreamVacation
    // and dreamDestinationList every time the method is called.


    public void chooseSeasonMethod(String countryName) {
        Destination destination = new Destination(countryName);
        if (season.equalsIgnoreCase("Summer")) {
            textPanel.summerCall(countryName, destination, destinationsManager);
            thisIsDreamVacation = new DreamVacation();
            dreamDestinationList = new ArrayList<>();
        } else if (season.equalsIgnoreCase("Winter")) {
            textPanel.winterCall(countryName, destination, destinationsManager);
            thisIsDreamVacation = new DreamVacation();
            dreamDestinationList = new ArrayList<>();
        }
    }



    // MODIFIES: this
    // EFFECTS: adds Destination typed in the text field panel of the Dream Vacation to the user's
    // current Dream Vacation List

    public void addMethod() {
        interactivePanel.setPanelListenerAdd(new PanelListener() {
            public void createEvent(InteractivePanelEvent e) {
                addMethodToList(dreamDestinationList);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds a typed country in the text panel of Dream Vacation with Alt+E or by user clicking on this button
    // to the user's current Dream Vacation List

    public void enterKey() {
        interactivePanel.setPanelListenerEnter(new PanelListener() {
            public void createEvent(InteractivePanelEvent e) {
                addMethodToList(dreamDestinationList);
            }
        });
    }

    // REQUIRES: the destination to be typed has to be of length >=1
    // MODIFIES: this
    // EFFECTS: implements the adding of a typed destination to the user's thisIsDreamVacation, DreamVacation,
    // as well as to the dreamDestinationList.

    public void addMethodToList(ArrayList<Destination> dreamDestinationList) {
        music.playAddSound();
        Destination destination = new Destination(InteractivePanel.getDreamVacationField().getText());
        if (destination.getDestinationCountryName().length() >= 1) {
            thisIsDreamVacation.addDreamDestinations(destination);
        }
        if (!dreamDestinationList.contains(destination)
                && destination.getDestinationCountryName().length() >= 1) {
            dreamDestinationList.add(destination);
        }
        textPanel.buildingDreamVacation(dreamDestinationList);
    }


    // MODIFIES: this
    // EFFECTS: removes a typed country with Alt+R in the text field panel from Dream Vacation List
    // according to the program specifications

    public void removeKey() {
        interactivePanel.setPanelListenerRemove(new PanelListener() {
            public void createEvent(InteractivePanelEvent e) {
                music.playDeleteSound();
                Destination destination = new Destination(InteractivePanel.getDreamVacationField().getText());
                if (dreamDestinationList.contains(destination)) {
                    dreamDestinationList.remove(destination);
                    thisIsDreamVacation.removeDreamDestinations(destination);
                }
                textPanel.buildingDreamVacation(dreamDestinationList);
            }
        });
    }



    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: to save the customized list of Dream Vacation to DREAM_VACATION_TXT file

    public void saveMethod() {
        interactivePanel.setPanelListenerSave(new PanelListener() {
            public void createEvent(InteractivePanelEvent e) {
                saveFunction();
            }
        });
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: loads the Dream Vacation list from DREAM_VACATION_TXT

    public void loadMethod() {
        interactivePanel.setPanelListenerLoad(new PanelListener() {
            public void createEvent(InteractivePanelEvent e) {
                try {
                    textPanel.setText("");
                    List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(DREAM_VACATION_TXT));
                    thisIsDreamVacation = dreamVacations.get(0);
                    dreamDestinationList = thisIsDreamVacation.viewDreamDestinations();
                    textPanel.loadedText(dreamDestinationList);
                    textPanel.setLoading();
                } catch (IOException | IndexOutOfBoundsException ev) {
                    dreamDestinationList = new ArrayList<>();
                    thisIsDreamVacation = new DreamVacation();
                }

            }
        });
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: to save the customized list of Dream Vacation to DREAM_VACATION_TXT file

    public void saveFunction() {
        try {
            Writer writer = new Writer(new File(DREAM_VACATION_TXT));
            writer.write(thisIsDreamVacation);
            writer.close();
            textPanel.saveToFileText();
        } catch (FileNotFoundException e) {
            textPanel.unableToSaveFile();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    //EFFECTS: sets the text on the text panel of the program according to the textEmitted from TopPanel class
    // upon pressing the StartButton

    public void handleText() {
        this.topPanel.toolBarOnHello(new TextListener() {
            public void stringInText(String text) {
                textPanel.getterForHandleText(text);
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: adds destination typed in the Dream Vacation text field to the Dream Vacation List
    public void handleAddCountryWithMenu() {
        this.menuBuilder.addCountryWithMenu(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMethodToList(dreamDestinationList);
            }
        });
    }

    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: loads the Dream Vacation list from DREAM_VACATION_TXT

    public void handleImport() {
        this.menuBuilder.onImport(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textPanel.setText("");
                    List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(DREAM_VACATION_TXT));
                    thisIsDreamVacation = dreamVacations.get(0);
                    dreamDestinationList = thisIsDreamVacation.viewDreamDestinations();

                    textPanel.loadedText(dreamDestinationList);
                    textPanel.setLoading();
                } catch (IOException | IndexOutOfBoundsException ev) {
                    dreamDestinationList = new ArrayList<>();
                    thisIsDreamVacation = new DreamVacation();
                }
            }
        });

    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // EFFECTS: to save the customized list of Dream Vacation to DREAM_VACATION_TXT file

    public void handleExport() {
        this.menuBuilder.onExport(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                saveFunction();
            }
        });
    }


    //EFFECTS: makes the form panel visible if selected on the menuItem of JCheckBoxMenuItem,
    // otherwise the form panel is not visible to the user
    public void handleShowItem() {
        this.menuBuilder.forShowItem(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
                interactivePanel.setVisible(menuItem.isSelected());
            }
        });
    }

    //EFFECTS: handles the closing of the application with exit Confirm Dialog to exit the program

    public void handleExit() {
        this.menuBuilder.onExit(new ActionListener() {
            @Override
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

}
