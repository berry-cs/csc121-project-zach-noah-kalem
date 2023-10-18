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
    public boolean clicked(PApplet c) {
    	if (this.active && c.mouseX > x - size/2 && c.mouseY < y + size/2 && c.mouseX < x + size/2 && c.mouseY > y - size/2  && c.mousePressed) {
    		color = 150;
    		this.active = false;
    		return true;
    	}
    	return false;
    }
    
    public int getPoints() {
    	return -this.points;
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