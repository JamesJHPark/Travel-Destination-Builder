/*
package ui;

import exceptions.IllegalDestinationException;
import model.Destination;
import model.DestinationsManager;
import model.DreamVacation;

import java.util.ArrayList;

public class MainFrameExperiment {
    private FormPanel formPanel;
    private String season;
    private TextPanel textPanel;
    private DestinationsManager winterDestinations;
    private DestinationsManager summerDestinations;
    private DreamVacation thisIsDreamVacation;
    private ArrayList<Destination> dreamDestinationList;

    public MainFrameExperiment() {
        initialize();
        submitMethod();
    }

    public void initialize() {
        formPanel = new FormPanel();
        textPanel = new TextPanel();
        thisIsDreamVacation = new DreamVacation();
        dreamDestinationList = new ArrayList<>();

    }

    public void submitMethod() {
        formPanel.setFormListener(new FormListener() {
            public void formEventOccurred(FormEvent e) {
                String countryName = e.getDestination();
                season = FormPanel.getSeasonField().getText();
                helperMethod(FormPanel.getVacationField().getText());
                System.out.println("df");
            }
        });
    }


    public void helperMethod(String countryName) {
        Destination destination = new Destination(countryName);
        if (season.equalsIgnoreCase("exit")) {
            textPanel.setText("Goodbye!");
        } else if (season.equalsIgnoreCase("Summer")) {
            summerCall(countryName, destination);
            thisIsDreamVacation = new DreamVacation();
            dreamDestinationList = new ArrayList<>();
        } else if (season.equalsIgnoreCase("Winter")) {
            winterCall(countryName, destination);
            thisIsDreamVacation = new DreamVacation();
            dreamDestinationList = new ArrayList<>();
        }
    }



    public void summerCall(String countryName, Destination destination) {
        textPanel.showCountriesWithSummerSeason(summerDestinations);
        if (countryName.length() > 0) {
            try {
                summerDestinations.getCityFromSummerDestinations(destination);
                textPanel.popularCitiesRetrieve(countryName);
                textPanel.appendText(summerDestinations.getCityFromSummerDestinations(destination));
                textPanel.textForDreamVacation();
            } catch (IllegalDestinationException e) {
                textPanel.textForChoosingRight();
            }
        }

    }


    public void winterCall(String countryName, Destination destination) {
        textPanel.showCountriesWithWinterSeason(winterDestinations);
        if (countryName.length() > 0) {
            try {
                winterDestinations.getCityFromWinterDestinations(destination);
                textPanel.popularCitiesRetrieve(countryName);
                textPanel.appendText(winterDestinations.getCityFromWinterDestinations(destination));
                textPanel.textForDreamVacation();
            } catch (IllegalDestinationException e) {
                textPanel.textForChoosingRight();
            }
        }
    }

}
*/

