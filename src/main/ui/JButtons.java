/*
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class JButtons {
    private JButton submitBtn;
    private JButton deleteBtn;
    private JButton enterBtn;
    private JButton removeBtn;
    private JButton saveBtn;
    private JButton loadBtn;

    public JButtons() {
        submitBtn = new JButton("SUBMIT");
        deleteBtn = new JButton("ADD button");
        enterBtn = new JButton("Alt+N to Add");
        removeBtn = new JButton("Alt+R to Remove");
        saveBtn = new JButton("SAVE LIST");
        loadBtn = new JButton("LOAD LIST");
        GridBagConstraints gc = new GridBagConstraints();
    }


    //EFFECTS: sets up the JButtons for submit and delete on the screen of the form panel of the program

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

     enterBtn.setMnemonic(KeyEvent.VK_N);
    removeBtn.setMnemonic(KeyEvent.VK_R);

}
*/
