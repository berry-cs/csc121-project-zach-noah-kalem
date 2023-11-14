import java.util.*;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import ddf.minim.AudioSample;
import ddf.minim.Minim;

/* A machine containing slots */
class Machine implements IStage {
	private ArrayList<Slot> slots = new ArrayList<Slot>();
	private Timer timer = new Timer();
	private int slotAmount, points;
	private boolean debounce;
	private int countDown = 1;
	private boolean incorrect= false;
	private String message = "You're Trash";
	private int difficulty;
	private int hitWindow;
	private int eachScore;

	public Machine(int slotAmount, int difficulty) {
		super();
		this.slotAmount = slotAmount;
		this.difficulty = difficulty;
		this.points = 0;
		this.debounce = false;
		this.hitWindow = 2000/difficulty;
		this.eachScore = 150*difficulty;
	}

	public PApplet draw(PApplet c) {
		c.background(0);
		
		//Draw slots on the screen.
		for (Slot slot : slots) {
			slot.draw(c);
		}
		
		//Handle mouse
		if (c.mousePressed) {
			if (!debounce) {
				slotHitDetection(c);
			}
		}
		else {
			debounce = false;
		}

		drawHUD(c);
		return c;
	}


	/**
	 * Check if the Machine has ended
	 */
	public IStage update() {
		this.countDown--;
		if (countDown <= 0) { 
			return new EndStage(getPoints(), difficulty); 
		} else {
			return this;
		}
	}
	
	/**
	 * Draw the hud (score, timer, etc.)
	 */
	public void drawHUD(PApplet c) {
		c.fill(255);
		c.textAlign(c.LEFT);
		c.textSize(32);
		c.text("Score: "+points, 0, Mole.height);
		c.textAlign(c.RIGHT);
		c.textSize(46);
		c.text(this.countDown/100, Mole.width, Mole.height / 28 );
		c.textAlign(c.CENTER);
		if (incorrect)

			c.text(message, Mole.width/2 , Mole.height/ (84 / 10));

	}

	/**Handle hit detection for slots.**/
	public void slotHitDetection(PApplet c) {
		for (Slot slot : slots) {
			if (slot.clicked(c) == 1) {
				addPoints(slot.getPoints());
				debounce = true;
			}
			else if (slot.clicked(c) == 2) {
				addPoints(-slot.getPoints());
				debounce = true;
				incorrect = true;
				displayMessage();
			}
		}
	}

	/** Generate slots for the machine **/
	void makeSlots() {
		/*
		 * Based on these values:
		 * Width = 840
		 * Height = 840
		 * Slots = 9
		 * Size = 150
		 */
		int curX = Mole.width/4;
		int curY = Mole.height/(42/10);
		for (int i = 1; i <= slotAmount; i++) {
			if (i % 3 == 1 && i != 1) {
				curX = Mole.width/4;
				curY += Mole.height/(42/10);
			}
			Slot newSlot = new Slot(curX, curY, Mole.width/(56/10), hitWindow, Mole.grey, eachScore);
			this.slots.add(newSlot);
			curX += Mole.width/4;
		}
	}

	/** Game-mode where a random slot is chosen every X seconds **/
	void gameRandom() {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				randomSlot().fillSlot();
			}
		}, hitWindow, hitWindow);
	}
	
	/** Choose a random slot from the machine to activate **/
	public Slot randomSlot() {
		int chosen = (int)(Math.random() * slotAmount);
		if (slots.get(chosen).getActive()) { //prevent pick if already active
			randomSlot();
		}
		return slots.get(chosen);
	}
	
	/** Return points **/
	public int getPoints() {
		return this.points;
	}
	
	/** Return a slot **/
	public Slot getSlot(int slotNum) {
		return this.slots.get(slotNum);
	}

	/**Add points to the player's score**/
	public int addPoints(int num) {
		this.points += num;
		return getPoints();
	}
	
	/** Show the miss message when clicked on wrong square **/
	public void displayMessage() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				incorrect = false;
				timer.cancel();
			}
		}, 100);

	}
	
	@Override
	/** Keypress func 
	 * 
	 * Q to quit game
	 * 
	 * **/
	public IStage keyPressed(KeyEvent kev) {
		if (Character.toLowerCase(kev.getKey()) == 'q') {
			return new WelcomeStage();
		} else {
			return this;
		}
	}
}
