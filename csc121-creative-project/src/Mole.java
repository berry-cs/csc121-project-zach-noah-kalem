import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;

import processing.core.*;
import processing.event.*;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class Mole extends PApplet {
	public static final int width = 1000;
	public static final int height = 1000;
	public static final int grey = 100;
	public static final int white = 255;
	private IStage currentStage;
	private static Minim minim;
	private static AudioPlayer song;
	private static AudioSample hit, miss;
	

	public void settings() {
		this.size(width, height);
	}

	public void setup() {
		minim = new Minim(this);
		song = minim.loadFile("src/assets/palace.mp3");
		hit = minim.loadSample("src/assets/hit.mp3");
		miss = minim.loadSample("src/assets/miss.mp3");
		currentStage = new WelcomeStage();   // new Machine(9);
	}

	public void draw() {
		currentStage = currentStage.update();
		currentStage.draw(this);
		playMusic();
	}

	public void mousePressed(MouseEvent mev) {
		//w = w.mousePressed(mev);
	}

	//Currently only 2 game states, change later
	public void keyPressed(KeyEvent kev) {
		currentStage = currentStage.keyPressed(kev);
	}
	
	/** Continuously plays music **/
	public void playMusic() {
		if (!song.isPlaying())
			song.rewind();
			song.play();
	}
	
	public static void playSound(AudioSample sound) {
		sound.trigger();
	}
	
	public static AudioSample getHit() {
		return hit;
	}
	
	public static AudioSample getMiss() {
		return miss;
	}

	public static void main(String[] args) {
		PApplet.runSketch(new String[] { "Mole" }, new Mole());
	}
}
