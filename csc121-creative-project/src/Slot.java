/* A slot */
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
        c.circle(x, y, size);
        return c;
    }
    
    void fillSlot(){
    	color = 255;
    	Mole mole = new Mole (this, false, 100);
    }
}