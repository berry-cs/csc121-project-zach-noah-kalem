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
    int state = 0;
    

    public void settings() {
        this.size(800, 800);
    }
    
    public void setup() {
        w = new CircleWorld(200, 0); 
        play();
    }
    	
    public void draw() {
    	this.background(0);
    	
    	//Switch case to allow easy control over menus
    	switch(state) {
	    	case 0: //default state
		        background(0);
		        textSize(32);
		        textAlign(CENTER);
		        text("Whack-a-deer", 400, 60);
		        textSize(24);
		        text("Press any key to start", 400, 130);      
		        break;
	    	case 1: //gameplay
	    		w = w.update();
	            w.draw(this); 
		        break;
		}
    }
    
    public void mousePressed(MouseEvent mev) {
    	w = w.mousePressed(mev);
        
    }
    
    //Currently only 2 game states, change later
    public void keyPressed(KeyEvent kev) {
    	state = 1;
        // w = w.keyPressed(kev);
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
