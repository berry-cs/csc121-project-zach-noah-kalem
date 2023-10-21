import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class WelcomeStage implements IStage {
	private boolean debounce;
	private boolean setup = true;
	private int difficulty = 1;
	private String difficultyText = "";
	private String difficultyMessage = "";
	private ArrayList<Button> buttons;
	private Button difficultyUp, difficultyDown;

	public void setup() {
		buttons = new ArrayList<Button>(); 
		difficultyUp = new Button(Mole.width/2 + Mole.width/10, Mole.height/2 - Mole.height/20, Mole.width/10, Mole.height/10, 255, ">");
		difficultyDown = new Button(Mole.width/2 - (Mole.width/10)*2, Mole.height/2- Mole.height/20, Mole.width/10, Mole.height/10, 255, "<");
	}

	@Override
	public PApplet draw(PApplet c) {
		c.background(0);

		if (setup) {
			setup();
			buttons.add(difficultyUp);
			buttons.add(difficultyDown);
			setup = false;
		} else {
			for (Button button : buttons) {
				button.draw(c);
			}
		}
		
		drawHUD(c);

		//Handle mouse
		if (c.mousePressed) {
			if (!debounce) {
				debounce = true;
				if (difficultyUp.clicked(c)) {
					changeDifficulty(1);
				}
				else if (difficultyDown.clicked(c)) {
					changeDifficulty(-1);
				}
			}
		}
		else {
			debounce = false;
		}
		return c;
	}
	
	/** Draw the hud **/
	public void drawHUD(PApplet c) {
		c.textSize(64);
		c.textAlign(c.CENTER);
		c.text("Whack-A-Mole", Mole.width/2, Mole.height/(84/10));
		c.textSize(24);
		c.text("Press space bar to start", Mole.width/2, Mole.height/2 + (Mole.height/10)*2); 
		
		if (getDifficulty() == 1) {
			difficultyText = "Easy";
			difficultyMessage = "Wahh wahh i play on easy";
		}
		else if (getDifficulty() == 2) {
			difficultyText = "Medium";
			difficultyMessage = "Whack some moles!";
		}
		else if (getDifficulty() == 3) {
			difficultyText = "Hard";
			difficultyMessage = "Whack a mole but faster!";
		}
		else if (getDifficulty() == 4) {
			difficultyText = "Insane";
			difficultyMessage = "The only fun way to play the game!";
		}
		
		c.textSize(42);
		c.text(difficultyText, Mole.width/2, Mole.height/2);
		c.textSize(32);
		c.text(difficultyMessage, Mole.width/2, Mole.height/2 + Mole.height/10);
		
		
	}
	
	/** Return difficulty **/
	public int getDifficulty() {
		return this.difficulty;
	}
	
	/** Change difficulty **/
	public int changeDifficulty(int num) {
		this.difficulty+=num;
		if (this.difficulty > 4) {
			this.difficulty = 4;
		}
		if (this.difficulty < 1) {
			this.difficulty = 1;
		}
		return this.difficulty;
	}

	@Override
	public IStage update() {
		return this;
	}

	@Override
	public IStage keyPressed(KeyEvent kev) {
		if (kev.getKey() == ' ') {
			Machine m = new Machine(9, getDifficulty());
			m.makeSlots();
			m.randomSlot();
			m.gameRandom();
			return m;
		} else {
			return this;
		}
	}



}
