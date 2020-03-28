package ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// This is the refactored class/codes from the MainFrame Class

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

    public void onExit(ActionListener actionListener) {
        exitItem.addActionListener(actionListener);
    }

    public void addCountryWithMenu(ActionListener actionListener) {
        addCountry.addActionListener(actionListener);
    }

    public void onImport(ActionListener actionListener) {
        importDataItem.addActionListener(actionListener);
    }

    public void onExport(ActionListener actionListener) {
        exportDataItem.addActionListener(actionListener);
    }

    public void forShowItem(ActionListener actionListener) {
        showFormItem.addActionListener(actionListener);
    }

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

    public void showMenuMethodItems() {
        showFormItem.setSelected(true);
        showMenu.add(showFormItem);
        secondMenu.add(showMenu);
    }

    public void showMenuBar() {
        menuBar.add(fileMenu);
        menuBar.add(secondMenu);
    }

    //EFFECTS: returns the the menu bar of the application

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
