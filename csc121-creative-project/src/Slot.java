/* A slot */
import java.util.Timer;
import java.util.TimerTask;

import processing.core.PApplet;

public class Slot {
    private int x, y; //position
    private int size;
    private boolean active = false; //Determine if slot is in used/queued
    private int color;
    private int points;
    
    public Slot(int x, int y, int size, boolean active, int color, int points) {
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.active = active;
        this.color = color;
        this.points = points;
    }
    public PApplet draw(PApplet c) {
        c.fill(color);
        c.square(x-size/2, y-size/2, size);
        return c;
    }
    
    /*Fill the slot with a mole*/
    void fillSlot(){
    	color = 255;
    	this.active = true;
    	update();
    }
    
    /*Event: Player clicked the slot*/
    void clicked() {
    	if (this.active) {
    		color = 255;
    		
    		this.active = false;
    	}
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