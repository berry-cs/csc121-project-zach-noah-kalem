import processing.core.*;
import processing.event.*;

/* Sound docs:
https://introcs.cs.princeton.edu/java/faq/mp3/MP3.java.html */
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class CircleApp extends PApplet {
    Player player;
    IStage currentStage;

    public void settings() {
        this.size(840, 840);
    }
    
    public void setup() {
        currentStage = new WelcomeStage();   // new Machine(9);
        play();
    }
    	
    public void draw() {
        currentStage = currentStage.update();
    	currentStage.draw(this);
    }
    
    public void mousePressed(MouseEvent mev) {
    	//w = w.mousePressed(mev);
    }
    
    //Currently only 2 game states, change later
    public void keyPressed(KeyEvent kev) {
    	currentStage = currentStage.keyPressed(kev);
    }
    
    //Function for playing bg music
    public void play() {
        try {
            FileInputStream fis = new FileInputStream("src/assets/palace.mp3");
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }
    

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "CircleApp" }, new CircleApp());
    }
}
