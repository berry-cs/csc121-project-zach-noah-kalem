import processing.core.PApplet;
import processing.event.KeyEvent;

public class EndStage implements IStage {
	private int points;
	private String difficultyText;
	
	public EndStage(int points, int difficulty) {
		this.points = points;
		this.difficultyText = getDifficulty(difficulty);
	}

    @Override
    public PApplet draw(PApplet c) {
        c.background(0);
        c.textSize(64);
        c.textAlign(c.CENTER);
        c.text("Game over!", Mole.getWidth()/2, Mole.getHeight()/(84/10));
        c.textSize(24);
        c.text("Press space bar to restart", Mole.getWidth()/2, Mole.getHeight()/2 + (Mole.getHeight()/10)*2);   
        
        c.textSize(32);
        c.text("Score: "+points, Mole.getWidth()/2, Mole.getHeight()/2); 
        c.text("Difficulty: "+difficultyText, Mole.getWidth()/2, Mole.getHeight()/2 + Mole.getHeight()/10);
        
        return c;
    }
    
    /** Get the difficulty **/
    public String getDifficulty(int difficulty) {
    	if (difficulty == 1) {
			return "Easy";
		}
		else if (difficulty == 2) {
			return "Medium";
		}
		else if (difficulty == 3) {
			return "Hard";
		}
		else if (difficulty == 4) {
			return "Insane";
		}
    	return "";
    }

    @Override
    public IStage update() {
        return this;
    }

    @Override
    public IStage keyPressed(KeyEvent kev) {
        if (kev.getKey() == ' ') {
        	return new WelcomeStage();
        } else {
            return this;
        }
    }

    
    
}
