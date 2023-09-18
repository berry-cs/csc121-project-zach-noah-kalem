import processing.core.*;
import processing.event.*;

//Minim doc: https://code.compartmental.net/minim/
//import ddf.minim.*;

//Sound: https://docs.oracle.com/javase/tutorial/sound/sampled-overview.html
//https://docs.oracle.com/javase/8/docs/technotes/guides/sound/programmer_guide/contents.html
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/////TODO:
/* 1. Create a page with Soundbutton
 * 2. Place the Soundbuttons into array or smth
 * 3. Save program state with JSON or other file method
 */

/**
 * Provides the scaffolding to launch a Processing application
 */
public class CircleApp extends PApplet {
    CircleWorld w;

    Soundbutton testButton = new Soundbutton("assets/sonido.mp3", new ImageFile("",1,1));
    
    public void settings() {
        this.size(400, 400);
    }
    
    public void setup() {
        w = new CircleWorld(200, 0);
        //g.play();
    }
    
    public void draw() {
        w = w.update();
        w.draw(this);
    }
    
    public void mousePressed(MouseEvent mev) {
        w = w.mousePressed(mev);
    }
    
    public void keyPressed(KeyEvent kev) {
        // w = w.keyPressed(kev);
    }

    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "CircleApp" }, new CircleApp());
    }
}
