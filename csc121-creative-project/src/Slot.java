/* A slot */
import java.util.Timer;

import java.util.TimerTask;

import processing.core.*;

public class Slot {
	private int x, y; //position
	private int size;
	private boolean active = false; //Determine if slot is in used/queued
	private boolean jiggling = false;
	private int color;
	private int points;
	private int grey = 150;
	private int white = 255;
	private int updateTimer = 1000;

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
		color = white;
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

	/*Event: Player clicked the slot
	 * 
	 * 0: Default state
	 * 1: Award points (correct hit)
	 * 2. Remove points (incorrect hit)
	 * 
	 * */
	public int clicked(PApplet c) {
		if (c.mouseX > x - size/2 && c.mouseY < y + size/2 &&
				c.mouseX < x + size/2 && c.mouseY > y - size/2) {
			
			if (this.active) {
				jiggle(c, false);
				Mole.playSound(Mole.getHit());
				color = grey;
				this.active = false;
				return 1;
			}
			else{
				jiggle(c, true);
				Mole.playSound(Mole.getMiss());
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
				color = grey;
				active = false;
				timer.cancel();
			}
		}, updateTimer);
		return this;

	}
	
	/* Jiggle the slot
	 * 
	 * false: Correct hit
	 * true: Incorrect hit
	 * 
	 * */
	public void jiggle(PApplet c, boolean missed) {
		int oldSize = this.size;
		this.jiggling = true;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				/*Decrease/Increase the size*/
				if (jiggling && size >= oldSize*0.8) {
					size-=5;
				}
				else {
					jiggling = false;
					size+=5;
				}
				
				/*End the timer if active or finished*/
				if (size >= oldSize || active) {
					size = oldSize;
					timer.cancel();
				}
			}
		}, 5, 5);
	}

}

