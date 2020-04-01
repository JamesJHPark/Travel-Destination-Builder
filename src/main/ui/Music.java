package ui;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

//Represents the Music class that contains methods to play sounds from wav. files stored in data folder of the
//project and this is the refactored class/codes from the MainFrame Class

public class Music {

    //EFFECTS: constructor of music class

    public Music() {
    }

    //REFERENCE: code taken from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //REFERENCE: code taken from https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
    //REFERENCE: music from https://www.pacdv.com/sounds/interface_sound_effects/sound108.wav
    //EFFECTS: to play sound of javadeletesound.wav file

    public void playDeleteSound() {
        try {
            File soundFile = new File("./data/javadeletesound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    //REFERENCE: code taken from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //REFERENCE: code taken from https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
    //REFERENCE: music from https://www.pacdv.com/sounds/interface_sound_effects/sound106.wav
    //EFFECTS: to play sound of addSound.wav file

    public void playAddSound() {
        try {
            File soundFile = new File("./data/addSound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
