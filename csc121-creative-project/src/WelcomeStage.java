import processing.core.PApplet;
import processing.event.KeyEvent;

public class WelcomeStage implements IStage {

    @Override
    public PApplet draw(PApplet c) {
        c.background(0);
        c.textSize(32);
        c.textAlign(c.CENTER);
        c.text("Whack-A-Mole", 400, 60);
        c.textSize(24);
        c.text("Press space bar to start", 400, 130);      
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
