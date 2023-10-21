import processing.core.PApplet;
import processing.event.KeyEvent;

public class EndStage implements IStage {

    @Override
    public PApplet draw(PApplet c) {
        c.background(0);
        c.textSize(64);
        c.textAlign(c.CENTER);
        c.text("Game over!", Mole.getWidth()/2, Mole.getHeight()/(84/10));
        c.textSize(24);
        c.text("Press space bar to restart", Mole.getWidth()/2, Mole.getHeight()/(60/10));      
        return c;
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
