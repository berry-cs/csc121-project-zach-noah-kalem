import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import processing.core.PApplet;

import org.junit.jupiter.api.Test;

class Testing {
	//BUTTON DRAW TESTS
    @Test
    void testDraw() {
        Button button1 = new Button(50, 50, 100, 30, "Message");
        PApplet b1 = new PApplet();

        assertEquals(b1.rect(50, 50, 100, 30), b1.text("Message", 25, 25), button1.draw(b1));
    }
}
