package ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

// This is the refactored class/codes from the MainFrame Class

public class MenuItemsCohesionFix {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem exportDataItem;
    private JMenuItem importDataItem;
    private JMenuItem addCountry;
    private JMenuItem exitItem;
    private JMenuItem secondMenu;
    private JMenuItem showMenu;
    private JCheckBoxMenuItem showFormItem;

    public MenuItemsCohesionFix() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Data");
        exportDataItem = new JMenuItem("Save Data");
        importDataItem = new JMenuItem("Load Data");
        addCountry = new JMenuItem("Add country (typed in Dream Vacation panel) to list");
        exitItem = new JMenuItem("Exit");
        secondMenu = new JMenu("Window");
        showFormItem = new JCheckBoxMenuItem("Vacation Form");
        showMenu = new JMenu("Launch");
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

    public void fileMenuMethods() {
        fileMenu.add(addCountry);
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
    //EFFECTS: sets up the menu bar of the application

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public JMenuItem getExportDataItem() {
        return exportDataItem;
    }

    public JMenuItem getImportDataItem() {
        return importDataItem;
    }

    public JMenuItem getAddCountry() {
        return addCountry;
    }

    public JMenuItem getExitItem() {
        return exitItem;
    }

    public JMenuItem getSecondMenu() {
        return secondMenu;
    }

    public JMenuItem getShowMenu() {
        return showMenu;
    }

    public JCheckBoxMenuItem getShowFormItem() {
        return showFormItem;
    }


    private void showItemMethod() {
    }

    private void exportMethod() {
    }

}
