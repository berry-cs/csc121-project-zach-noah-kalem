import processing.core.*;
import processing.event.KeyEvent;

/** 
 * Represents the currently active thing that is going on in the
 * app's window
 */
public interface IStage {

    PApplet draw(PApplet c);
    IStage update();
    IStage keyPressed(KeyEvent kev);
    //IStage mousePressed(MousePressed mev);
}
