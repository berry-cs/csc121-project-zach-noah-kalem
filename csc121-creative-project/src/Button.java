import processing.core.PApplet;

/* A clickable menu button */
public class Button {
	private	int x, y;
	private	int w, h;
	private	String text;
	private int color;

	public Button(int x, int y, int w, int h, int color, String text) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
		this.text = text;

	}

	/** Draw button on screen **/
	public PApplet draw(PApplet c) {
		c.fill(color);
		c.rect(x, y, w, h);
		c.fill(0);
		c.text(text, x + w/2, y + h/2);
		c.fill(color);
		return c;
	}

	/** Button was clicked **/
	public boolean clicked(PApplet c) {
		return (c.mouseX > x - w/2 && c.mouseY < y + h/2 &&
				c.mouseX < x + w/2 && c.mouseY > y - h/2);
	}


}
