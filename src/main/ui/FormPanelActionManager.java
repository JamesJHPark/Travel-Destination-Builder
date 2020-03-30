/*

package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FormPanelActionManager extends JPanel implements FormListener {
    private FormPanel formpanel = new FormPanel();
    private FormEvent ev;
    private FormListener formListener;

    public FormPanelActionManager() {
        submitButton();
        deleteBtnCall();
        enterBtn();
        setRemoveBtn();
        setSaveBtn();
        setLoadBtn();
    }


    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the button is clicked, and in this
    // case, the purpose of the button is to submit the typed answer to the program

    public void submitButton() {
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = FormPanel.getVacationField().getText();
                String dreamVacation = FormPanel.getDreamVacationField().getText();
                String season = FormPanel.getSeasonField().getText();
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
        formpanel.getLoadBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = FormPanel.getVacationField().getText();
                String dreamVacation = FormPanel.getDreamVacationField().getText();
                String season = FormPanel.getSeasonField().getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });

    }

    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the button is clicked, and
    // in this case, the purpose of the button is to save the Dream Vacation list

    public void setSaveBtn() {
        formpanel.getSaveBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = FormPanel.getVacationField().getText();
                String dreamVacation = FormPanel.getDreamVacationField().getText();
                String season = FormPanel.getSeasonField().getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the alt+N key is pressed

    private void enterBtn() {
        formpanel.getEnterBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = FormPanel.getVacationField().getText();
                String dreamVacation = FormPanel.getDreamVacationField().getText();
                String season = FormPanel.getSeasonField().getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListener != null) {
                    formListener.formEventOccurred(ev);

                }
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when alt+R key is pressed

    private void setRemoveBtn() {
        formpanel.getRemoveBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = FormPanel.getVacationField().getText();
                String dreamVacation = FormPanel.getDreamVacationField().getText();
                String season = FormPanel.getSeasonField().getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });

    }


    //MODIFIES: this
    //EFFECTS: creates a new event with destination, dreamVacation, and season when the button is clicked, and in this
    // case, the purpose of the button is to delete a typed country from the Dream Vacation list

    public void deleteBtnCall() {
        formpanel.getDeleteBtn().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = FormPanel.getVacationField().getText();
                String dreamVacation = FormPanel.getDreamVacationField().getText();
                String season = FormPanel.getSeasonField().getText();
                ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: FormListener for the submit JButton

    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    @Override
    public void formEventOccurred(FormEvent e) {

    }
}
*/
