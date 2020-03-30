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
    private Toolbar toolbar;
    private JFrame frame;
    private FormPanel formPanel;
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

    //MODIFIES: this
    //EFFECTS: sets up the frame, and initializes the toolbar, textPanel, formPanel of the program
    public void setPanels() {
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
    //EFFECTS: sets up the JMenu bar of the application

    private JMenuBar createMenuBar() {
        menuBuilder.fileMenuMethods();
        handleExit();
        handleImport();
        handleExport();
        handleShowItem();
        handleAddCountryWithMenu();
        return menuBuilder.getMenuBar();
    }

    //MODIFIES: this
    //EFFECTS: methods that submit user entries to the FormPanel, add, remove Destinations from the DreamVacation and
    // dreamDestinationList, as well as saving/loading the DreamVacation List for the user to view.

    private void buildingDreamVacation() {
        submitMethod();
        addMethod();
        removeKey();
        enterKey();
        saveMethod();
        loadMethod();
        handleText();
    }

    //MODIFIES: this
    //EFFECTS: submits a typed country into Destination panel, Dream Vacation panel,
    // or season in the text field panels according to the program specifications

    public void submitMethod() {
        formPanel.setFormListener(new FormListener() {
            public void formEventOccurred(FormEvent e) {
                String countryName = e.getDestination();
                season = e.getSeason();
                chooseSeasonMethod(e.getDestination());
            }
        });
    }


    //EFFECTS: helper method to create destination object with countryName and passes in the parameters of
    // String countryName, Destination destination, and DestinationsManager destinationsManager into the
    // testSummerCall, testWinterCall within TextPanel, and creates a new object of thisIsDreamVacation
    // and dreamDestinationList every time the method is called.


    public void chooseSeasonMethod(String countryName) {
        Destination destination = new Destination(countryName);
        if (season.equalsIgnoreCase("Summer")) {
            textPanel.testSummerCall(countryName, destination, destinationsManager);
            thisIsDreamVacation = new DreamVacation();
            dreamDestinationList = new ArrayList<>();
        } else if (season.equalsIgnoreCase("Winter")) {
            textPanel.testWinterCall(countryName, destination, destinationsManager);
            thisIsDreamVacation = new DreamVacation();
            dreamDestinationList = new ArrayList<>();
        }
    }



    //MODIFIES: this
    //EFFECTS: adds Destination typed in the text field panel to the Dream Vacation List
    // according to the program specifications

    public void addMethod() {
        formPanel.setFormListenerAdd(new FormListenerAdd() {
            public void formEventAdd(FormEvent e) {
                addMethodToList(dreamDestinationList);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: adds a typed country in the text panel of Dream Vacation with Alt+E or by user clicking on this button
    // to Dream Vacation List

    public void enterKey() {
        formPanel.setFormListenerEnter(new FormListenerEnter() {
            public void formEventOccurredEnter(FormEvent e) {
                addMethodToList(dreamDestinationList);
            }
        });
    }

    public void addMethodToList(ArrayList<Destination> dreamDestinationList) {
        music.playAddSound();
        Destination destination = new Destination(FormPanel.getDreamVacationField().getText());
        if (destination.getDestinationCountryName().length() >= 1) {
            thisIsDreamVacation.addDreamDestinations(destination);
        }
        if (!dreamDestinationList.contains(destination)
                && destination.getDestinationCountryName().length() >= 1) {
            dreamDestinationList.add(destination);
        }
        textPanel.buildingDreamVacation(dreamDestinationList);
    }


    //MODIFIES: this
    //EFFECTS: removes a typed country with Alt+R in the text field panel from Dream Vacation List
    // according to the program specifications

    public void removeKey() {
        formPanel.setFormListenerRemove(new FormListenerRemove() {
            public void formEventOccurredRemove(FormEvent e) {
                music.playDeleteSound();
                Destination destination = new Destination(FormPanel.getDreamVacationField().getText());
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
        formPanel.setFormListenerSave(new FormListenerSave() {
            public void formEventSave(FormEvent e) {
                saveFunction();
            }
        });
    }


    // REFERENCE: code taken from URL: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    // MODIFIES: this
    // EFFECTS: loads the Dream Vacation list from DREAM_VACATION_TXT

    public void loadMethod() {
        formPanel.setFormListenerLoad(new FormListenerLoad() {
            public void formEventLoad(FormEvent e) {
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
            textPanel.appendText("Unable to save Dream Vacation to " + DREAM_VACATION_TXT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }



    //EFFECTS: sets the text on the text panel of the program according to the textEmitted from Toolbar class

    public void handleText() {
        this.toolbar.toolBarOnHello(new StringListener() {
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


    //EFFECTS: makes the form panel visible if selected, otherwise the form panel is not visible to the user
    public void handleShowItem() {
        this.menuBuilder.forShowItem(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });
    }


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


/* public void textForChoosingRight() {
        textPanel.appendText(
                "\n\n"
                        + FormPanel.getVacationField().getText()
                        + " is an invalid selection! Please select 1 country from the above list\n\n.");
    }*/


/*
        textPanel.setText("Build your Dream Vacation List!"
                + "\nType country name "
                + "into Dream Vacation Panel"
                + "\n" + dreamDestinationList
                + "\n"
                + "\nPlease click on SAVE LIST button when you are finished."
                + "\n"
                + "\n***Please note: You can only enter a country once***");
    }*/

//EFFECTS: sets the season with user's response of Summer and provides the user with
// list of summer DestinationsManager to choose from and shows the list of the corresponding cities of
// summer Destination that the user has chosen
/*

    public void summerCall(String countryName, Destination destination) {
        textPanel.testSummerCall(countryName, destination, destinationsManager);
*/

 /*       textPanel.showCountriesWithSummerSeason(destinationsManager);
        if (countryName.length() > 0) {
            try {
                destinationsManager.getCityFromSummerDestinations(destination);
                textPanel.popularCitiesRetrieve(countryName);
                textPanel.appendText(destinationsManager.getCityFromSummerDestinations(destination));
                textPanel.textForDreamVacation();
            } catch (IllegalDestinationException e) {
                textPanel.textForChoosingRightSummer(destinationsManager);
            }
        }
*/


//EFFECTS: sets the season with user's response of Summer and provides the user with
// list of summer DestinationsManager to choose from and shows the list of the corresponding cities of
// winter Destination that the user has chosen
/*


    public void winterCall(String countryName, Destination destination) {
        textPanel.testWinterCall(countryName, destination, destinationsManager);
    }

*/


//THESE HAVE BEEN REFACTORED - MOVED TO A NEW CLASS, MenuBuilder **
      /* menuBar = new JMenuBar();
        fileMenu = new JMenu("Data");
        exportDataItem = new JMenuItem("Save Data");
        importDataItem = new JMenuItem("Load Data");
        addCountry = new JMenuItem("Add country (typed in Dream Vacation panel) to list");
        exitItem = new JMenuItem("Exit");
        secondMenu = new JMenu("Window");
        showFormItem = new JCheckBoxMenuItem("Vacation Form");
        showMenu = new JMenu("Launch");*/