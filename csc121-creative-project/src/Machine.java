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
	private int countDown = 3000;
	private boolean incorrect= false;
	private String message = "You're Trash";

	public Machine(int slotAmount) {
		super();
		this.slotAmount = slotAmount;
		this.points = 0;
		this.debounce = false;
	}

	public PApplet draw(PApplet c) {
		c.background(0);
		
		


		//Draw slots on the screen.
		for (Slot slot : slots) {
			slot.draw(c);
		}
		drawHUD(c);
		//Handle mouse
		if (c.mousePressed) {
			if (!debounce) {
				slotHitDetection(c);
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
		c.text("Score: "+points, 0, Mole.getHeight());
		c.textAlign(c.RIGHT);
		c.textSize(46);
		c.text(this.countDown/100, Mole.getWidth(), Mole.getHeight() / 28 );
		c.textAlign(c.CENTER);
		if (incorrect)

			c.text(message, Mole.getWidth()/2 , Mole.getHeight()/ (84 / 10));

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

	/*Generate slots for the machine*/
	void makeSlots() {
		/*
		 * Width = 840
		 * Height = 840
		 * Slots = 9
		 * Size = 150
		 * 
		 * 
		 */
		int curX = Mole.getWidth()/4;
		int curY = Mole.getHeight()/(42/10);
		for (int i = 1; i <= slotAmount; i++) {
			System.out.print(i);
			if (i % 3 == 1 && i != 1) {
				System.out.print("Pudding");
				curX = Mole.getWidth()/4;
				curY += Mole.getHeight()/(42/10);
			}
			Slot newSlot = new Slot(curX, curY, Mole.getWidth()/(56/10), false, 150, 300);
			this.slots.add(newSlot);
			curX += Mole.getWidth()/4;
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

	public void displayMessage() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				incorrect = false;
				timer.cancel();
			}
		}, 100);

	}
}
