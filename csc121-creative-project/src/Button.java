import java.util.Objects;

import processing.core.PApplet;

/** A clickable menu button **/
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
		return (c.mouseX > x && c.mouseY < y + h &&
				c.mouseX < x + w  && c.mouseY > y);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(color, h, text, w, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Button other = (Button) obj;
		return color == other.color && h == other.h && Objects.equals(text, other.text) && w == other.w && x == other.x
				&& y == other.y;
	}


}
