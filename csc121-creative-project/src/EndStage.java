import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

import processing.core.PApplet;
import processing.event.KeyEvent;


public class EndStage implements IStage {
	private int points;
	private String difficultyText;
	
	/**Represents a stage end
	 * @param points
	 * : player score
	 * 
	 * @param difficulty
	 * : 1 - easy
	 * : 2 - medium
	 * : 3 - hard
	 * : 4 - insane 
	 */
	public EndStage(int points, int difficulty) {
		this.points = points;
		this.difficultyText = getDifficulty(difficulty);
		for(int i = 0; i < Mole.maxScores; i++) {
			if(this.points >= Mole.scoresList.get(i)) {		
				Mole.scoresList.remove(Mole.scoresList.size() - 1);
				Mole.scoresList.add(i, this.points);
				break;
			}
		}
		try {
			PrintWriter pw = new PrintWriter(new File("src/assets/scores.txt"));
			for(int i = 0; i < Mole.maxScores; i++) {
				pw.println(Mole.scoresList.get(i));
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public PApplet draw(PApplet c) {
        c.background(0);
        c.textSize(64);
        c.textAlign(c.CENTER);
        c.text("Game over!", Mole.width/2, Mole.height/(84/10));
        c.textSize(24);
        c.text("Press space bar to restart", Mole.width/2, Mole.height/2 + (Mole.height/10)*2);   
        
        c.textSize(32);
        c.text("Score: "+points, Mole.width/2, Mole.height/2); 
        c.text("Difficulty: "+difficultyText, Mole.width/2, Mole.height/2 + Mole.height/10);
        
        //LEADER BOARD
        for(int i = 1; i <= Mole.scoresList.size(); i++){
        	c.text(Mole.scoresList.get(i - 1), Mole.width/2 - 60, Mole.height/6 + 40 * i);
        	c.text("Score " + i, Mole.width/2 + 60, Mole.height/6 + 40 * i);
        } 
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
