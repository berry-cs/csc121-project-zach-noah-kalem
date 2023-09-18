/* A slot */
import processing.core.PApplet;

public class Slot {
	int x, y; //position
	int size;
	boolean active; //Determine if slot is in used/queued
	
	public Slot(int x, int y, int size, boolean active) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.active = active;
	}
	
	public PApplet draw(PApplet c) {
		c.circle(x, y, size);
        return c;
    }
}
