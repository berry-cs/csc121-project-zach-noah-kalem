import processing.core.PApplet;
import processing.event.KeyEvent;

public class WelcomeStage implements IStage {

    @Override
    public PApplet draw(PApplet c) {
        c.background(0);
        c.textSize(64);
        c.textAlign(c.CENTER);
        c.text("Whack-A-Mole", Mole.getWidth()/2, Mole.getHeight()/(84/10));
        c.textSize(24);
        c.text("Press space bar to start", Mole.getWidth()/2, Mole.getHeight()/(60/10));      
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
