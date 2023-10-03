/* A slot */
import java.util.Timer;
import java.util.TimerTask;

import processing.core.PApplet;

public class Slot {
    int x, y; //position
    int size;
    boolean active; //Determine if slot is in used/queued
    int color;
    
    public Slot(int x, int y, int size, boolean active, int color) {
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.active = active;
        this.color = color;
    }
    public PApplet draw(PApplet c) {
        c.fill(color);
        c.square(x-size/2, y-size/2, size);
        return c;
    }
    
    /*Fill the slot with a mole*/
    void fillSlot(){
    	color = 255;
    	Mole mole = new Mole (this, false, 100);
    	update();
    }
    
    /*Return the slot to normal after X time*/
    public Slot update() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        	public void run() {
        		color = 150;
        		active = false;
        	}
        }, 1000);
        return this;
    }
}