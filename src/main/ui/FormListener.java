package ui;

import java.util.EventListener;

//REFERENCE: class codes referenced/taken from https://www.youtube.com/watch?v=WRwPVZ4jmNY&t=1211s


//Represents FormListener interface of the program
public interface FormListener extends EventListener {

    //EFFECTS: forms the event generated by JButtons of FormPanel class

    void formEventOccurred(FormEvent e);

}
