import java.util.*;
import processing.core.PApplet;

/* A page containing slots */
class Machine {
	ArrayList<Slot> slots = new ArrayList<Slot>();
	int slotAmount;
	
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
	
	/*Generate slots for the machine*/
	void makeSlots() {
		int curX = 210; //for now let's hardcode this to the screen size. as well as the modulo.
		int curY = 200;
		for (int i = 1; i <= slotAmount; i++) {
			if (i % 3 == 1 && i != 1) {
				curX = 210;
				curY += 200;
			}
			Slot newSlot = new Slot(curX, curY, 150, false);
			this.slots.add(newSlot);
			curX += 210;
		}
		System.out.println(slots);
		
	}
	
	/*Choose a random slot from the machine to create a mole*/
	Slot randomSlot() {
		return slots.get((int)Math.random() * slotAmount);
	}
}
