import processing.core.*;
import processing.event.*;

/* Sound docs:
https://introcs.cs.princeton.edu/java/faq/mp3/MP3.java.html */
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

/////TODO:
/* 
 * 
 * 
 */

/**
 * Provides the scaffolding to launch a Processing application
 */
public class CircleApp extends PApplet {
    CircleWorld w;
    Player player;

    public void settings() {
        this.size(800, 800);
    }
    
    public void setup() {
        w = new CircleWorld(200, 0); 
    }
    	
    public void draw() {
        w = w.update();
        w.draw(this);
    }
    
    public void mousePressed(MouseEvent mev) {
        w = w.mousePressed(mev);
        play();
    }
    
    public void keyPressed(KeyEvent kev) {
        // w = w.keyPressed(kev);
    }
    
    
    public void play() {
        try {
            FileInputStream fis = new FileInputStream("");
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // run in new thread to play in background
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
