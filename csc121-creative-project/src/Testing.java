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
    	Machine m1 = new Machine(2, 1);
    	Machine m2 = new Machine(3, 2);
    	Machine m3 = new Machine(4, 3);
    	
    	assertEquals(m1.getPoints(), 0);
    	assertEquals(0, m2.getPoints());
    	assertEquals(m3.getPoints(), 0);
    	
    	assertEquals(m1.getPoints() + 2, m1.addPoints(2));
    	assertEquals(m2.getPoints() + 15, m2.addPoints(15));
    	assertEquals(m3.getPoints() + 360, m3.addPoints(360));
    	
    	assertEquals(2, m1.getPoints());
    	assertEquals(15, m2.getPoints());
    	assertEquals(360, m3.getPoints());
    }
    
    @Test
    void testMakeSlots() {
    	Machine m1 = new Machine(2, 1);
    	m1.makeSlots();

    	assertEquals(false, m1.getSlot(1).getActive());
		assertEquals(150, m1.getSlot(1).getPoints());
		
		m1.getSlot(1).fillSlot();
		assertEquals(true, m1.getSlot(1).getActive());
    }
    
    @Test
    void testWelcome() {
    	WelcomeStage stage = new WelcomeStage();
    	stage.setup();
    	
    	assertEquals(1, stage.getDifficulty());
    	stage.changeDifficulty(1);
    	assertEquals(2, stage.getDifficulty());
    	stage.changeDifficulty(1);
    	assertEquals(3, stage.getDifficulty());
    	stage.changeDifficulty(1);
    	assertEquals(4, stage.getDifficulty());
    	stage.changeDifficulty(1);
    	assertEquals(4, stage.getDifficulty());
    	stage.changeDifficulty(-5);
    	assertEquals(1, stage.getDifficulty());
    }
    
    @Test
    void testEnd() {
    	EndStage stage = new EndStage(5000, 1);
    	
    	assertEquals("Easy", stage.getDifficulty(1));
    	assertEquals("Medium", stage.getDifficulty(2));
    	assertEquals("Hard", stage.getDifficulty(3));
    	assertEquals("Insane", stage.getDifficulty(4));
    	assertEquals("", stage.getDifficulty(5));

    }
    
    
}
