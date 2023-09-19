import java.util.*;
import processing.core.PApplet;

/* A page containing slots */
class Machine {
	ArrayList<Slot> slots = new ArrayList<Slot>();
	int slotAmount;
	Timer timer = new Timer();
	
	public Machine(int slotAmount) {
		super();
		this.slotAmount = slotAmount;
	}
	
	public PApplet draw(PApplet c) {
		for (Slot x : slots) {
			x.draw(c);
		}
        return c;
    }
	
	/**
     * Update function doesn't do anything right now
     */
    public Machine update() {
        return this;
    }
	
	/*Generate slots for the machine*/
	void makeSlots() {
		int curX = 210; //for now let's hardcode this to the screen size. as well as the modulo.
		int curY = 200;
		for (int i = 1; i <= slotAmount; i++) {
			if (i % 3 == 1 && i != 1) {
				curX = 210;
				curY += 200;
			}
			Slot newSlot = new Slot(curX, curY, 150, false, 150);
			this.slots.add(newSlot);
			curX += 210;
		}
	}
	
	/* Gamemode where a random slot is chosen every 2 seconds */
	void gameRandom() {
        timer.scheduleAtFixedRate(new TimerTask() {
        	public void run() {
        		randomSlot().fillSlot();
        	}
        }, 2000, 2000);
	}
	/*Choose a random slot from the machine to create a mole*/
	Slot randomSlot() {
		int chosen = (int)(Math.random() * slotAmount);
		return slots.get(chosen);
	}
	
	
}
