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

public class FormPanel extends JPanel {

    private JLabel destinationLabel;
    private JLabel dreamVacationLabel;
    private JLabel seasonLabel;
    protected JTextField destinationField;
    protected JTextField dreamVacationField;
    protected JTextField seasonField;
    private JButton okBtn;
    private FormListener formListener;
    private FormListener1 formListener1;
    private FormListener2 formListener2;
    private FormListener3 formListener3;
    private FormListenerSave formListenerSave;
    private FormListenerLoad formListenerLoad;
    private JButton deleteBtn;
    private JButton enterBtn;
    private JButton removeBtn;
    private JButton saveBtn;
    private JButton loadBtn;
    private BufferedImage image;


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

        try {
            image = ImageIO.read(new File("C:\\Users\\James\\Desktop\\123.JPG"));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, -300, -500, this); // see javadoc for more info on the parameters
    }


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


    private void enterBtn() {
        enterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                FormEvent ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListener2 != null) {
                    formListener2.formEventOccurred2(ev);

                }
            }
        });
    }

    private void setRemoveBtn() {
        removeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                FormEvent ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListener3 != null) {
                    formListener3.formEventOccurred3(ev);

                }
            }
        });

    }


    public void deleteBtnCall() {
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String destination = destinationField.getText();
                String dreamVacation = dreamVacationField.getText();
                String season = seasonField.getText();
                FormEvent ev = new FormEvent(this, destination, dreamVacation, season);
                if (formListener1 != null) {
                    formListener1.formEventOccurred1(ev);
                }
            }
        });
    }

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

    public void setTwoButtons(GridBagConstraints gc) {

        ///// FOURTH ROW /////
        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridy = 3;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(okBtn, gc);

        ///// FOURTH ROW DELETE BUTTON/////
        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridy = 3;
        gc.gridx = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(deleteBtn, gc);

    }

    public void setEnterBtn(GridBagConstraints gc) {
        gc.weightx = 10;
        gc.weighty = 10;
        gc.gridy = 4;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(enterBtn, gc);
    }

    public void removeBtn(GridBagConstraints gc) {
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridy = 4;
        gc.gridx = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(removeBtn, gc);
    }


    public void saveBtn(GridBagConstraints gc) {
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridy = 5;
        gc.gridx = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(saveBtn, gc);
    }

    public void loadBtn(GridBagConstraints gc) {
        gc.weightx = 1;
        gc.weighty = 2;
        gc.gridy = 5;
        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        add(loadBtn, gc);
    }


    public void setFormListener(FormListener listener) {
        this.formListener = listener;
    }

    public void setFormListenerKey(FormListener listener) {
        this.formListener = listener;
    }

    public void setFormListenerDelete(FormListener1 listener) {

        this.formListener1 = listener;
    }

    public void setFormListenerEnter(FormListener2 listener) {
        this.formListener2 = listener;
    }

    public void setFormListenerRemove(FormListener3 listener) {
        this.formListener3 = listener;
    }

    public void setFormListenerSave(FormListenerSave listener) {
        this.formListenerSave = listener;
    }


    public void setFormListenerLoad(FormListenerLoad listener) {
        this.formListenerLoad = listener;
    }


    public void setPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 350;
        setPreferredSize(dim);
        destinationLabel = new JLabel("Destination: ");
        dreamVacationLabel = new JLabel("Dream Vacation: ");
        seasonLabel = new JLabel("Season: ");
        destinationField = new JTextField(10);
        dreamVacationField = new JTextField(10);
        seasonField = new JTextField(10);
        okBtn = new JButton("SUBMIT");
        deleteBtn = new JButton("DELETE");
        enterBtn = new JButton("Alt+N adds to list");
        removeBtn = new JButton("Alt+R removes from list");
        saveBtn = new JButton("SAVE");
        loadBtn = new JButton("LOAD");
        enterBtn.setMnemonic(KeyEvent.VK_N);
        removeBtn.setMnemonic(KeyEvent.VK_R);
    }


}
