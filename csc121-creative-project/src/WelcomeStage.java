import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class WelcomeStage implements IStage {
	private boolean debounce;
	private boolean setup = true;
	private ArrayList<Button> buttons;
	Button difficultyUp, difficultyDown;
	
	public void setup() {
		buttons = new ArrayList<Button>(); 
		difficultyUp = new Button(100, 100, 100, 100, 255, "test");
		difficultyDown = new Button(200, 200, 100, 100, 255, "test");
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
        
        c.textSize(64);
        c.textAlign(c.CENTER);
        c.text("Whack-A-Mole", Mole.getWidth()/2, Mole.getHeight()/(84/10));
        c.textSize(24);
        c.text("Press space bar to start", Mole.getWidth()/2, Mole.getHeight()/(60/10));      
        
        
        
        if (c.mousePressed) {
        	
        }
        
        
        
        return c;
    }

    @Override
    public IStage update() {
        return this;
    }

    @Override
    public IStage keyPressed(KeyEvent kev) {
        if (kev.getKey() == ' ') {
            Machine m = new Machine(9);
            m.makeSlots();
            m.randomSlot();
            m.gameRandom();
            return m;
        } else {
            return this;
        }
    }

    
    
}
