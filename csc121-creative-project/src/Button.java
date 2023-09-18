import processing.core.PApplet;

/* A clickable menu button */
public class Button {
	int x, y;
	int w, h;
	String text;
	
	
	public Button(int x, int y, int w, int h, String text) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.text = text;
		
	}
	
	public PApplet draw(PApplet c) {
		c.rect(x,  y,  w, h);
		c.text(text, x/2, y/2);
        return c;
    }
	
	void pressed() {
		//todo
	}
	
	

}
