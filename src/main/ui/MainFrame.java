package ui;

import model.Destination;
import model.Destinations;
import model.DreamVacation;
import persistence.Reader;
import persistence.Writer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
public class MainFrame extends JFrame  {
    protected TextPanel textPanel;
    private Toolbar toolbar;
    protected FormPanel formPanel;
    private Destinations destinationsW;
    private Destinations destinations;
    private String d1;
    private String season;
    private String dreamVacation;
    private static final String DREAM_VACATION_TXT = "./data/DreamVacation.txt";
    private DreamVacation createDreamDestinations = new DreamVacation();
    ArrayList<String> fixedList = new ArrayList<>();
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Data");
    JMenuItem exportDataItem = new JMenuItem("Save Data");
    JMenuItem importDataItem = new JMenuItem("Load Data");
    JMenuItem removeCountry = new JMenuItem("Remove the Most Recent Country Added");
    JMenuItem addCountry = new JMenuItem("Add the Most Recent Country Added Again");
    JMenuItem exitItem = new JMenuItem("Exit");
    JMenu secondMenu = new JMenu("Window");
    JMenu showMenu = new JMenu("Launch");
    JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Vacation Form");



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

    public void submitMethod() {

        formPanel.setFormListener(new FormListener() {
            public void formEventOccurred(FormEvent e) {
                d1 = e.getDestination();
                dreamVacation = e.getDreamVacation();
                season = e.getSeason();
                helperMethod(d1);
                Destination destination = new Destination(dreamVacation);
                createDreamDestinations.addDreamDestinations(destination);
                playSound();
            }
        });
    }

    public void deleteMethod() {

        formPanel.setFormListenerDelete(new FormListenerDelete() {
            public void formEventOccurred1(FormEvent e) {

                dreamVacation = e.getDreamVacation();
                Destination removeVacacy = new Destination(dreamVacation);
                createDreamDestinations.removeDreamDestinations(removeVacacy);
                textPanel.removeText(dreamVacation);
                playSound();
            }
        });

    }

    public void removeKey() {
        formPanel.setFormListenerRemove(new ListenerRemove() {
            public void formEventOccurred3(FormEvent e) {
                dreamVacation = e.getDreamVacation();
                Destination removeVacacy = new Destination(dreamVacation);
                textPanel.replaceSelection();
                createDreamDestinations.removeDreamDestinations(removeVacacy);
                textPanel.removeText(dreamVacation);
            }
        });
    }

    public void enterKey() {
        formPanel.setFormListenerEnter(new ListenerEnterKey() {
            public void formEventOccurred2(FormEvent e) {
                d1 = e.getDestination();
                dreamVacation = e.getDreamVacation();
                season = e.getSeason();
                helperMethod(d1);
                Destination destination = new Destination(dreamVacation);
                createDreamDestinations.addDreamDestinations(destination);

            }
        });
    }

    public void saveMethod() {
        formPanel.setFormListenerSave(new FormListenerSave() {
            public void formEventSave(FormEvent ev) {
                d1 = ev.getDestination();
                dreamVacation = ev.getDreamVacation();
                saveFunction();
            }
        });
    }

    public void loadMethod() {
        formPanel.setFormListenerLoad(new FormListenerLoad() {
            public void formEventLoad(FormEvent ev) {
                d1 = ev.getDestination();
                dreamVacation = ev.getDreamVacation();
                try {
                    textPanel.setText("");
                    List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(DREAM_VACATION_TXT));
                    createDreamDestinations = dreamVacations.get(0);
                    fixedList = createDreamDestinations.getDestinationObject();
                    textPanel.appendText(fixedList.toString());
                } catch (IOException | IndexOutOfBoundsException e) {
                    destinations = new Destinations();
                    createDreamDestinations = new DreamVacation();
                }
            }
        });
    }




    //REFERENCE: code taken from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //REFERENCE: code taken from https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
    //REFERENCE: music from https://www.pacdv.com/sounds/interface_sound_effects/sound106.wav

    public void playSound() {
        try {
            URL url = new URL("https://www.pacdv.com/sounds/interface_sound_effects/sound106.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void saveFunction() {
        try {
            Writer writer = new Writer(new File(DREAM_VACATION_TXT));
            writer.write(createDreamDestinations);
            writer.close();
            textPanel.appendText("\nYour customized dream vacation list has been saved. "
                    + "\nTo exit the program, please click on data menu and then click exit. Thank you!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save Dream Vacation to " + DREAM_VACATION_TXT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

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


    public void selectVacation(String dreamVacation) {
        if (dreamVacation.length() > 0) {
            textPanel.setText("");
            textPanel.appendText("Now, let's create your customized Dream Vacation List for your future travels!\n");
            textPanel.addOnText(dreamVacation);

        }
    }

    public void setPanel() {
        JTextField component = new JTextField();
        JFrame f = new JFrame();
        f.add(component);
        f.setLayout(new BorderLayout());
        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        f.add(formPanel, BorderLayout.WEST);
        f.add(toolbar, BorderLayout.NORTH);
        f.add(textPanel, BorderLayout.CENTER);
        f.setSize(1000, 950);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setJMenuBar(createMenuBar());

    }




    private JMenuBar createMenuBar() {
        fileMenuMethods();
        exitMethod();
        importMethod();
        exportMethod();
        showItemMethod();

        addCountry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                        FormEvent recentCountry = new FormEvent(this, season, d1, dreamVacation);
                        String d2 = recentCountry.getDreamVacation();
                        helperMethod(d2);
                    }
                });

        removeCountry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textPanel.removeText(dreamVacation);
            }
        });
        return menuBar;
    }

    public void fileMenuMethods() {
        fileMenu.add(addCountry);
        fileMenu.add(removeCountry);
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

    public void importMethod() {
        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    textPanel.setText("");
                    List<DreamVacation> dreamVacations = Reader.readDreamVacations(new File(DREAM_VACATION_TXT));
                    createDreamDestinations = dreamVacations.get(0);
                    fixedList = createDreamDestinations.getDestinationObject();
                    textPanel.appendText(fixedList.toString());
                } catch (IOException | IndexOutOfBoundsException e) {
                    destinations = new Destinations();
                    createDreamDestinations = new DreamVacation();
                }

            }
        });
    }

    public void exportMethod() {
        exportDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFunction();
            }
        });
    }

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

