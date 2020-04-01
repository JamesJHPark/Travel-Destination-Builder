package ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


//REFERENCE: the class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s
// This is the refactored class/codes from the MainFrame Class

//Represents the class of MenuBuilder that create JMenu, JMenuBar, JCheckBoxMenuItem, and JMenuItems
public class MenuBuilder {
    private JMenu fileMenu;
    private JMenuBar menuBar;
    private JMenuItem exportDataItem;
    private JMenuItem importDataItem;
    private JMenuItem addCountry;
    private JMenuItem exitItem;
    private JMenuItem secondMenu;
    private JMenuItem showMenu;
    private JCheckBoxMenuItem showFormItem;

    //EFFECTS: constructs the MenuBuilder with JMenu, JMenuBar, JCheckBoxMenuItem, and JMenuItem
    public MenuBuilder() {
        fileMenu = new JMenu("Data");
        secondMenu = new JMenu("Window");
        showMenu = new JMenu("Launch");
        menuBar = new JMenuBar();
        exportDataItem = new JMenuItem("Save Data");
        importDataItem = new JMenuItem("Load Data");
        addCountry = new JMenuItem("Add country (typed in Dream Vacation panel) to list");
        exitItem = new JMenuItem("Exit");
        showFormItem = new JCheckBoxMenuItem("Vacation Form");
    }

    //EFFECTS: adds actionListener with exitItem JMenuItem
    public void onExit(ActionListener actionListener) {
        exitItem.addActionListener(actionListener);
    }

    //EFFECTS: adds actionListener with addCountry JMenuItem
    public void addCountryWithMenu(ActionListener actionListener) {
        addCountry.addActionListener(actionListener);
    }

    //EFFECTS: adds actionListener with onImport JMenuItem

    public void onImport(ActionListener actionListener) {
        importDataItem.addActionListener(actionListener);
    }

    //EFFECTS: adds actionListener with onExport JMenuItem

    public void onExport(ActionListener actionListener) {
        exportDataItem.addActionListener(actionListener);
    }

    //EFFECTS: adds actionListener with forShowItem JMenuItem

    public void forShowItem(ActionListener actionListener) {
        showFormItem.addActionListener(actionListener);
    }

    //MODIFIES: this
    //EFFECTS: adds the JMenuItem objects of addCountry, exportDataItem, importDataItem, exitItem to the Data Jmenu
    public void fileMenuMethods() {
        fileMenu.add(addCountry);
        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        showMenuMethodItems();
        showMenuBar();
        fileMenu.setMnemonic(KeyEvent.VK_D);
        exitItem.setMnemonic(KeyEvent.VK_E);
    }
    //MODIFIES: this
    //EFFECTS: adds the JCheckBoxMenuItem and Launch JMenu to the WindowMenu JMenu

    public void showMenuMethodItems() {
        showFormItem.setSelected(true);
        showMenu.add(showFormItem);
        secondMenu.add(showMenu);
    }

    //MODIFIES: this
    //EFFECTS: adds the fileMenu and secondMenu to the menuBar JMenuBar

    public void showMenuBar() {
        menuBar.add(fileMenu);
        menuBar.add(secondMenu);
    }

    //EFFECTS: returns the the menu bar of the application

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
