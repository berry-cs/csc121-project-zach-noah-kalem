import java.util.*;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

/* A machine containing slots */
class Machine implements IStage {
	ArrayList<Slot> slots = new ArrayList<Slot>();
	Timer timer = new Timer();
	private int slotAmount, points;
	private boolean debounce;
	int countDown = 3000;

	public Machine(int slotAmount) {
		super();
		this.slotAmount = slotAmount;
		this.points = 0;
		this.debounce = false;
	}

	public PApplet draw(PApplet c) {
		c.background(0);
		drawHUD(c);

		//Draw slots on the screen.
		for (Slot slot : slots) {
			slot.draw(c);
		}

		//Handle mouse
		if (c.mousePressed) {
			if (!debounce) {
				for (Slot slot : slots) {
					if (slot.clicked(c) == 1) {
						addPoints(slot.getPoints());
						debounce = true;
					}
					else if (slot.clicked(c) == 2) {
						addPoints(-slot.getPoints());
						debounce = true;
					}
				}
			}
		}
		else {
			debounce = false;
		}


		return c;
	}


	/**
	 * Update function doesn't do anything right now
	 */
	public IStage update() {
		this.countDown--;
		if (countDown <= 0) { 
			return new WelcomeStage(); 
		} else {
			return this;
		}
	}

	public void drawHUD(PApplet c) {
		c.fill(255);
		c.textAlign(c.LEFT);
		c.textSize(32);
		c.text("Score: "+points, 0, 840);
		c.textAlign(c.RIGHT);
		c.textSize(46);
		c.text(this.countDown/100, 840, 30);
	}

	/*Generate slots for the machine*/
	void makeSlots() {
		int curX = 210; //for now let's hard-code this to the screen size. as well as the modulo.
		int curY = 200;
		for (int i = 1; i <= slotAmount; i++) {
			if (i % 3 == 1 && i != 1) {
				curX = 210;
				curY += 200;
			}
			Slot newSlot = new Slot(curX, curY, 150, false, 150, 300);
			this.slots.add(newSlot);
			curX += 210;
		}
	}

	/* Game-mode where a random slot is chosen every 2 seconds */
	void gameRandom() {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				randomSlot().fillSlot();
			}
		}, 2000, 2000);
	}
	/*Choose a random slot from the machine to activate*/
	Slot randomSlot() {
		int chosen = (int)(Math.random() * slotAmount);
		return slots.get(chosen);
	}

	@Override
	public IStage keyPressed(KeyEvent kev) {
		if (Character.toLowerCase(kev.getKey()) == 'q') {
			return new WelcomeStage();
		} else {
			return this;
		}
	}

	/**Add points to the player's score**/
	public void addPoints(int num) {
		this.points += num;
	}
	/**
	 * Produces an updated world with the position of the
	 * drop updated to the location of the mouse press.
	 */
	public CircleWorld mousePressed(MouseEvent mev) {
		return new CircleWorld(mev.getX(), mev.getY());
	}

}
