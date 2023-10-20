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
    
    /* Get this slot's edges position */
    public int[] getPosition() {
    	int left = x - size/2;
    	int right = x + size/2;
    	int top = y - size/2;
    	int bottom = y + size/2;
    	int[] arr = {left, right, top, bottom};
    	return arr;
    }
    
    /*Event: Player clicked the slot*/
    public int clicked(PApplet c) {
    	if (
    		c.mouseX > x - size/2 && c.mouseY < y + size/2 &&
    		c.mouseX < x + size/2 && c.mouseY > y - size/2) {
    		
    		if (this.active) {
    			color = 150;
    			this.active = false;
    			return 1;
    		}
    		else{
    			return 2;
    		}
    	}
    	return 0;
    }
    
    public int getPoints() {
    	return this.points;
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

