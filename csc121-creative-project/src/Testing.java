import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class Testing {
	//BUTTON DRAW TESTS
    @Test
    void testButton() {
        Button button1 = new Button(50, 50, 100, 30, 255, "Message");
        Button button2 = new Button(20, 20, 500, 30, 155, "Hey Man");
        Button button3 = new Button(60, 60, 60, 60, 60, "Sixty");

        

        assertEquals(button1, new Button(50, 50, 100, 30, 255, "Message"));
        assertEquals(button2, new Button(20, 20, 500, 30, 155, "Hey Man"));
        assertEquals(button3, new Button(60, 60, 60, 60, 60, "Sixty"));

    }
    
    @Test
    void testAddPoints() {
    	Machine m1 = new Machine(2);
    	Machine m2 = new Machine(3);
    	Machine m3 = new Machine(4);
    	
    	assertEquals(m1.points + 2, m1.addPoints(2));
    	assertEquals(m2.points + 15, m2.addPoints(15));
    	assertEquals(m3.points + 360, m3.addPoints(360));
    }
    
    @Test
    void testRandomSlot() {
    	Machine m1 = new Machine(5);
    	Machine m2 = new Machine(6);
    	Machine m3 = new Machine(7);
    	
    	assertEquals((Math.random() * 5), m1.randomSlot());
    	assertEquals((Math.random() * 6), m2.randomSlot());
    	assertEquals((Math.random() * 7), m3.randomSlot());
    }
    
}
