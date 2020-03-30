/*
package ui;

import model.Destination;
import model.DreamVacation;
import persistence.Reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveLoad {
    private TextPanel textPanel;

    public SaveLoad() {

    }


    public void loadingMethod(DreamVacation thisIsDreamVacation, ArrayList<Destination> dreamDestinationList) {
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
    }
}
*/
